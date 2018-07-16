package ua.kiev.supersergey.deputysearch.inputparser.json.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by supersergey on 21.04.18.
 */

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CompanyJson {
    private String guid;
    private String name;
}