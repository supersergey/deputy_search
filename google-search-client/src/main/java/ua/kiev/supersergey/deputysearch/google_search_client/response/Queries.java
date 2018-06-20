package ua.kiev.supersergey.deputysearch.google_search_client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Queries {
    private NextPage[] nextPage;
}
