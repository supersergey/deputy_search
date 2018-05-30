package ua.kiev.supersergey.deputysearch.commonlib.entity;

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
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "name")
    private String name;
    @Transient
    private String infocardGuid;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CompanyStatus status;
    @Column(name = "url_time_stamp")
    private Date urlTimeStamp;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(
            name = "infocard_company",
            joinColumns = {@JoinColumn(name = "company_uuid")},
            inverseJoinColumns = {@JoinColumn(name = "infocard_guid")}
    )
    private List<InfoCard> infoCards;
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
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", infocardGuid='" + infocardGuid + '\'' +
                ", status=" + status +
                ", urlTimeStamp=" + urlTimeStamp +
                '}';
    }
}
