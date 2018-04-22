package ua.kiev.supersergey.deputysearch.inputparser.deserializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kiev.supersergey.deputysearch.inputparser.deserializer.infocard.InfoCardListDeserializer;
import ua.kiev.supersergey.deputysearch.inputparser.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.util.JsonNodeUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

/**
 * Created by supersergey on 21.04.18.
 */
@Service
public class DeclarationsDeserializer {
    private ObjectMapper mapper = new ObjectMapper();
    private AbstractNodeListDeserializer<InfoCard> infoCardListDeserializer;

    @Autowired
    public DeclarationsDeserializer(AbstractNodeListDeserializer<InfoCard> infoCardListDeserializer) {
        this.infoCardListDeserializer = infoCardListDeserializer;
    }

    @PostConstruct
    private void init() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<InfoCard> deserializeDeclarations(InputStreamReader isr) {
        try {
            JsonNode root = mapper.readTree(isr);
            JsonNode objectList = root.get("results").get("object_list");
            return JsonNodeUtils.isEmptyList(objectList) ?
                    Collections.EMPTY_LIST :
                    infoCardListDeserializer.deserialize(objectList, mapper);
        } catch (IOException ex) {
            throw new RuntimeException("Cannot read input stream");
        }
    }
}
