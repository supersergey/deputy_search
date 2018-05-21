package ua.kiev.supersergey.webclient.deputysearch.import_genius_client.parser;

import org.jsoup.nodes.Element;
import ua.kiev.supersergey.webclient.deputysearch.import_genius_client.parser.agent.Agent;

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
