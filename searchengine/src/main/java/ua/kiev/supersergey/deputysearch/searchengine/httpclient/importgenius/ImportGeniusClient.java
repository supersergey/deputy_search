package ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;
import ua.kiev.supersergey.deputysearch.searchengine.exception.InvalidSearchResultException;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius.parser.FreightParser;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius.parser.AgentParser;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius.parser.ImportGeniusPageParser;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius.parser.agent.Agent;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius.parser.freight.Freight;

import java.io.IOException;

/**
 * Created by supersergey on 01.05.18.
 */
@Slf4j
public class ImportGeniusClient {
    public static SearchResult doGet(String url) throws InvalidSearchResultException {
        log.info("Fetching url: " + url);
        try {
            Document document = Jsoup.connect(url).get();
            return ImportGeniusPageParser.parseDocument(document);
        }  catch (IOException ex) {
            throw new InvalidSearchResultException(ex);
        }
    }
}
