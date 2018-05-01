package ua.kiev.supersergey.searchengine.deputysearch.inputparser.json.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.json.entity.CompanyJson;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.json.entity.InfoCardJson;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.json.util.JsonNodeUtils;

import java.io.IOException;

/**
 * Created by supersergey on 22.04.18.
 */
@Slf4j
@Service
public class Deserializer {
    final private ObjectMapper mapper;

    public Deserializer() {
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Flux<?> deserialize(byte[] raw) {
        try {
            JsonNode results = mapper.readTree(raw).get("results").get("object_list");
            if (JsonNodeUtils.isEmptyList(results)) {
                return Flux.empty();
            }
            return Flux.create(fluxSink -> {
                results.iterator().forEachRemaining(entry -> {
                    String guid = entry.get("guid").asText();
                    InfoCardJson infoCardJson = deserializeInfoCard(entry, mapper, guid);
                    fluxSink.next(infoCardJson);
                    if (!JsonNodeUtils.isEmptyList(entry.get("unified_source").get("step_9"))) {
                        entry.get("unified_source").get("step_9").iterator().forEachRemaining(company -> {
                            CompanyJson companyJson = deserializeCompany(company, mapper, guid);
                            fluxSink.next(companyJson);
                        });
                    }
                });
                fluxSink.complete();
            });
        } catch (IOException ex) {
            throw new RuntimeException("Cannot read input data, aborting");
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
