package ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by supersergey on 20.04.18.
 */
@Entity
@Table(name = "info_card")
@NoArgsConstructor
@Data
public class InfoCard {
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
    private Date parsedDate;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "infoCard")
    List<Company> companies = new ArrayList<>();

    @Override
    public String toString() {
        return "InfoCardJson{" +
                "guid='" + guid + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", createdDate=" + createdDate +
                ", url='" + url + '\'' +
                ", firstName='" + firstName + '\'' +
                ", companies=" + companies +
                '}';
    }
}
