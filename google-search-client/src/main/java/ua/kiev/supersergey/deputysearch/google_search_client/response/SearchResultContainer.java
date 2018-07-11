package ua.kiev.supersergey.deputysearch.google_search_client.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchResultContainer {
    private List<Item> items;
    private boolean isFinishedSuccessfully;
    private int errorCode;
}
