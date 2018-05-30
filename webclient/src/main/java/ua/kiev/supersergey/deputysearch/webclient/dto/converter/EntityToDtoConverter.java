package ua.kiev.supersergey.deputysearch.webclient.dto.converter;

import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.deputysearch.webclient.dto.SearchResultDto;

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
                searchResult.getCompany().getInfoCard().getLastName(),
                searchResult.getCompany().getInfoCard().getFirstName(),
                searchResult.getCompany().getInfoCard().getPatronymic()
        ));
        result.setBeneficiaryId(searchResult.getCompany().getInfoCard().getGuid());
        result.setCompany(searchResult.getCompany().getName());
        result.setCompanyId(searchResult.getCompany().getId());
        result.setRecepient(String.join("\n",
                searchResult.getRecepientName(),
                searchResult.getRecepientAddress()));
        result.setSender(String.join("\n",
                searchResult.getSenderName(),
                searchResult.getSenderAddress()));
        result.setFreightDesc(cutString(searchResult.getFreightDesc(), 100) + buildUrl(searchResult.getUrl()));

        return result;
    }

    private static String buildUrl(String url) {
        return String.format("... <br><a href=\"%s\" target=\"_blank\">Подробнее >>></a>", url);
    }

    private static String cutString(String s, int maxLength) {
        return s.length() > maxLength ? s.substring(0, maxLength) : s;
    }
}
