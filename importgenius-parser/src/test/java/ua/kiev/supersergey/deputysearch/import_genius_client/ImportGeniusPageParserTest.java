package ua.kiev.supersergey.deputysearch.import_genius_client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.deputysearch.import_genius_client.parser.ImportGeniusPageParser;
import ua.kiev.supersergey.deputysearch.import_genius_client.exception.InvalidSearchResultException;


import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResultStatus.PARSED_OK;

/**
 * Created by supersergey on 19.05.18.
 */
@RunWith(JUnit4.class)
public class ImportGeniusPageParserTest {
    @Test
    public void parseDocument() throws Exception {
        Document document;
        URI uri = getClass().getClassLoader().getResource("importGenius-search-result-1.html").toURI();
        document = Jsoup.parse(Paths.get(uri).toFile(), StandardCharsets.UTF_8.name());
        SearchResult result = ImportGeniusPageParser.parseDocument(document);
        assertNotNull(result);
        assertEquals("Пп Вітал-Плюс", result.getRecepientName());
        assertEquals("61024, М. Харків, Вул. Пушкінська, 63", result.getRecepientAddress());
        assertEquals("Ar Mr Pharmacy Inc", result.getSenderName());
        assertEquals(
                "\"\"1.Дезодоранти-антиперспіранти тверді та гелеві ТМ \"\"\"\"Secret\"\"\"\", в неаерозольнійупаковці, в пластикових флаконах-стіках, без вмісту озоноруйнуючих речовин,упако вані для роздрібної торгівлі: SECRET EXP C/G 2.6 OZ LAVENDER, UPC3700020468 - 12 0шт.; SECRET EXP C/G 2.6 OZ SUMMRBERRY, UPC 3700080905 - 120шт.;SECRET EXP I/S2.6 OZ COCO KIS, UPC 3700080949 -120шт.; SECRET I/S 1.6 OZ POWDER FRSH, UPC3700012331 - 36шт.; SECRET I/S 2.6 OZ OOH LA LAVEND, UPC 3700080955 - 120шт.;SECRET I/S 2.6 OZ POWDR FRSH, UPC 3700012343 - 36шт.; SECRET I/S 2.6 OZ SHEERCLEAN, UPC 3700012335 - 36шт.; SECRET I/S 2.6 OZ SHWR FRSH, UPC 3700012345 -36шт.; SECRET I/S 2.6 OZ UNSCENTED, UPC 3700012340 - 36шт.; SECRET OUTLAST 2.6OZ CLEAN CLR, UPC 3700080822 - 120шт.; SECRET OUTLAST 2.6 OZ CLR GL UNS, UPC3700080831 - 120шт.; SECRET OUTLAST I/S 2.6 OZ CMPLT, UPC 3700080805 - 60шт.;SECRET OUTLAST I/S2.6 OZ UNSCNT, UPC 3700080806 - 60шт.; SECRET OUTLST 2.6 Z CLR GEL PWD, UPC3700080819 - 120шт.; SECRET W/S 1.7 OZ SHOWER FRESH, UPC\"\" \"\" 3700012445 - 36шт.;SECRET W/S 2.7 OZ POWDER FRESH, UPC 3700012451 - 36шт.Виробник: Procter &amp; Gamble Company.Торгова марка: SECRET.Країна виробництва: US.\"\"", result.getFreightDesc());
    }

    @Test(expected = InvalidSearchResultException.class)
    public void parseInvalidDocument() throws Exception{
        Document document;
        URI uri = getClass().getClassLoader().getResource("invalid-search.html").toURI();
        document = Jsoup.parse(Paths.get(uri).toFile(), StandardCharsets.UTF_8.name());
        ImportGeniusPageParser.parseDocument(document);
    }

    @Test
    public void parseRealDocument() throws Exception{
        URI uri = getClass().getClassLoader().getResource("why-irrelevant.html").toURI();
        Document document = Jsoup.parse(Paths.get(uri).toFile(), StandardCharsets.UTF_8.name());
        SearchResult searchResult = ImportGeniusPageParser.parseDocument(document);
        assertEquals(PARSED_OK, searchResult.getStatus());
    }

}