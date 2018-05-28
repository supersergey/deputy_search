package ua.kiev.supersergey.webclient.contoller;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;

@Data
@Builder
public class SearchResultFilter {
    private int page;
    private int size;
    private Sort sort;
    private SearchCriteria searchCriteria;

    @Data
    @Builder
    public static class SearchCriteria {
        private boolean isSearchEnabled;
        private Pair<DataColumn, String> searchKey;
    }
}
