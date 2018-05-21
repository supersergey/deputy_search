package ua.kiev.supersergey.deputysearch.google_search_client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.kiev.supersergey.deputysearch.google_search_client.entity.Item;
import ua.kiev.supersergey.deputysearch.google_search_client.httpclient.GoogleSearchClient;
import ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.dao.CompanyRepository;
import ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.entity.Company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by supersergey on 20.05.18.
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class GoogleSearchDirectorTest {
    @Autowired
    private GoogleSearchDirector realGoogleSearchDirector;
    @Autowired
    private CompanyRepository repository;
    private GoogleSearchDirector mockedGoogleSearchDirector;

    @Mock
    private GoogleSearchClient client;

    @Before
    public void init() {
        List<Item> items = Arrays.asList(
                new Item("link1/suppliers/zz"),
                new Item("link2/suppliers/zz"),
                new Item("link3/suppliers/zz"),
                new Item("link4/suppliers/zz"));
        mockedGoogleSearchDirector = new GoogleSearchDirector(client, repository);
        when(client.searchByCompany(any())).thenReturn(new ArrayList<Item>());
    }

    @Test
    public void testFindCompaniesUnprocessedByGoogle() throws Exception {
        List<Company> companies = realGoogleSearchDirector.findCompaniesUnprocessedByGoogle(10);
        assertEquals(10, companies.size());
        assertEquals(0, companies.stream()
                .filter(c -> c.getStatus() != null)
                .count());
        assertEquals(0, companies.stream()
                .filter(c -> c.getUrlTimeStamp() != null)
                .count());
    }

    @Test
    public void test() {
        mockedGoogleSearchDirector.search();
    }

}