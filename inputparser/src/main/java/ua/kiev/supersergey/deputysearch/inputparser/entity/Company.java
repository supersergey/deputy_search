package ua.kiev.supersergey.deputysearch.inputparser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by supersergey on 21.04.18.
 */
@Data
@Entity
@NoArgsConstructor
public class Company {
    @Id
    private String name;
    @JsonIgnore
    @ManyToOne
    private InfoCard infoCard;
}
