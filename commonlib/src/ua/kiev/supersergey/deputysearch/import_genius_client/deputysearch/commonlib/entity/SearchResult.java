package ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by supersergey on 17.05.18.
 */
@Entity
@Table(name = "search_result")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchResult {
    @Id
    private long id;
    @Column(name = "recepient_name")
    private String recepientName;
    @Column(name = "recepient_address")
    private String recepientAddress;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "sender_address")
    private String senderAddress;
    @Column(name = "freight_desc")
    private String freightDesc;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
