package ua.kiev.supersergey.deputysearch.import_genius_client.parser.validator;

import org.springframework.util.StringUtils;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.deputysearch.commonlib.filter.CompanyNameTransformer;

import java.util.Arrays;
import java.util.function.Predicate;

public class SearchResultValidator implements Predicate<SearchResult> {
    public boolean test(SearchResult searchResult) {
        if (searchResult == null) {
            return false;
        }
        String strippedCompanyName = CompanyNameTransformer.transform(searchResult.getCompany().getName());
        boolean result = false;
        if (!StringUtils.isEmpty(searchResult.getSenderName())) {
            result = searchResult.getSenderName().toLowerCase().contains(strippedCompanyName.toLowerCase());
        }
        if (!StringUtils.isEmpty(searchResult.getRecepientName())) {
            result = result || searchResult.getRecepientName().toLowerCase().contains(strippedCompanyName.toLowerCase());
        }
        return result;
    }
}
