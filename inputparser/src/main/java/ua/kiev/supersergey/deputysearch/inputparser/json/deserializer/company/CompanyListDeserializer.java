package ua.kiev.supersergey.deputysearch.inputparser.json.deserializer.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kiev.supersergey.deputysearch.inputparser.json.deserializer.AbstractNodeListDeserializer;
import ua.kiev.supersergey.deputysearch.inputparser.entity.Company;

/**
 * Created by supersergey on 22.04.18.
 */
@Slf4j
@Service
public class CompanyListDeserializer extends AbstractNodeListDeserializer<Company> {
    @Override
    public Company deserializeNode(JsonNode node, ObjectMapper mapper) {
        Company result = new Company();
        try {
            result = mapper.treeToValue(node, Company.class);
            log.info("Parsed company: " + result.toString());
        } catch (JsonProcessingException ex) {
            log.warn("Could not deserialize company json element: ", node.toString());
        }
        return result;
    }
}
