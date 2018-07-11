package ua.kiev.supersergey.deputysearch.google_search_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
                    switch (response.getErrorCode()) {
                        case 0:
                        case 403:
                            collectResults(c, response);
                            break;
                        default:
                            c.setStatus(CompanyStatus.FAIL);
                            log.error("Error during search, error code: " + response.getErrorCode());
                            break;
                    }
                    companyRepository.save(c);
                    if (response.getErrorCode() == HttpStatus.FORBIDDEN.value()) {
                        log.error("Google search access is forbidden, most probably the daily quota is exceeded");
                        break;
                    }
                }
    }

    private void collectResults(Company c, SearchResultContainer searchResultContainer) {
        if (CollectionUtils.isEmpty(searchResultContainer.getItems())) {
            c.setStatus(CompanyStatus.NOT_FOUND);
            log.info("No search results found for company: " + c.getName());
        } else {
            c.setStatus(CompanyStatus.FOUND);
            c.setSearchResults(
                    searchResultContainer.getItems()
                            .stream()
                            .filter(SEARCH_RESULT_FILTER)
                            .map(item ->
                                    SearchResult.builder()
                                            .company(c)
                                            .url(item.getLink())
                                            .build())
                            .collect(Collectors.toList())
            );
            log.info(String.format("Found: %d results for company: %s", searchResultContainer.getItems().size(), c.getName()));
        }
    }

    private SearchResultContainer searchForCompany(Company c) {
        String shortCompanyName = CompanyNameTransformer.transform(c.getName());
        return googleSearchClient
                .searchByCompany(shortCompanyName);
    }

    private List<Company> findCompaniesUnprocessedByGoogle(int size) {
        return companyRepository.findCompaniesUnprocessedByGoogle(PageRequest.of(0, size));
    }
}
