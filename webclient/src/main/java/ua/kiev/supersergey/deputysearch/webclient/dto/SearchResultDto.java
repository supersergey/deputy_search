package ua.kiev.supersergey.deputysearch.webclient.dto;

import lombok.Data;

/**
 * Created by supersergey on 22.05.18.
 */
@Data
public class SearchResultDto {
    private String beneficiary;
    private String beneficiaryId;
    private String company;
    private long companyId;
    private String freightDesc;
    private String sender;
    private String recepient;
    private String url;
}
