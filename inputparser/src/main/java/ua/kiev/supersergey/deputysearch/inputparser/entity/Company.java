package ua.kiev.supersergey.deputysearch.inputparser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Created by supersergey on 21.04.18.
 */
@Data
@Entity
@NoArgsConstructor
public class Company {
    private String name;
    @JsonIgnore
    @ManyToOne
    private InfoCard infoCard;
}
