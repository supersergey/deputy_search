package ua.kiev.supersergey.deputysearch.inputparser.json.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kiev.supersergey.deputysearch.inputparser.json.deserializer.DeclarationsResponseDeserializer;

import java.util.List;

@JsonDeserialize(using = DeclarationsResponseDeserializer.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeclarationsResponse {
    private Paginator paginator;
    private List<InfoCardJson> infocards;
}
