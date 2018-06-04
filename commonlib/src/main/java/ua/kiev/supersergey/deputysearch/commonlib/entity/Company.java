package ua.kiev.supersergey.deputysearch.commonlib.entity;

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
    @ManyToMany(mappedBy = "companies", cascade = CascadeType.PERSIST)
    private Set<InfoCard> infoCards = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<SearchResult> searchResults = new ArrayList<>();

    public Company() {
    }

    public Company(String name, String infocardGuid, CompanyStatus status, Date urlTimeStamp, Set<InfoCard> infoCards, List<SearchResult> searchResults) {
        this.name = name;
        this.infocardGuid = infocardGuid;
        this.status = status;
        this.urlTimeStamp = urlTimeStamp;
        this.infoCards = infoCards;
        this.searchResults = searchResults;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfocardGuid() {
        return infocardGuid;
    }

    public void setInfocardGuid(String infocardGuid) {
        this.infocardGuid = infocardGuid;
    }

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }

    public Date getUrlTimeStamp() {
        return urlTimeStamp;
    }

    public void setUrlTimeStamp(Date urlTimeStamp) {
        this.urlTimeStamp = urlTimeStamp;
    }

    public Set<InfoCard> getInfoCards() {
        return infoCards;
    }

    public void setInfoCards(Set<InfoCard> infoCards) {
        this.infoCards = infoCards;
    }

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
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
