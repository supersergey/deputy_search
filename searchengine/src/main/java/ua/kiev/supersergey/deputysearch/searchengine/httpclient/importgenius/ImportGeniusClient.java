package ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;
import ua.kiev.supersergey.deputysearch.searchengine.exception.InvalidSearchResultException;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius.parser.FreightParser;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius.parser.AgentParser;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius.parser.agent.Agent;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius.parser.freight.Freight;

import java.io.IOException;

/**
 * Created by supersergey on 01.05.18.
 */
@Slf4j
public class ImportGeniusClient {
    private final static AgentParser AGENT_PARSER = new AgentParser();
    private final static FreightParser FREIGHT_PARSER = new FreightParser();

    public static SearchResult doGet(String url) throws InvalidSearchResultException {
        log.info("Fetching url: " + url);
        try {
            Document document = Jsoup.connect(url).get();
            if (isPresentBillOfLading(document)) {
                return parseDocument(document);
            } else {
                throw new InvalidSearchResultException("Invalid search result");
            }
        }  catch (IOException ex) {
            throw new InvalidSearchResultException(ex);
        }
    }

    private static SearchResult parseDocument(Document document) {
        Elements mainDataTable = document
                .select("div.data-box.clearfix")
                .select("table")
                .select("tbody")
                .get(0)
                .select("tr")
                .select("td");
        Agent recepient = AGENT_PARSER.parse(mainDataTable.get(0));
        Agent sender = AGENT_PARSER.parse(mainDataTable.get(1));
        Freight freight = FREIGHT_PARSER.parse(mainDataTable.get(2));
        return SearchResult
                .builder()
                .senderName(sender.getName())
                .senderAddress(sender.getAddress())
                .recepientName(recepient.getName())
                .recepientAddress(recepient.getAddress())
                .freightDesc(freight.getDesc())
                .build();
    }

    private static boolean isPresentBillOfLading(Document document) {
        Elements billOfLading = document.select("div.bill_title");
        return !CollectionUtils.isEmpty(billOfLading) && billOfLading.toString().contains("Bill of Lading");
    }
}
