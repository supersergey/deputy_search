package ua.kiev.supersergey.deputysearch.inputparser.filereader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by supersergey on 20.04.18.
 */
@Service
public class JsonInputFileReader implements JsonInputReader {
    @Value("${input.file.name}")
    private String fileName;

    @Override
    public InputStreamReader read() throws IOException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IOException("Cannot read input data from file: " + fileName);
        }
        return new InputStreamReader(new FileInputStream(resource.getFile()));
    }
}
