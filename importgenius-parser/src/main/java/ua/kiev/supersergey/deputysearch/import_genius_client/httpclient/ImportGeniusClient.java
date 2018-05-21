package ua.kiev.supersergey.deputysearch.import_genius_client.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ua.kiev.supersergey.deputysearch.import_genius_client.exception.InvalidSearchResultException;

import java.io.IOException;

/**
 * Created by supersergey on 01.05.18.
 */
@Slf4j
public class ImportGeniusClient {
    public static Document fetchImportGeniusPage(String url) throws InvalidSearchResultException {
        log.info("Fetching url: " + url);
        try {
            return Jsoup.connect(url).get();
        }  catch (Throwable ex) {
            log.error("Could not fetch URL: " + url, ex);
            throw new InvalidSearchResultException(ex);
        }
    }
}
