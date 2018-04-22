package ua.kiev.supersergey.deputysearch.inputparser.filereader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.kiev.supersergey.deputysearch.inputparser.entity.Company;
import ua.kiev.supersergey.deputysearch.inputparser.entity.InfoCard;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by supersergey on 20.04.18.
 */
@Service
public class JsonInputFileReader implements JsonInputReader {
    @Value("${input.file.name}")
    private String fileName;

    @Override
    public InputStreamReader read() {
        try {
            File inputFile = new File(getClass().getClassLoader().getResource(fileName).getFile());
            return new InputStreamReader(new FileInputStream(inputFile));
        } catch (IOException ex) {
            throw new RuntimeException("Cannot read data from the input file: " + fileName, ex);
        }
    }
}
