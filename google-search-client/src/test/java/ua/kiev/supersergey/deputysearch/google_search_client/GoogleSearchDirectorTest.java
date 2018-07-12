package ua.kiev.supersergey.deputysearch.google_search_client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import ua.kiev.supersergey.deputysearch.commonlib.dao.CompanyRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.google_search_client.httpclient.GoogleSearchClient;
import ua.kiev.supersergey.deputysearch.google_search_client.response.Item;
import ua.kiev.supersergey.deputysearch.google_search_client.response.SearchResultContainer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoogleSearchDirectorTest {

    private GoogleSearchDirector googleSearchDirector;
    @Mock
    private GoogleSearchClient client;
    @Mock
    private CompanyRepository repository;

    @Before
    public void init() {
        googleSearchDirector = new GoogleSearchDirector(client, repository);
        when(repository.findCompaniesUnprocessedByGoogle(PageRequest.of(0, 10))).thenReturn(CompanyFactory.getCompanies(10));
        when(client.searchByCompany(any()))
                .thenReturn(SearchResultContainer.builder()
                        .errorCode(0)
                        .items(ItemFactory.getItems(10))
                        .build())
                .thenReturn(SearchResultContainer.builder()
                        .errorCode(0)
                        .items(ItemFactory.getItems(10))
                        .build())
                .thenReturn(SearchResultContainer.builder()
                        .errorCode(403)
                        .items(ItemFactory.getItems(5))
                        .build());
    }

    @Test
    public void doSearch() {
        googleSearchDirector.doSearch();
        verify(repository, times(25)).save(any());
    }

    static class CompanyFactory {
        static List<Company> getCompanies(int count) {
            return IntStream.range(0, count)
                    .mapToObj(i -> Company.builder()
                            .name(String.valueOf(i))
                            .uuid(String.valueOf(i))
                            .build())
                    .collect(Collectors.toList());
        }
    }

    static class ItemFactory {
        static List<Item> getItems(int count) {
            return IntStream.range(0, count)
                    .mapToObj(i -> new Item("https://www.importgenius.com/ukraine/importers/zzzz"))
                    .collect(Collectors.toList());
        }
    }
}