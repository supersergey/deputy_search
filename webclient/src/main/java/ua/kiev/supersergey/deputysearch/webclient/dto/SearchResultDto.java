package ua.kiev.supersergey.deputysearch.webclient.dto;

import lombok.Data;

/**
 * Created by supersergey on 22.05.18.
 */
@Data
public class SearchResultDto {
    private long id;
    private String beneficiary;
    private String beneficiaryGuid;
    private String company;
    private String companyUuid;
    private String freightDesc;
    private String sender;
    private String recepient;
    private String url;
}
