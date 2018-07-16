package ua.kiev.supersergey.deputysearch.inputparser.json.deserializer;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.DeclarationsResponse;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class DeclarationsResponseDeserializerTest {

    @Test
    public void deserialize() throws Exception{
        URL resource = this.getClass().getClassLoader().getResource("testDeclaration.json");
        byte[] declarationJson = Files.readAllBytes(Paths.get(resource.getFile()));
        ClientResponse response = ClientResponse
                .create(HttpStatus.OK)
                .header("content-type", "APPLICATION/JSON")
                .body(new String(declarationJson, StandardCharsets.UTF_8))
                .build();
//        System.out.println(new String(declarationJson, StandardCharsets.UTF_8));
        System.out.println(response.bodyToMono(DeclarationsResponse.class).block());
    }
}