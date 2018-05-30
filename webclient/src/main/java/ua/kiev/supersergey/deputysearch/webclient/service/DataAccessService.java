package ua.kiev.supersergey.deputysearch.webclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.supersergey.deputysearch.commonlib.dao.SearchResultsRepository;
import ua.kiev.supersergey.deputysearch.commonlib.dao.SearchResultsRepositoryJpa;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.deputysearch.commonlib.entity.filter.SearchResultFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.stream.Stream;

/**
 * Created by supersergey on 21.05.18.
 */
@Service
public class DataAccessService {
    private SearchResultsRepository searchResultsRepository;
    private SearchResultsRepositoryJpa searchResultsRepositoryJpa;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public DataAccessService(SearchResultsRepository searchResultsRepository, SearchResultsRepositoryJpa searchResultsRepositoryJpa) {
        this.searchResultsRepository = searchResultsRepository;
        this.searchResultsRepositoryJpa = searchResultsRepositoryJpa;
    }

    public Long fetchNonEmptySearchResultsCount(SearchResultFilter searchResultFilter) {
        return searchResultsRepositoryJpa.fetchCountBySearchFilter(searchResultFilter);
    }

    @Transactional
    public Stream<SearchResult> fetchNonEmptySearchResults(SearchResultFilter searchResultFilter) {
        return searchResultsRepositoryJpa.fetchDataBySearchFilter(searchResultFilter);
    }
}
