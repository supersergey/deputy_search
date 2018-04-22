package ua.kiev.supersergey.deputysearch.inputparser.filereader;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by supersergey on 20.04.18.
 */
public interface JsonInputReader {
    InputStreamReader read() throws IOException;
}
