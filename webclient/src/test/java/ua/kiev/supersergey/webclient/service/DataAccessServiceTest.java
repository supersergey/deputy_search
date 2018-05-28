package ua.kiev.supersergey.webclient.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.webclient.WebClientApp;

import java.util.List;

import static org.junit.Assert.*;

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