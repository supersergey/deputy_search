package ua.kiev.supersergey.deputysearch.google_search_client.response.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ua.kiev.supersergey.deputysearch.google_search_client.response.Item;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by supersergey on 19.05.18.
 */
@RunWith(JUnit4.class)
public class GoogleSearchResultFilterTest {
    private List<Item> itemList;

    @Before
    public void init() {
        Item item1 = new Item();
        item1.setLink("https://www.importgenius.com/ukraine/suppliers/ar-mr-pharmacy-inc");
        Item item3 = new Item();
        item3.setLink("https://www.importgenius.com/ukraine/buyers/ar-mr-pharmacy-inc");
        Item item4 = new Item();
        item4.setLink("https://www.importgenius.com/ukraine/importers/ar-mr-pharmacy-inc");
        Item item5 = new Item();
        item5.setLink("https://www.importgenius.com/ukraine/exporters/%D1%81%D0%B4-%D1%82%D0%BE%D0%B2-%D0%B5%D0%B4%D0%B5%D0%BB%D1%8C%D0%B2%D0%B5%D0%B9%D1%81-%D1%84%D1%96%D1%80%D0%BC%D0%B0-%D0%BA%D1%80%D0%B5%D1%81%D1%82%D0%BC%D0%BE%D0%BD%D1%82-%D1%96%D0%BD%D0%B2%D0%B5%D1%81%D1%82%D0%BC%D0%B5%D0%BD%D1%82%D1%81-%D0%BA%D0%BE%D1%80%D0%BF%D0%BE%D1%80%D0%B5%D0%B9%D1%88%D0%B5%D0%BD");
        Item item2 = new Item();
        item2.setLink("https://www.importgenius.com/ukraine/ar-mr-pharmacy-inc");
        itemList = Arrays.asList(item1, item2, item3, item4, item5, null);
    }
    @Test
    public void filterGoogleResults() throws Exception {
        Predicate<Item> predicate = new GoogleSearchResultFilter();
        List<Item> filteredResults = itemList
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
        assertEquals(4, filteredResults.size());

    }

}