package ua.kiev.supersergey.deputysearch.commonlib.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * Created by supersergey on 21.04.18.
 */
@Entity
@Table(name = "company")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @Column(name = "uuid")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String uuid;
    private String name;
    @Transient
    private String infocardGuid;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CompanyStatus status;
    @Column(name = "url_time_stamp")
    private Date urlTimeStamp;
    @ManyToMany(mappedBy = "companies")
    @Builder.Default
    private List<InfoCard> infoCards = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @Builder.Default
    private List<SearchResult> searchResults = new ArrayList<>();

    @Override
    public String toString() {
        return "Company{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", infocardGuid='" + infocardGuid + '\'' +
                ", status=" + status +
                ", urlTimeStamp=" + urlTimeStamp +
                '}';
    }
}
