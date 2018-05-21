package ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by supersergey on 21.04.18.
 */
@Entity
@Table(name = "company")
@NoArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Transient
    private String infocardGuid;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CompanyStatus status;
    @Column(name = "url_time_stamp")
    private Date urlTimeStamp;
    @ManyToOne
    @JoinColumn(name = "info_card")
    private InfoCard infoCard;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<SearchResult> searchResults;

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

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", infocardGuid='" + infocardGuid + '\'' +
                ", status=" + status +
                ", urlTimeStamp=" + urlTimeStamp +
                '}';
    }
}
