package ua.kiev.supersergey.deputysearch.commonlib.dao;

import org.springframework.stereotype.Repository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.deputysearch.commonlib.entity.filter.SearchResultFilter;

import java.util.stream.Stream;

@Repository
public interface SearchResultsRepositoryJpa {
    Stream<SearchResult> fetchDataBySearchFilter(SearchResultFilter searchResultFilter);
    Long fetchCountBySearchFilter(SearchResultFilter searchResultFilter);
}
