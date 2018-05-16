package ua.kiev.supersergey.deputysearch.inputparser.json.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by supersergey on 21.04.18.
 */

@NoArgsConstructor
@Data
public class CompanyJson {
    private String guid;
    private String name;
}
