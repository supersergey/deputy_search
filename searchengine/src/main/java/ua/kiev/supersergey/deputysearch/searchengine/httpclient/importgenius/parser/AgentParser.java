package ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius.parser.agent.Agent;

/**
 * Created by supersergey on 17.05.18.
 */
public class AgentParser implements Parser<Agent> {
    @Override
    public Agent parse(Element document) {
        return new Agent(
                document.select("h3.shiptle").html(),
                document.select("div.data-con").html());
    }
}
