package ua.kiev.supersergey.deputysearch.import_genius_client.parser.validator;

import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.deputysearch.commonlib.filter.CompanyNameTransformer;

public class SearchResultValidator {
    public static boolean isValid(SearchResult searchResult, String companyName) {
        String strippedCompanyName = CompanyNameTransformer.transform(companyName);
        return searchResult != null
                &&
                (
                        searchResult.getSenderName().toLowerCase().contains(strippedCompanyName.toLowerCase())
                        ||
                        searchResult.getRecepientName().toLowerCase().contains(strippedCompanyName.toLowerCase())
                );
    }
}
