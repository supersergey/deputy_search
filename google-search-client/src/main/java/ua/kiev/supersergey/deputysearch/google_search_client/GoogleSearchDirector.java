package ua.kiev.supersergey.deputysearch.google_search_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ua.kiev.supersergey.deputysearch.google_search_client.filter.GoogleSearchResultFilter;
import ua.kiev.supersergey.deputysearch.google_search_client.httpclient.GoogleSearchClient;
import ua.kiev.supersergey.deputysearch.google_search_client.filter.CompanyNameTransformer;
import ua.kiev.supersergey.deputysearch.commonlib.dao.CompanyRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.CompanyStatus;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;

import java.util.Date;
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

    private GoogleSearchClient googleSearchClient;
    private CompanyRepository companyRepository;

    @Autowired
    public GoogleSearchDirector(GoogleSearchClient googleSearchClient, CompanyRepository companyRepository) {
        this.googleSearchClient = googleSearchClient;
        this.companyRepository = companyRepository;
    }

    private long getCurrentTime() {
        return new Date().getTime();
    }

    @Transactional
    public int search() {
        List<Company> companiesUnprocessedByGoogle = findCompaniesUnprocessedByGoogle(BATCH_SIZE);
        int counter = 0;
        if (CollectionUtils.isEmpty(companiesUnprocessedByGoogle)) {
            log.info("0 companies left");
        } else {
            for (Company c: companiesUnprocessedByGoogle) {
                List<SearchResult> searchResults = searchForCompany(c);
                counter += collectSearchResults(c, searchResults);
                companyRepository.save(c);
            }
        }
        return counter;
    }

    private int collectSearchResults(Company c, List<SearchResult> searchResults) {
        int counter = 0;
        if (CollectionUtils.isEmpty(searchResults)) {
            log.info("No search results found for company: " + c.getName());
            c.setStatus(CompanyStatus.NOT_FOUND);
        } else {
            log.info(String.format("Found: %d results for company: %s", searchResults.size(), c.getName()));
            c.setStatus(CompanyStatus.FOUND);
            c.setSearchResults(searchResults);
            counter++;
        }
        c.setUrlTimeStamp(new Date());
        return counter;
    }

    private List<SearchResult> searchForCompany(Company c) {
        String shortCompanyName = CompanyNameTransformer.transform(c.getName());
        return googleSearchClient
                .searchByCompany(shortCompanyName)
                .stream()
                .filter(GoogleSearchResultFilter.suppliersOnly())
                .map(u -> SearchResult.builder()
                        .url(u.getLink())
                        .company(c)
                        .build()
                )
                .collect(Collectors.toList());
    }

    List<Company> findCompaniesUnprocessedByGoogle(int size) {
        return companyRepository.findCompaniesUnprocessedByGoogle(PageRequest.of(0, size));
    }
}
