package ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by supersergey on 21.04.18.
 */
@Entity
@NoArgsConstructor
@Data
public class Company {
    @Id
    private long id;
    @Column(name="name")
    private String name;
    @Transient
    private String infocardGuid;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private CompanyStatus status;
    @Column(name = "error_message")
    private String errorMessage;
    @Column(name = "url_time_stamp")
    private Date urlTimeStamp;
    @Column(name = "parse_time_stamp")
    private Date parseTimeStamp;
    @ManyToOne
    @JoinColumn(name = "info_card")
    private InfoCard infoCard;
    @OneToMany
    private List<SearchResult> searchResult;

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
