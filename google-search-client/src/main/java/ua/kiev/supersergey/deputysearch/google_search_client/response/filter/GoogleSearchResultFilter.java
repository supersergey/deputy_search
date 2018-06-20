package ua.kiev.supersergey.deputysearch.google_search_client.response.filter;

import ua.kiev.supersergey.deputysearch.google_search_client.response.Item;

import java.util.function.Predicate;

/**
 * Created by supersergey on 19.05.18.
 */
public class GoogleSearchResultFilter implements Predicate<Item>{
    public boolean test(Item item) {
        return item != null 
                && item.getLink() != null 
                && item.getLink().matches("^.*(suppliers|exporters|importers|buyers).*$");
    }
}
