package ua.kiev.supersergey.deputysearch.google_search_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ua.kiev.supersergey.deputysearch.google_search_client.entity.Item;
import ua.kiev.supersergey.deputysearch.google_search_client.filter.CompanyNameTransformer;
import ua.kiev.supersergey.deputysearch.google_search_client.httpclient.GoogleSearchClient;
import ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.dao.CompanyRepository;
import ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.entity.Company;

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

    public int search() {
        List<Company> companiesUnprocessedByGoogle = findCompaniesUnprocessedByGoogle(BATCH_SIZE);
        if (CollectionUtils.isEmpty(companiesUnprocessedByGoogle)) {
            log.info("0 companies left");
        } else {
            companiesUnprocessedByGoogle
                    .stream()
                    .map(c -> CompanyNameTransformer.transform(c.getName()))
                    .forEach(c -> {
                        List<Item> items = googleSearchClient.searchByCompany(c);

                    });

        }
        return counter;
    }

    protected List<Company> findCompaniesUnprocessedByGoogle(int size) {
        return companyRepository.findCompaniesUnprocessedByGoogle(PageRequest.of(0, size));
    }
}
