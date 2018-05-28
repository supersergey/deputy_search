package ua.kiev.supersergey.deputysearch.import_genius_client.parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResultStatus;
import ua.kiev.supersergey.deputysearch.import_genius_client.parser.agent.Agent;
import ua.kiev.supersergey.deputysearch.import_genius_client.parser.freight.Freight;
import ua.kiev.supersergey.deputysearch.import_genius_client.exception.InvalidSearchResultException;


/**
 * Created by supersergey on 19.05.18.
 */
public class ImportGeniusPageParser {
    private final static AgentParser AGENT_PARSER = new AgentParser();
    private final static FreightParser FREIGHT_PARSER = new FreightParser();

    public static SearchResult parseDocument(Document document) throws InvalidSearchResultException {
        if (isPresentBillOfLading(document)) {
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
                    .status(SearchResultStatus.PARSED_OK)
                    .build();
        } else {
            throw new InvalidSearchResultException("Invalid search result");
        }
    }

    private static boolean isPresentBillOfLading(Document document) {
        Elements billOfLading = document.select("div.bill_title");
        return !CollectionUtils.isEmpty(billOfLading) && billOfLading.toString().contains("Bill of Lading");
    }
}
