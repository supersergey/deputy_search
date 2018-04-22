package ua.kiev.supersergey.deputysearch.inputparser.deserializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kiev.supersergey.deputysearch.inputparser.entity.InfoCard;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by supersergey on 21.04.18.
 */
@Service
public class DeclarationsDeserializer {
    private ObjectMapper mapper = new ObjectMapper();
    private Deserializer<List<InfoCard>> infoCardDeserializer;

    @Autowired
    public DeclarationsDeserializer(Deserializer<List<InfoCard>> infoCardDeserializer) {
        this.infoCardDeserializer = infoCardDeserializer;
    }

    @PostConstruct
    private void init() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<InfoCard> deserializeDeclarations(InputStreamReader isr) {
        try {
            JsonNode root = mapper.readTree(isr);
            JsonNode objectList = root.get("results").get("object_list");
            return infoCardDeserializer.deserialize(objectList, mapper);
        }
        catch (IOException ex) {
            throw new RuntimeException("Cannot read input stream");
        }
    }
}
