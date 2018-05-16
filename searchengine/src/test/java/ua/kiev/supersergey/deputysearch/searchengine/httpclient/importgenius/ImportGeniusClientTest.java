package ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.googlesearch.entity.Items;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.googlesearch.entity.Item;

import java.util.Arrays;

/**
 * Created by supersergey on 02.05.18.
 */
@RunWith(JUnit4.class)
public class ImportGeniusClientTest {
    @Test
    public void doGet() throws Exception {
        SearchResult result = ImportGeniusClient.doGet("https://www.importgenius.com/ukraine/suppliers/cut-sew-industrial-co-ltd-rm-16-9f-no-360-2");
        System.out.println(result);
    }
}