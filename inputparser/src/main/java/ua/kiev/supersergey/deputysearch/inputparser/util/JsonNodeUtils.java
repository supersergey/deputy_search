package ua.kiev.supersergey.deputysearch.inputparser.util;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by supersergey on 22.04.18.
 */
public class JsonNodeUtils {
    public static boolean isEmptyList(JsonNode node) {
        return node==null || node.size() == 0;
    }
}
