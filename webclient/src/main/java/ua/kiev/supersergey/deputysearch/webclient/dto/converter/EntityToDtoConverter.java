package ua.kiev.supersergey.deputysearch.webclient.dto.converter;

import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.deputysearch.webclient.dao.entity.SearchResultQueryResult;
import ua.kiev.supersergey.deputysearch.webclient.dto.SearchResultDto;

import java.util.stream.Collectors;

/**
 * Created by supersergey on 22.05.18.
 */
public class EntityToDtoConverter {
    public static SearchResultDto toDto(SearchResultQueryResult searchResult) {
        SearchResultDto result = new SearchResultDto();
        if (searchResult == null) {
            return result;
        }
        result.setId(searchResult.getId());
        result.setBeneficiary(
                String.join(" ", searchResult.getLastName(), searchResult.getFirstName(), searchResult.getPatronymic()));
        // todo add urls
        result.setCompany(searchResult.getCompanyName());
        result.setCompanyUuid(searchResult.getCompanyUuid());
        result.setRecepient(String.join("\n",
                searchResult.getRecepientName(),
                searchResult.getRecepientAddress()));
        result.setSender(String.join("\n",
                searchResult.getSenderName(),
                searchResult.getSenderAddress()));
        result.setFreightDesc(cutString(searchResult.getFreightDesc(), 100));
        result.setUrl(searchResult.getUrl());
        return result;
    }

    private static String buildUrl(String url) {
        return String.format("... <br><a href=\"%s\" target=\"_blank\">Подробнее >>></a>", url);
    }

    private static String cutString(String s, int maxLength) {
        return s.length() > maxLength ? s.substring(0, maxLength) : s;
    }
}
