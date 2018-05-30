package ua.kiev.supersergey.deputysearch.commonlib.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by supersergey on 17.05.18.
 */
@Entity
@Table(name = "search_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private SearchResultStatus status;
    @Column(name = "url")
    private String url;
    @Column(name = "parse_time_stamp")
    private Date parseTimeStamp;
    @Column(name = "error_message")
    private String errorMessage;
    @ManyToOne
    @JoinColumn(name = "company_uuid")
    private Company company;

    @Override
    public String toString() {
        return "SearchResult{" +
                "id=" + id +
                ", recepientName='" + recepientName + '\'' +
                ", recepientAddress='" + recepientAddress + '\'' +
                ", senderName='" + senderName + '\'' +
                ", senderAddress='" + senderAddress + '\'' +
                ", freightDesc='" + freightDesc + '\'' +
                ", status=" + status +
                ", url='" + url + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", parseTimeStamp=" + parseTimeStamp +
                '}';
    }
}
