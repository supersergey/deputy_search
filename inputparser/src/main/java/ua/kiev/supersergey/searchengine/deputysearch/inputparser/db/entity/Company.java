package ua.kiev.supersergey.searchengine.deputysearch.inputparser.db.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by supersergey on 21.04.18.
 */
@Entity(name = "company")
@NoArgsConstructor
@Data
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Transient
    private String infocardGuid;
    @ManyToOne
    @JoinColumn(name = "info_card")
    private InfoCard infoCard;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        return name.equals(company.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
