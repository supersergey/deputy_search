package ua.kiev.supersergey.deputysearch.commonlib.filter;

/**
 * Created by supersergey on 20.05.18.
 */
public class CompanyNameTransformer {

    private static final String[] prefixes = {
            "ТОВ", "ТЗОВ", "ОАО", "ЗАО", "ООО", "ЧП", "КП", "ДП", "РТЦ", "СХК", "ПП", "АБ"};
    private static final String[] suffixes = {
            "Лтд", "Гмбх", "Лимитед", "Лімітед", "ltd", "gmbh"};

    public static String transform(String name) {
        for (String prefix : prefixes) {
            name = name.replaceAll("(?iu)" + prefix + "\\s+", "");
        }
        for (String suffix : suffixes) {
            name = name.replaceAll("(?iu)" + suffix + "\\.?", "");
        }
        name = name.replace("''", "\"");
        int firstQuoteIndex = name.indexOf("\"");
        int lastQuoteIndex = name.lastIndexOf("\"");
        if (firstQuoteIndex == -1 || firstQuoteIndex == lastQuoteIndex) {
            return name.trim();
        }
        do {
            int nextQouteIndex = name.indexOf("\"", firstQuoteIndex + 1);
            if (nextQouteIndex == lastQuoteIndex) {
                break;
            } else {
                firstQuoteIndex = nextQouteIndex;
            }
        } while (true);
        return name.substring(firstQuoteIndex + 1, lastQuoteIndex).trim();
    }
}
