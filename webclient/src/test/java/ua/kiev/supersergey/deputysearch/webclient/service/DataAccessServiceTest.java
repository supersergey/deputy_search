package ua.kiev.supersergey.deputysearch.webclient.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.kiev.supersergey.deputysearch.commonlib.dao.CompanyRepository;
import ua.kiev.supersergey.deputysearch.commonlib.dao.InfoCardRepository;
import ua.kiev.supersergey.deputysearch.commonlib.dao.SearchResultsRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.deputysearch.webclient.WebClientApp;

@SpringBootTest(classes = WebClientApp.class)
@RunWith(SpringRunner.class)
public class DataAccessServiceTest {
    @Autowired
    private DataAccessService service;
    @Autowired
    private SearchResultsRepository searchResultsRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void fetchNonEmptySearchResults() {

    }
}