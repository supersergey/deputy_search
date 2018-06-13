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
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "infocard_company",
            joinColumns = {@JoinColumn(name = "infocard_guid")},
            inverseJoinColumns = {@JoinColumn(name = "company_uuid")}
    )
    @Builder.Default
    private Set<Company> companies = new HashSet<>();
}
