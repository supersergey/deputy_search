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
import ua.kiev.supersergey.deputysearch.google_search_client.response.SearchResultContainer;

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

    public SearchResultContainer searchByCompany(String companyName) {
        log.info("Searching for the company: " + companyName);
        Map<String, String> requestParams = GoogleSearchClientRequest.newBuilder()
                .withApiKey(apiKey)
                .withCx(cx)
                .withCompanyName(companyName)
                .build();
        return fetchSearchResults(requestParams);
    }

    private SearchResultContainer fetchSearchResults(Map<String, String> requestParams) {
        List<Item> items = new ArrayList<>();
        requestParams.put("start", "1");
        try {
            int startIndex;
            do {
                GoogleSearchEngineResponse searchResult = queryGoogleSearchEngine(requestParams);
                if (!CollectionUtils.isEmpty(searchResult.getItems())) {
                    items.addAll(searchResult.getItems());
                } else {
                    break;
                }
                if (searchResult.getQueries() != null) {
                    startIndex = getNextIndex(searchResult);
                    requestParams.put("start", String.valueOf(startIndex));
                } else {
                    break;
                }
            } while (startIndex > 0 && startIndex < 100); // restriction of Google CSE, no more than 100 items per search
            return SearchResultContainer.builder()
                    .items(items)
                    .build();
        } catch (WebClientResponseException ex) {
            log.error(String.format("Google web client error, code: %d, message: %s", ex.getStatusCode().value(), ex.getStatusText()));
            return SearchResultContainer.builder()
                    .items(items)
                    .errorCode(ex.getStatusCode().value())
                    .errorMessage(ex.getStatusText())
                    .build();
        }
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

    private int getNextIndex(GoogleSearchEngineResponse searchResult) {
        if (searchResult == null) {
            return -1;
        }
        int startIndex;
        if (searchResult.getQueries().getNextPage() != null) {
            startIndex = searchResult.getQueries().getNextPage()[0].getStartIndex();
        } else {
            startIndex = -1;
        }
        return startIndex;
    }
}
