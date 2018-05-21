package ua.kiev.supersergey.webclient.deputysearch.import_genius_client.parser;

import org.jsoup.nodes.Element;

/**
 * Created by supersergey on 17.05.18.
 */
public interface Parser<T> {
    T parse(Element element);
}
