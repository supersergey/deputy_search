package ua.kiev.supersergey.deputysearch.import_genius_client.parser;

import org.jsoup.nodes.Element;
import ua.kiev.supersergey.deputysearch.import_genius_client.parser.freight.Freight;

/**
 * Created by supersergey on 17.05.18.
 */
public class FreightParser implements Parser<Freight> {
    @Override
    public Freight parse(Element element) {
        return new Freight(element.select("span.original_marks").html());
    }
}
