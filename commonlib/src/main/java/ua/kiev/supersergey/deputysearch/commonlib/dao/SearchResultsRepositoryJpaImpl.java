package ua.kiev.supersergey.deputysearch.commonlib.dao;

import org.springframework.stereotype.Repository;
import ua.kiev.supersergey.deputysearch.commonlib.dao.helper.QueryBuilder;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.deputysearch.commonlib.entity.filter.SearchResultFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.stream.Stream;

@Repository
public class SearchResultsRepositoryJpaImpl implements SearchResultsRepositoryJpa {
    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public Stream<SearchResult> fetchDataBySearchFilter(SearchResultFilter searchResultFilter) {
        Query query = em.createQuery(
                QueryBuilder.newBuilder(false)
                        .searchCriteria(searchResultFilter.getSearchCriteria())
                        .sortCriteria(searchResultFilter.getSort())
                        .build(),
                SearchResult.class);
        query.setFirstResult((searchResultFilter.getPage() - 1) * searchResultFilter.getSize());
        query.setMaxResults(searchResultFilter.getSize());
        return query.getResultList().stream();
    }

    @Override
    public Long fetchCountBySearchFilter(SearchResultFilter searchResultFilter) {
        Query query = em.createQuery(
                QueryBuilder.newBuilder(true)
                        .searchCriteria(searchResultFilter.getSearchCriteria())
                        .sortCriteria(searchResultFilter.getSort())
                        .build(), Long.class);
        return (Long) query.getSingleResult();
    }

}
