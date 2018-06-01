package ua.kiev.supersergey.deputysearch.webclient.dao.querybuilder;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.util.Set;

@Data
@Builder
public class SearchResultFilter {
    private int page;
    private int size;
    private SortCriteria sort;
    private SearchCriteria searchCriteria;

    @Data
    @Builder
    public static class SearchCriteria {
        private boolean isEnabled;
        private Set<Pair<DataColumn, String>> keys;
    }

    @Data
    @Builder
    public static class SortCriteria {
        private boolean isEnabled;
        private DataColumn column;
        private SortDirection direction;
    }

    public enum SortDirection {
        ASC("ASC"), DESC("DESC");
        private String value;

        SortDirection(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
