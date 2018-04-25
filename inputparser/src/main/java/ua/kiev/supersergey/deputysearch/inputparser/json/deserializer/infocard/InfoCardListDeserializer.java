package ua.kiev.supersergey.deputysearch.inputparser.json.deserializer.infocard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kiev.supersergey.deputysearch.inputparser.json.deserializer.AbstractNodeListDeserializer;
import ua.kiev.supersergey.deputysearch.inputparser.entity.Company;
import ua.kiev.supersergey.deputysearch.inputparser.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.json.util.JsonNodeUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by supersergey on 22.04.18.
 */
@Slf4j
@Service
public class InfoCardListDeserializer extends AbstractNodeListDeserializer<InfoCard> {
    private AbstractNodeListDeserializer<Company> companyListDeserializer;

    @Autowired
    public InfoCardListDeserializer(AbstractNodeListDeserializer<Company> companyListDeserializer) {
        this.companyListDeserializer = companyListDeserializer;
    }

    @Override
    public InfoCard deserializeNode(JsonNode node, ObjectMapper mapper) {
        try {
            JsonNode infocard = node.get("infocard");
            final InfoCard result = mapper.treeToValue(infocard, InfoCard.class);
            log.info("Parsed infocard: " + result.toString());
            JsonNode companyNode = node.get("unified_source").get("step_9");
            if (!JsonNodeUtils.isEmptyList(companyNode)) {
                List<Company> companies = companyListDeserializer
                        .deserialize(companyNode, mapper)
                        .stream().distinct().collect(Collectors.toList());
                companies.forEach(i -> i.setInfoCard(result));
                result.setCompanies(companies);
            }
            result.setParsedDate(new Date());
            return result;
        } catch (JsonProcessingException ex) {
            log.warn("Could not deserialize infocard json element: ", node.toString());
            return new InfoCard();
        }
    }
}
