package ua.kiev.supersergey.deputysearch.google_search_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ua.kiev.supersergey.deputysearch.google_search_client.httpclient.GoogleSearchClient;
import ua.kiev.supersergey.deputysearch.google_search_client.response.SearchResultContainer;
import ua.kiev.supersergey.deputysearch.google_search_client.response.filter.GoogleSearchResultFilter;
import ua.kiev.supersergey.deputysearch.commonlib.filter.CompanyNameTransformer;
import ua.kiev.supersergey.deputysearch.commonlib.dao.CompanyRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.CompanyStatus;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by supersergey on 17.05.18.
 */
@Service
@Slf4j
public class GoogleSearchDirector {
    @Value("${google_search_client.number_of_searches_per_day}")
    private int MAX_NUMBER_OF_SEARCHES_PER_DAY;
    @Value("${google_search_client.company_fetch_batch_size}")
    private int BATCH_SIZE;

    private final GoogleSearchClient googleSearchClient;
    private final CompanyRepository companyRepository;
    private static final GoogleSearchResultFilter SEARCH_RESULT_FILTER = new GoogleSearchResultFilter();

    @Autowired
    public GoogleSearchDirector(GoogleSearchClient googleSearchClient, CompanyRepository companyRepository) {
        this.googleSearchClient = googleSearchClient;
        this.companyRepository = companyRepository;
    }

    public void doSearch() {
        List<Company> companiesUnprocessedByGoogle = findCompaniesUnprocessedByGoogle(BATCH_SIZE);
        for (Company c : companiesUnprocessedByGoogle) {
            SearchResultContainer response = searchForCompany(c);
            boolean isContinue = collectResultsWithRegardToErrorCode(c, response);
            companyRepository.save(c);
            if (!isContinue) {
                break;
            }
        }
    }

    private SearchResultContainer searchForCompany(Company c) {
        String shortCompanyName = CompanyNameTransformer.transform(c.getName());
        return googleSearchClient
                .searchByCompany(shortCompanyName);
    }


    private boolean collectResultsWithRegardToErrorCode(Company c, SearchResultContainer searchResultContainer) {
        switch (searchResultContainer.getErrorCode()) {
            case 0:
                if (CollectionUtils.isEmpty(searchResultContainer.getItems())) {
                    c.setStatus(CompanyStatus.NOT_FOUND);
                    log.info("No search results found for company: " + c.getName());
                } else {
                    c.setStatus(CompanyStatus.FOUND);
                    log.info(String.format("Found: %d results for company: %s", searchResultContainer.getItems().size(), c.getName()));
                    assignSearchResultsToCompany(c, searchResultContainer);
                }
                return true;
            case 403:
                c.setStatus(CompanyStatus.FORBIDDEN);
                log.error("Google search access is forbidden, most probably the daily quota is exceeded");
                assignSearchResultsToCompany(c, searchResultContainer);
                return false;
            default:
                log.error(
                        String.format("Failed to query google for company %s. Error code: %d",
                                c.getName(), searchResultContainer.getErrorCode())
                );
                assignSearchResultsToCompany(c, searchResultContainer);
                c.setStatus(CompanyStatus.FAIL);
                return true;
        }
    }

    private void assignSearchResultsToCompany(Company c, SearchResultContainer searchResultContainer) {
        c.setSearchResults(searchResultContainer.getItems()
                .stream()
                .filter(SEARCH_RESULT_FILTER)
                .map(item ->
                        SearchResult.builder()
                                .company(c)
                                .url(item.getLink())
                                .build())
                .collect(Collectors.toList())
        );
    }

    private List<Company> findCompaniesUnprocessedByGoogle(int size) {
        return companyRepository.findCompaniesUnprocessedByGoogle(PageRequest.of(0, size));
    }
}
