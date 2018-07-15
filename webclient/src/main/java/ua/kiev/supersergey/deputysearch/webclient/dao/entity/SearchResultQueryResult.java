package ua.kiev.supersergey.deputysearch.webclient.dao.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class SearchResultQueryResult {
    private long id;
    private String recepientName;
    private String recepientAddress;
    private String senderName;
    private String senderAddress;
    private String companyName;
    private String firstName;
    private String patronymic;
    private String lastName;
    private String beneficiaryGuid;
    private String companyUuid;
    private Integer searchResultId;
    private String freightDesc;
    private String url;
}
