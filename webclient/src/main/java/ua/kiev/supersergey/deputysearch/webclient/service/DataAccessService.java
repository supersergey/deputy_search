package ua.kiev.supersergey.deputysearch.webclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.supersergey.deputysearch.webclient.dao.SearchResultsRepositoryJpa;
import ua.kiev.supersergey.deputysearch.webclient.dao.entity.SearchResultQueryResult;
import ua.kiev.supersergey.deputysearch.webclient.dao.querybuilder.SearchResultFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.stream.Stream;

/**
 * Created by supersergey on 21.05.18.
 */
@Service
public class DataAccessService {
    private SearchResultsRepositoryJpa searchResultsRepositoryJpa;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public DataAccessService(SearchResultsRepositoryJpa searchResultsRepositoryJpa) {
        this.searchResultsRepositoryJpa = searchResultsRepositoryJpa;
    }

    public Long fetchNonEmptySearchResultsCount(SearchResultFilter searchResultFilter) {
        return searchResultsRepositoryJpa.fetchCountBySearchFilter(searchResultFilter);
    }

    @Transactional
    public Stream<SearchResultQueryResult> fetchNonEmptySearchResults(SearchResultFilter searchResultFilter) {
        return searchResultsRepositoryJpa.fetchDataBySearchFilter(searchResultFilter);
    }
}
