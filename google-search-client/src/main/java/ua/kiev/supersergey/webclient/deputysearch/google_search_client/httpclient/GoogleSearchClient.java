package ua.kiev.supersergey.webclient.deputysearch.google_search_client.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.*;
import ua.kiev.supersergey.webclient.deputysearch.google_search_client.entity.Items;
import ua.kiev.supersergey.webclient.deputysearch.google_search_client.entity.Item;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by supersergey on 01.05.18.
 */
@Service
@Slf4j
public class GoogleSearchClient extends AbstractHttpClientWithLogging {
    @Value("${request.template}")
    private String requestTemplate;
    @Value("${google.search.engine.cx}")
    private String cx;
    @Value("${google.search.engine.apikey}")
    private String apiKey;
    private WebClient client;
    private Map<String, String> requestParams;

    @PostConstruct
    private void init() {
        client = WebClient.builder()
                .baseUrl("https://www.googleapis.com/customsearch/v1")
                .filter(logRequest())
                .build();
        requestParams = new HashMap<>();
        requestParams.put("cx", cx);
        requestParams.put("key", apiKey);
    }

    public List<Item> searchByCompany(String companyName) {
        log.info("Searching for the company: " + companyName);
        requestParams.put("query", companyName);
        List<Item> searchResult = client.get()
                .uri(requestTemplate, requestParams)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(Items.class)
                .log()
                .block()
                .getItems();
        return CollectionUtils.isEmpty(searchResult) ? Collections.emptyList() : searchResult;
    }
}
