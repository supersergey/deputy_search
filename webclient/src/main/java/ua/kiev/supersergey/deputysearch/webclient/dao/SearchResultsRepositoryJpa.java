package ua.kiev.supersergey.deputysearch.webclient.dao;

import org.springframework.stereotype.Repository;
import ua.kiev.supersergey.deputysearch.webclient.dao.entity.SearchResultQueryResult;
import ua.kiev.supersergey.deputysearch.webclient.dao.querybuilder.SearchResultFilter;

import java.util.stream.Stream;

@Repository
public interface SearchResultsRepositoryJpa {
    Stream<SearchResultQueryResult> fetchDataBySearchFilter(SearchResultFilter searchResultFilter);
    Long fetchCountBySearchFilter(SearchResultFilter searchResultFilter);
}
