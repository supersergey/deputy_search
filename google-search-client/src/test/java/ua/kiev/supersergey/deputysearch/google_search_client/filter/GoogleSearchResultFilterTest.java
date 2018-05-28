package ua.kiev.supersergey.deputysearch.google_search_client.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ua.kiev.supersergey.deputysearch.google_search_client.entity.Item;

import java.util.Arrays;
import java.util.List;
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
        Item item2 = new Item();
        item2.setLink("https://www.importgenius.com/ukraine/ar-mr-pharmacy-inc");
        itemList = Arrays.asList(item1, item2, null);
    }
    @Test
    public void filterGoogleResults() throws Exception {
        List<Item> filteredResults = itemList
                .stream()
                .filter(GoogleSearchResultFilter.suppliersOnly())
                .collect(Collectors.toList());
        assertEquals(1, filteredResults.size());

    }

}