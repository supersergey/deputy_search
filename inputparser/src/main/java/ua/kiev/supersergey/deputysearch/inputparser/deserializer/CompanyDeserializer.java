package ua.kiev.supersergey.deputysearch.inputparser.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kiev.supersergey.deputysearch.inputparser.entity.Company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by supersergey on 21.04.18.
 */
@Service
@Slf4j
public class CompanyDeserializer implements Deserializer<List<Company>> {
    @Override
    public List<Company> deserialize(JsonNode nodeList, ObjectMapper mapper) {
        List<Company> result = new ArrayList<>();
        if (nodeList == null) {
            return result;
        }
        try {
            Iterator<JsonNode> iterator = nodeList.iterator();
            while (iterator.hasNext()) {
                JsonNode companyNode = iterator.next();
                Company company = mapper.treeToValue(companyNode, Company.class);
                log.info("Parsed company: " + company.toString());
                result.add(company);
            }
        } catch (JsonProcessingException ex) {
            log.warn("Failed to deserialize company from json, skipping.");
        }
        return result;
    }
}
