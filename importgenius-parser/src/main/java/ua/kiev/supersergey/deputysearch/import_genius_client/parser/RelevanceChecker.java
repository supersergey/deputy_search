package ua.kiev.supersergey.deputysearch.import_genius_client.parser;

import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;

public class RelevanceChecker {
    public static boolean isResultRelevant(SearchResult searchResult) {
        String senderName = searchResult.getSenderName().toLowerCase();
        String recepientName = searchResult.getRecepientName().toLowerCase();
        String companyName = searchResult.getCompany().getName().toLowerCase();
        return senderName.contains(companyName) || recepientName.contains(companyName);
    }
}
