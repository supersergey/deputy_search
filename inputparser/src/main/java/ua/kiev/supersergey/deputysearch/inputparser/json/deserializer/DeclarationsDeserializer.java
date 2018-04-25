package ua.kiev.supersergey.deputysearch.inputparser.json.deserializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kiev.supersergey.deputysearch.inputparser.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.json.util.JsonNodeUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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

    public List<InfoCard> deserializeDeclarations(byte[] rawContent) {
        try {
            JsonNode root = mapper.readTree(rawContent);
            JsonNode objectList = root.get("results").get("object_list");
            return JsonNodeUtils.isEmptyList(objectList) ?
                    Collections.EMPTY_LIST :
                    infoCardListDeserializer.deserialize(objectList, mapper);
        } catch (IOException ex) {
            throw new RuntimeException("Cannot read input stream");
        }
    }


}
