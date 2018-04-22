package ua.kiev.supersergey.deputysearch.inputparser.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kiev.supersergey.deputysearch.inputparser.entity.Company;
import ua.kiev.supersergey.deputysearch.inputparser.entity.InfoCard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by supersergey on 21.04.18.
 */
@Slf4j
@Service
public class InfoCardDeserializer implements Deserializer<List<InfoCard>> {
    private Deserializer<List<Company>> companyDeserializer;

    @Autowired
    public InfoCardDeserializer(Deserializer<List<Company>> companyDeserializer) {
        this.companyDeserializer = companyDeserializer;
    }

    @Override
    public List<InfoCard> deserialize(JsonNode nodeList, ObjectMapper mapper) {
        List<InfoCard> result = new ArrayList<>();
        if (nodeList == null) {
            return result;
        }
        try {
            Iterator<JsonNode> iterator = nodeList.elements();
            while (iterator.hasNext()) {
                JsonNode node = iterator.next();
                InfoCard infoCard = mapper.treeToValue(node.get("infocard"), InfoCard.class);
                log.info("Parsed infocard: " + infoCard.toString());
                JsonNode companyNode = node.get("unified_source").get("step_9");
                List<Company> companies = companyDeserializer.deserialize(companyNode, mapper);
                infoCard.setCompanies(companies);
                result.add(infoCard);
            }
        } catch (JsonProcessingException ex) {
            log.warn("Could not deserialize infocard, skipping.");
        }
        return result;
    }
}
