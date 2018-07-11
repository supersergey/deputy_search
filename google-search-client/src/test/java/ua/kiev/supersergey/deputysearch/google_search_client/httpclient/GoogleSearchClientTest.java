package ua.kiev.supersergey.deputysearch.google_search_client.httpclient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import ua.kiev.supersergey.deputysearch.google_search_client.response.GoogleSearchEngineResponse;
import ua.kiev.supersergey.deputysearch.google_search_client.response.Item;
import ua.kiev.supersergey.deputysearch.google_search_client.response.SearchResultContainer;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GoogleSearchClientTest{

    @Mock
    private WebClient webClient;
    private GoogleSearchClientMock googleSearchClient;
    private ClientResponse responsePage1;
    private ClientResponse responsePage2;
    private ClientResponse emptryResponse;
    static int counter = 0;

    @Before
    public void init() throws Exception{
        googleSearchClient = new GoogleSearchClientMock(webClient);
        URL resource = this.getClass().getClassLoader().getResource("google_se_response_page1.json");
        byte[] page1Json = Files.readAllBytes(Paths.get(resource.getFile()));
        resource = this.getClass().getClassLoader().getResource("google_se_response_page2.json");
        byte[] page2Json = Files.readAllBytes(Paths.get(resource.getFile()));
        resource = this.getClass().getClassLoader().getResource("google_se_empty_response.json");
        byte[] page3Json = Files.readAllBytes(Paths.get(resource.getFile()));
        responsePage1 = ClientResponse
                .create(HttpStatus.OK)
                .header("content-type", "APPLICATION/JSON")
                .body(new String(page1Json, StandardCharsets.UTF_8))
                .build();
        responsePage2 = ClientResponse
                .create(HttpStatus.OK)
                .header("content-type", "APPLICATION/JSON")
                .body(new String(page2Json, StandardCharsets.UTF_8))
                .build();
        emptryResponse = ClientResponse
                .create(HttpStatus.OK)
                .header("content-type", "APPLICATION/JSON")
                .body(new String(page3Json, StandardCharsets.UTF_8))
                .build();
    }

    @Test
    public void searchByCompany() {
        SearchResultContainer container = googleSearchClient.searchByCompany("");
        assertNotNull(container.getItems());
        assertEquals(14, container.getItems().size());
    }

    class GoogleSearchClientMock extends GoogleSearchClient{

        public GoogleSearchClientMock(WebClient client) throws Exception{
            super(client);
        }

        @Override
        protected GoogleSearchEngineResponse queryGoogleSearchEngine(Map<String, String> requestParams) {
            if (counter == 0) {
                counter++;
                return responsePage1.bodyToMono(GoogleSearchEngineResponse.class).block();
            }
            if (counter == 1) {
                counter++;
                return responsePage2.bodyToMono(GoogleSearchEngineResponse.class).block();
            }
            return emptryResponse.bodyToMono(GoogleSearchEngineResponse.class).block();
        }
    }
}