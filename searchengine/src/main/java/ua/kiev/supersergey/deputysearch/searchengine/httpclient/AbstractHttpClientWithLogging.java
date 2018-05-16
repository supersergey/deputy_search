package ua.kiev.supersergey.deputysearch.searchengine.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

/**
 * Created by supersergey on 02.05.18.
 */
@Slf4j
public abstract class AbstractHttpClientWithLogging {
    protected static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }
}
