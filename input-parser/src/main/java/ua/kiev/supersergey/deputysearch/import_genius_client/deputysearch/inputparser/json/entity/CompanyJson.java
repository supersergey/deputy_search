package ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.inputparser.json.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by supersergey on 21.04.18.
 */

@NoArgsConstructor
@Data
public class CompanyJson {
    private String guid;
    private String name;
}