package ua.kiev.supersergey.searchengine.httpclient.googlesearch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by supersergey on 01.05.18.
 */
@Data
@NoArgsConstructor
public class Items {
    private List<Item> items;
}
