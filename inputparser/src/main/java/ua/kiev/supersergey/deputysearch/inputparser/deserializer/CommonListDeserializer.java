package ua.kiev.supersergey.deputysearch.inputparser.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by supersergey on 22.04.18.
 */
@Slf4j
public class CommonListDeserializer<T> {
    public List<T> deserialize(JsonNode nodeList, ObjectMapper mapper, NodeDeserializer<T> nodeDeserializer) {
        if (nodeList == null || nodeList.size() == 0) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        Iterator<JsonNode> iterator = nodeList.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            T element = nodeDeserializer.deserialize(node, mapper);
            result.add(element);
        }
        return result;
    }
}
