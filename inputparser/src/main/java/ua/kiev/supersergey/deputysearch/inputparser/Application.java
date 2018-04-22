package ua.kiev.supersergey.deputysearch.inputparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ua.kiev.supersergey.deputysearch.inputparser.deserializer.DeclarationsDeserializer;
import ua.kiev.supersergey.deputysearch.inputparser.filereader.JsonInputFileReader;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by supersergey on 20.04.18.
 */
@SpringBootApplication
@ComponentScan(basePackages = "ua.kiev.supersergey.deputysearch.inputparser")
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        InputStreamReader inputStreamReader = context.getBean(JsonInputFileReader.class).read();
        context.getBean(DeclarationsDeserializer.class).deserializeDeclarations(inputStreamReader);
        try {
            inputStreamReader.close();
        } catch (IOException ex) {
            throw new RuntimeException("Cannot read input data", ex);
        }
    }
}
