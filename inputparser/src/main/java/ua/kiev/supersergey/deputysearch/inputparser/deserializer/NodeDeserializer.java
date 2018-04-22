package ua.kiev.supersergey.deputysearch.inputparser.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Created by supersergey on 22.04.18.
 */
public interface NodeDeserializer<T> {
    T deserialize(JsonNode rootNode, ObjectMapper mapper);
}
