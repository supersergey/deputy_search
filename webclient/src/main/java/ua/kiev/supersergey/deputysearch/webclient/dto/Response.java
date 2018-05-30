package ua.kiev.supersergey.deputysearch.webclient.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by supersergey on 23.05.18.
 */
@Data
@Builder
public class Response {
    private List<SearchResultDto> rows;
    private int page;
    private long total;
    private long records;
}
