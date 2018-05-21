package ua.kiev.supersergey.webclient.deputysearch.google_search_client.filter;

/**
 * Created by supersergey on 20.05.18.
 */
public class CompanyNameTransformer {
    public static String transform(String name) {
        name = name.replace("''", "\"");
        int firstQuoteIndex = name.indexOf("\"");
        int lastQuoteIndex = name.lastIndexOf("\"");
        if (firstQuoteIndex == -1 || firstQuoteIndex == lastQuoteIndex) {
            return name;
        }
        do {
            int nextQouteIndex = name.indexOf("\"", firstQuoteIndex + 1);
            if (nextQouteIndex == lastQuoteIndex) {
                break;
            } else {
                firstQuoteIndex = nextQouteIndex;
            }
        } while (true);
        return name.substring(firstQuoteIndex + 1, lastQuoteIndex);
    }
}
