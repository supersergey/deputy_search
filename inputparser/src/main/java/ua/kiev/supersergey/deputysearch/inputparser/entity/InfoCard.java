package ua.kiev.supersergey.deputysearch.inputparser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by supersergey on 20.04.18.
 */
@Data
@Entity
@NoArgsConstructor
public class InfoCard {
    @Id
    private String id;
    @JsonProperty("last_name")
    private String lastName;
    private String patronymic;
    @JsonProperty("created_date")
    private Date createdDate;
    private String url;
    @JsonProperty("first_name")
    private String firstName;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "infoCard")
    List<Company> companies = new ArrayList<>();
}
