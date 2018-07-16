package ua.kiev.supersergey.deputysearch.inputparser.json.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Paginator {
    private int count;
    @JsonProperty("per_page")
    private int perPage;
    @JsonProperty("num_pages")
    private int numPages;
}
