package ua.kiev.supersergey.searchengine.httpclient.importgenius;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ua.kiev.supersergey.searchengine.httpclient.AbstractHttpClientWithLogging;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by supersergey on 01.05.18.
 */
@Slf4j
public class ImportGeniusClient {
    public static String doGet(String url) throws IOException {
        log.info("Fetching url: " + url);
        Jsoup.connect(url).get();
        return null; // fixme
    }
}
