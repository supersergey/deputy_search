package ua.kiev.supersergey.deputysearch.inputparser.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;
import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.CompanyJson;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.DeclarationsResponse;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.InfoCardJson;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.Paginator;
import ua.kiev.supersergey.deputysearch.inputparser.json.util.JsonNodeUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DeclarationsResponseDeserializer extends StdDeserializer<DeclarationsResponse> {

    private ObjectMapper mapper;

    public DeclarationsResponseDeserializer() {
        super(DeclarationsResponse.class);
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public DeclarationsResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        JsonNode objectList = root.get("results").get("object_list");
        Paginator paginator = deserializePaginator(root.get("results").get("paginator"));
        List<InfoCardJson> infocards = new ArrayList<>();
        objectList.iterator().forEachRemaining(entry -> {
            String guid = entry.get("guid").asText();
            InfoCardJson infoCardJson = deserializeInfoCard(entry, mapper, guid);
            if (!JsonNodeUtils.isEmptyList(entry.get("unified_source").get("step_9"))) {
                entry.get("unified_source").get("step_9").iterator().forEachRemaining(company -> {
                    CompanyJson companyJson = deserializeCompany(company, mapper, guid);
                    infoCardJson.getCompanies().add(companyJson);
                });
            }
            infocards.add(infoCardJson);
        });
        DeclarationsResponse declarationsResponse = new DeclarationsResponse();
        declarationsResponse.setPaginator(paginator);
        declarationsResponse.setInfocards(infocards);
        return declarationsResponse;
    }

    private Paginator deserializePaginator(JsonNode jsonNode) {
        try {
            return mapper.treeToValue(jsonNode, Paginator.class);
        } catch (JsonProcessingException ex) {
            log.warn("Could not deserialize paginator: ", jsonNode.toString());
            return new Paginator();
        }
    }

    private InfoCardJson deserializeInfoCard(JsonNode node, ObjectMapper mapper, String guid) {
        try {
            JsonNode infocard = node.get("infocard");
            final InfoCardJson result = mapper.treeToValue(infocard, InfoCardJson.class);
            result.setGuid(guid);
            log.info("Parsed infocard: " + result.toString());
            return result;
        } catch (JsonProcessingException ex) {
            log.warn("Could not deserialize infocard json element: ", node.toString());
            return new InfoCardJson();
        }
    }

    private CompanyJson deserializeCompany(JsonNode node, ObjectMapper mapper, String guid) {
        CompanyJson result = new CompanyJson();
        try {
            result = mapper.treeToValue(node, CompanyJson.class);
            result.setGuid(guid);
            log.info("Parsed company: " + result.toString());
        } catch (JsonProcessingException ex) {
            log.warn("Could not deserialize company json element: ", node.toString());
        }
        return result;
    }
}
