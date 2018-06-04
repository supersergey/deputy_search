package ua.kiev.supersergey.deputysearch.commonlib.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.*;

/**
 * Created by supersergey on 20.04.18.
 */
@Entity
@Table(name = "info_card")
public class InfoCard {
    @Id
    private String guid;
    @JsonProperty("last_name")
    private String lastName;
    private String patronymic;
    @JsonProperty("created_date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;
    private String url;
    @JsonProperty("first_name")
    private String firstName;
    private Date parsedDate;

    public InfoCard() {
    }

    public InfoCard(String guid, String lastName, String patronymic, Date createdDate, String url, String firstName, Date parsedDate, Set<Company> companies) {
        this.guid = guid;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.createdDate = createdDate;
        this.url = url;
        this.firstName = firstName;
        this.parsedDate = parsedDate;
        this.companies = companies;
    }

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "infocard_company",
            joinColumns = {@JoinColumn(name = "infocard_guid")},
            inverseJoinColumns = {@JoinColumn(name = "company_uuid")}
    )
    private Set<Company> companies = new HashSet<>();

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getParsedDate() {
        return parsedDate;
    }

    public void setParsedDate(Date parsedDate) {
        this.parsedDate = parsedDate;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "InfoCard{" +
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
