package ua.kiev.supersergey.webclient.contoller;

import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;

import java.util.Map;
import java.util.Optional;

public class LandingPageControllerHelper {
    public static SearchResultFilter decodeParams(Map<String, String> params) {
        return SearchResultFilter.builder()
                .page(parsePage(params))
                .size(parseSize(params))
                .sort(parseSortCriteria(params))
                .searchCriteria(parseSearchCriteria(params))
                .build();
    }

    private static SearchResultFilter.SearchCriteria parseSearchCriteria(Map<String, String> params) {
        return SearchResultFilter.SearchCriteria.builder()
                .isSearchEnabled(parseIsSearchEnabled(params))
                .searchKey(parseSearchKey(params))
                .build();
    }

    private static Pair<DataColumn, String> parseSearchKey(Map<String, String> params) {
        for (DataColumn dc : DataColumn.values()) {
            if (params.containsKey(dc.getWebName())) {
                return Pair.of(dc, params.get(dc.getWebName()));
            }
        }
        return null;
    }

    private static Sort parseSortCriteria(Map<String, String> params) {
        Sort result = Sort.unsorted();
        if (params.containsKey("sidx")) {
            DataColumn dc = DataColumn.findByWebName(params.get("sidx"));
            if (dc != null) {
                result = Sort.by(parseDirection(params), dc.getDbName());
            }
        }
        return result;
    }

    private static Sort.Direction parseDirection(Map<String, String> params) {
        if (params.containsKey("sord") && params.get("sord").equals("desc")) {
            return Sort.Direction.DESC;
        } else if (params.containsKey("sord") && params.get("sord").equals("asc")) {
            return Sort.Direction.ASC;
        } else {
            return Sort.DEFAULT_DIRECTION;
        }
    }

    private static int parseSize(Map<String, String> params) {
        return Integer.valueOf(Optional.ofNullable(params.get("size")).orElse("30"));
    }

    private static int parsePage(Map<String, String> params) {
        return Integer.valueOf(Optional.ofNullable(params.get("page")).orElse("0"));
    }

    private static boolean parseIsSearchEnabled(Map<String, String> params) {
        return Boolean.valueOf(Optional.ofNullable(params.get("_search")).orElse("true"));
    }
}
