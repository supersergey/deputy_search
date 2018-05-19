package ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.inputparser.json.filereader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by supersergey on 20.04.18.
 */
public class JsonInputFileReader {
    public static byte[] read(Path path) throws IOException{
        return Files.readAllBytes(path);
    }
}
