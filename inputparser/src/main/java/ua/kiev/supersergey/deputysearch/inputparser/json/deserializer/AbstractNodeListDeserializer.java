package ua.kiev.supersergey.deputysearch.inputparser.json.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by supersergey on 22.04.18.
 */
@Slf4j
public abstract class AbstractNodeListDeserializer<T> {

    public List<T> deserialize(JsonNode nodeList, ObjectMapper mapper) {
        if (nodeList == null || nodeList.size() == 0) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        Iterator<JsonNode> iterator = nodeList.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            T element = deserializeNode(node, mapper);
            result.add(element);
        }
        return result;
    }



    public abstract T deserializeNode(JsonNode node, ObjectMapper mapper);
}
