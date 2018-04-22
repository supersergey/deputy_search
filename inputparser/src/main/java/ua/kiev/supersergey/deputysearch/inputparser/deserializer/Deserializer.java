package ua.kiev.supersergey.deputysearch.inputparser.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.kiev.supersergey.deputysearch.inputparser.entity.InfoCard;

/**
 * Created by supersergey on 21.04.18.
 */
public interface Deserializer<T> {
    T deserialize(JsonNode nodeList, ObjectMapper mapper);
}
