package ua.kiev.supersergey.deputysearch.inputparser.deserializer.infocard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ua.kiev.supersergey.deputysearch.inputparser.deserializer.CommonListDeserializer;
import ua.kiev.supersergey.deputysearch.inputparser.deserializer.company.CompanyDeserializer;
import ua.kiev.supersergey.deputysearch.inputparser.deserializer.NodeDeserializer;
import ua.kiev.supersergey.deputysearch.inputparser.entity.Company;
import ua.kiev.supersergey.deputysearch.inputparser.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.util.JsonNodeUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by supersergey on 22.04.18.
 */
@Slf4j
public class InfoCardDeserializer implements NodeDeserializer<InfoCard> {
    @Override
    public InfoCard deserialize(JsonNode node, ObjectMapper mapper) {
        InfoCard result = new InfoCard();
        try {
            JsonNode infocard = node.get("infocard");
            result = mapper.treeToValue(infocard, InfoCard.class);
            log.info("Parsed infocard: " + result.toString());
            JsonNode companyNode = node.get("unified_source").get("step_9");
            List<Company> companies = new CommonListDeserializer<Company>().deserialize(companyNode, mapper,
                    new CompanyDeserializer());
            result.setCompanies(companies);
        } catch (JsonProcessingException ex) {
            log.warn("Could not deserialize infocard json element: ", node.toString());
        }
        return result;
    }
}
