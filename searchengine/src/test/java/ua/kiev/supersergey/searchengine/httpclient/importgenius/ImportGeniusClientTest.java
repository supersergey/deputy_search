package ua.kiev.supersergey.searchengine.httpclient.importgenius;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.kiev.supersergey.searchengine.httpclient.googlesearch.entity.Item;
import ua.kiev.supersergey.searchengine.httpclient.googlesearch.entity.Items;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by supersergey on 02.05.18.
 */
@RunWith(JUnit4.class)
public class ImportGeniusClientTest {
    @Test
    public void doGet() throws Exception {
//        ImportGeniusClient.doGet("https://www.importgenius.com/ukraine/suppliers/ar-mr-pharmacy-inc").subscribe(System.out::println);
        Item item1 = new Item();
        item1.setLink("abc");
        Item item2 = new Item();
        item2.setLink("zfkljsdf");
        Item item3 = new Item();
        item3.setLink("asfkdjklasdf");
        Items items = new Items();
        items.setItems(Arrays.asList(item1, item2, item3));
        Flux<Item> itemFlux = Flux.fromIterable(items.getItems());
        itemFlux.flatMap(i -> Mono.just(i.getLink())).subscribe(System.out::println);
    }

}