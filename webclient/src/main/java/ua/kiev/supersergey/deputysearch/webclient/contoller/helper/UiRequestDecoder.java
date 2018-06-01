package ua.kiev.supersergey.deputysearch.webclient.contoller.helper;

import org.springframework.data.util.Pair;
import org.springframework.util.CollectionUtils;
import ua.kiev.supersergey.deputysearch.webclient.dao.querybuilder.DataColumn;
import ua.kiev.supersergey.deputysearch.webclient.dao.querybuilder.SearchResultFilter;
import ua.kiev.supersergey.deputysearch.webclient.exception.InvalidDataColumnException;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UiRequestDecoder {
    public static SearchResultFilter decodeParams(Map<String, String> params) {
        return SearchResultFilter.builder()
                .page(parsePage(params))
                .size(parseSize(params))
                .sort(parseSortCriteria(params))
                .searchCriteria(parseSearchCriteria(params))
                .build();
    }

    private static int parsePage(Map<String, String> params) {
        return Integer.valueOf(Optional.ofNullable(params.get("page")).orElse("0"));
    }

    private static int parseSize(Map<String, String> params) {
        return Integer.valueOf(Optional.ofNullable(params.get("size")).orElse("30"));
    }

    private static SearchResultFilter.SortCriteria parseSortCriteria(Map<String, String> params) {
        try {
            if (!params.containsKey("sidx")) {
                throw new InvalidDataColumnException("sidx");
            } else {
                DataColumn dc = Optional
                        .ofNullable(DataColumn.findByWebName(params.get("sidx")))
                        .orElseThrow(() -> new InvalidDataColumnException("sidx"));
                if (dc == DataColumn.ID) {
                    throw new InvalidDataColumnException("id");
                }
                SearchResultFilter.SortDirection direction = Optional
                        .ofNullable(parseSortDirection(params))
                        .orElseThrow(() -> new InvalidDataColumnException("sord"));
                return SearchResultFilter.SortCriteria.builder()
                        .isEnabled(true)
                        .column(dc)
                        .direction(direction)
                        .build();
            }
        } catch (InvalidDataColumnException ex) {
            return SearchResultFilter.SortCriteria.builder()
                    .isEnabled(false)
                    .build();
        }
    }

    private static SearchResultFilter.SearchCriteria parseSearchCriteria(Map<String, String> params) {
        Set<Pair<DataColumn, String>> searchKeys = parseSearchKeys(params);
        return CollectionUtils.isEmpty(searchKeys) ?
                SearchResultFilter.SearchCriteria.builder()
                        .isEnabled(false)
                        .build()
                :
                SearchResultFilter.SearchCriteria.builder()
                        .isEnabled(parseIsSearchEnabled(params))
                        .keys(searchKeys)
                        .build();
    }

    private static Set<Pair<DataColumn, String>> parseSearchKeys(Map<String, String> params) {
        return params.entrySet().stream()
                .map(param -> DataColumn.findByWebName(param.getKey()))
                .filter(Objects::nonNull)
                .map(dc -> Pair.of(dc, params.get(dc.getWebName())))
                .collect(Collectors.toSet());
    }


    private static SearchResultFilter.SortDirection parseSortDirection(Map<String, String> params) {
        if (params.containsKey("sord") && params.get("sord").equals("desc")) {
            return SearchResultFilter.SortDirection.DESC;
        } else if (params.containsKey("sord") && params.get("sord").equals("asc")) {
            return SearchResultFilter.SortDirection.ASC;
        } else {
            return null;
        }
    }

    private static boolean parseIsSearchEnabled(Map<String, String> params) {
        return params.containsKey("_search") && params.get("_search").equals("true");
    }
}
