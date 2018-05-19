package ua.kiev.supersergey.deputysearch.google_search_client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.entity.Company;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by supersergey on 20.05.18.
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class GoogleSearchDirectorTest {
    @Autowired
    private GoogleSearchDirector googleSearchDirector;

    @Test
    public void testFindCompaniesUnprocessedByGoogle() throws Exception {
        List<Company> companies = googleSearchDirector.findCompaniesUnprocessedByGoogle(10);
        assertEquals(10, companies.size());
        assertEquals(0, companies.stream()
                .filter(c -> c.getStatus() != null)
                .count());
        assertEquals(0, companies.stream()
                .filter(c -> c.getUrlTimeStamp() != null)
                .count());
    }

}