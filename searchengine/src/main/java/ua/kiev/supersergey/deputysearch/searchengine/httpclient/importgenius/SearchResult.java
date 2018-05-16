package ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by supersergey on 17.05.18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
}
