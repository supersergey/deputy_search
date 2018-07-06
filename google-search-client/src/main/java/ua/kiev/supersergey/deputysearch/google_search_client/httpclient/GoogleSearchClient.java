package ua.kiev.supersergey.deputysearch.google_search_client.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.*;
import ua.kiev.supersergey.deputysearch.google_search_client.request.GoogleSearchClientRequest;
import ua.kiev.supersergey.deputysearch.google_search_client.response.GoogleSearchEngineResponse;
import ua.kiev.supersergey.deputysearch.google_search_client.response.Item;

import java.util.*;

/**
 * Created by supersergey on 01.05.18.
 */
@Service
@Slf4j
@PropertySource("classpath:google.properties")
public class GoogleSearchClient {
    @Value("${request.template}")
    private String requestTemplate;
    @Value("${google.search.engine.cx}")
    private String cx;
    @Value("${google.search.engine.apikey}")
    private String apiKey;
    private final WebClient client;

    public GoogleSearchClient(WebClient client) {
        this.client = client;
    }

    public List<Item> searchByCompany(String companyName) {
        log.info("Searching for the company: " + companyName);
        int startIndex = 1;
        int counter = 0;
        List<Item> items = new ArrayList<>();
        Map<String, String> requestParams = GoogleSearchClientRequest.newBuilder()
                .withApiKey(apiKey)
                .withCx(cx)
                .withCompanyName(companyName)
                .build();
        do {
            requestParams.put("start", String.valueOf(startIndex));
            GoogleSearchEngineResponse searchResult;
            try {
                searchResult = queryGoogleSearchEngine(requestParams);
            } catch (WebClientResponseException ex) {
                log.error(String.format(
                        "Error while querying google: code: %s, status: %d", ex.getMessage(), ex.getStatusCode().value())
                );
                return items;
            }
            if (searchResult != null && searchResult.getQueries() != null && searchResult.getItems() != null) {
                items.addAll(searchResult.getItems());
                counter++;
                startIndex = calculateNextPageStartIndex(searchResult);
            } else {
                break;
            }
        } while (startIndex > 0);
        log.info("Fetched " + counter + " results for company: " + companyName);
        return CollectionUtils.isEmpty(items) ? Collections.emptyList() : items;
    }

    private int calculateNextPageStartIndex(GoogleSearchEngineResponse searchResult) {
        int startIndex;
        if (searchResult.getQueries().getNextPage() != null) {
            startIndex = searchResult.getQueries().getNextPage()[0].getStartIndex();
        } else {
            startIndex = -1;
        }
        return startIndex;
    }

    protected GoogleSearchEngineResponse queryGoogleSearchEngine(Map<String, String> requestParams) {
        return client.get()
                .uri(requestTemplate, requestParams)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(GoogleSearchEngineResponse.class)
                .log()
                .block();
    }
}
