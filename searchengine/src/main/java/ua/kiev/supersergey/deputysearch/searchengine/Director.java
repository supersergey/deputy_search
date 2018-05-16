package ua.kiev.supersergey.deputysearch.searchengine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ua.kiev.supersergey.deputysearch.commonlib.dao.CompanyRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by supersergey on 17.05.18.
 */
@Service
@Slf4j
public class Director {
    private final static int BATCH_SIZE = 30;
    private final static int MAX_NUMBER_OF_SEARCHES_PER_DAY = 100;
    private CompanyRepository companyRepository;

    @Autowired
    public Director(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void parseCompanies(int maxNumber, long duration) {
        long startTime = new Date().getTime();
        int numberOfSearchesRemaining = MAX_NUMBER_OF_SEARCHES_PER_DAY;
        boolean moreCompaniesAvailable = true;
        while (moreCompaniesAvailable) {
            List<Company> companies = fetchCompanies(BATCH_SIZE);
            if (CollectionUtils.isEmpty(companies)) {
                log.info("No more companies available for search");
                break;
            } else {
                log.info(String.format("Loaded %d companies", companies.size()));
                Iterator<Company> iterator = companies.iterator();
                while (iterator.hasNext() && numberOfSearchesRemaining > 0) {
//                    ImportGeniusClient.doGet(iterator.next().getName());
                }
            }

        }
    }

    private long getCurrentTime() {
        return new Date().getTime();
    }

    private List<Company> fetchCompanies(int size) {
        return companyRepository.findWhereCompanyStatusIsNotNull(PageRequest.of(0, size));
    }
}
