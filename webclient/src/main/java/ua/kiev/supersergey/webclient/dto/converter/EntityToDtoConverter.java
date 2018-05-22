package ua.kiev.supersergey.webclient.dto.converter;

import org.apache.logging.log4j.util.Strings;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.webclient.dto.SearchResultDto;

/**
 * Created by supersergey on 22.05.18.
 */
public class EntityToDtoConverter {
    public static SearchResultDto toDto(SearchResult searchResult) {
        SearchResultDto result = new SearchResultDto();
        if (searchResult == null) {
            return result;
        }
        result.setBeneficiary(String.join(" ",
                searchResult.getCompany().getInfoCard().getFirstName(),
                searchResult.getCompany().getInfoCard().getPatronymic(),
                searchResult.getCompany().getInfoCard().getLastName()
        ));
        result.setBeneficiaryId(searchResult.getCompany().getInfoCard().getGuid());
        result.setCompany(searchResult.getCompany().getName());
        result.setCompanyId(searchResult.getCompany().getId());
        result.setRecepient(String.join(" ",
                searchResult.getRecepientName(),
                searchResult.getRecepientAddress()));
        result.setSender(String.join(" ",
                searchResult.getSenderName(),
                searchResult.getSenderAddress()));
        result.setFreightDesc(searchResult.getFreightDesc());
        result.setUrl(searchResult.getUrl());
        return result;
    }
}
