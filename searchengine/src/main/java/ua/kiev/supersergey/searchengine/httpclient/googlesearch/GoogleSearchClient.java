package ua.kiev.supersergey.searchengine.httpclient.googlesearch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.kiev.supersergey.searchengine.httpclient.AbstractHttpClientWithLogging;
import ua.kiev.supersergey.searchengine.httpclient.googlesearch.entity.Item;
import ua.kiev.supersergey.searchengine.httpclient.googlesearch.entity.Items;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by supersergey on 01.05.18.
 */
@Service
@Slf4j
public class GoogleSearchClient extends AbstractHttpClientWithLogging{
    @Value("${request.template}")
    private String requestTemplate;
    @Value("${google.search.engine.cx}")
    private String cx;
    @Value("${google.search.engine.apikey}")
    private String apiKey;
    private WebClient client;
    private Map<String, String> requestParams;

    @PostConstruct
    public void init() {
        client = WebClient.builder()
                .baseUrl("https://www.googleapis.com/customsearch/v1")
                .filter(logRequest())
                .build();
        requestParams = new HashMap<>();
        requestParams.put("cx", cx);
        requestParams.put("key", apiKey);
    }

    public Flux<String> doGet(String companyName) {
        requestParams.put("query", companyName);
        return Flux.fromIterable(
                client.get()
                        .uri(requestTemplate, requestParams)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve().bodyToMono(Items.class)
                        .log()
                        .block()
                        .getItems()).map(Item::getLink);
    }
}
