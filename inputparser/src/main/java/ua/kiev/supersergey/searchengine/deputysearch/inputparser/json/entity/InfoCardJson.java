package ua.kiev.supersergey.searchengine.deputysearch.inputparser.json.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by supersergey on 20.04.18.
 */
@NoArgsConstructor
@Data
public class InfoCardJson {
    @Id
    private String guid;
    @JsonProperty("last_name")
    private String lastName;
    private String patronymic;
    @JsonProperty("created_date")
    private Date createdDate;
    private String url;
    @JsonProperty("first_name")
    private String firstName;
}
