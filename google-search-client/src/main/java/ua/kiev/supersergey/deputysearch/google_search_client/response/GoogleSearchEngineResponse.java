package ua.kiev.supersergey.deputysearch.google_search_client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class GoogleSearchEngineResponse {
    private Queries queries;
    private List<Item> items;

    public boolean hasNext() {
        return Optional.ofNullable(queries.getNextPage()).isPresent();
    }
}
