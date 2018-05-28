package ua.kiev.supersergey.deputysearch.google_search_client.filter;

import ua.kiev.supersergey.deputysearch.google_search_client.entity.Item;

import java.util.function.Predicate;

/**
 * Created by supersergey on 19.05.18.
 */
public class GoogleSearchResultFilter {
    public static Predicate<Item> suppliersOnly() {
        return item -> item != null && item.getLink().contains("/suppliers/");
    }
}
