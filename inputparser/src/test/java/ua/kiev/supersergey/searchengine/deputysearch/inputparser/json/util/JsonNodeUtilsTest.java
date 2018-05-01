package ua.kiev.supersergey.searchengine.deputysearch.inputparser.json.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by supersergey on 28.04.18.
 */
@RunWith(JUnit4.class)
public class JsonNodeUtilsTest {
    @Test
    public void testIsEmpty() {
        JsonNode node = new ObjectMapper().createObjectNode();
        assertTrue(JsonNodeUtils.isEmptyList(node));
        assertTrue(JsonNodeUtils.isEmptyList(null));
    }
}