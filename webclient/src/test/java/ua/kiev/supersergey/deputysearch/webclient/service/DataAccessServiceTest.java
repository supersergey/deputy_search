package ua.kiev.supersergey.deputysearch.webclient.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.kiev.supersergey.deputysearch.webclient.WebClientApp;

@SpringBootTest(classes = WebClientApp.class)
@RunWith(SpringRunner.class)
public class DataAccessServiceTest {
    @Autowired
    private DataAccessService service;

    @Test
    public void fetchNonEmptySearchResults() {
//        List<SearchResult> searchResults = service.fetchNonEmptySearchResults(0, 10, Sort.by(Sort.Order.by("lastName")));
//        System.out.println(searchResults);
    }
}