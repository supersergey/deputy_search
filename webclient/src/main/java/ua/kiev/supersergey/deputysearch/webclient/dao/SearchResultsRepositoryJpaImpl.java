package ua.kiev.supersergey.deputysearch.webclient.dao;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.deputysearch.webclient.dao.entity.SearchResultQueryResult;
import ua.kiev.supersergey.deputysearch.webclient.dao.querybuilder.QueryBuilder;
import ua.kiev.supersergey.deputysearch.webclient.dao.querybuilder.SearchResultFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.stream.Stream;

@Repository
public class SearchResultsRepositoryJpaImpl implements SearchResultsRepositoryJpa {
    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public Stream<SearchResultQueryResult> fetchDataBySearchFilter(SearchResultFilter searchResultFilter) {
        Query query = em.createNativeQuery(
                QueryBuilder.newBuilder(false)
                        .searchCriteria(searchResultFilter.getSearchCriteria())
                        .sortCriteria(searchResultFilter.getSort())
                        .build());
        query.setFirstResult((searchResultFilter.getPage() - 1) * searchResultFilter.getSize());
        query.setMaxResults(searchResultFilter.getSize());
        return query
                .unwrap(NativeQuery.class)
                .setResultTransformer(new AliasToBeanResultTransformer(SearchResultQueryResult.class))
                .getResultList().stream();
    }

    @Override
    public Long fetchCountBySearchFilter(SearchResultFilter searchResultFilter) {
        Query query = em.createNativeQuery(
                QueryBuilder.newBuilder(true)
                        .searchCriteria(searchResultFilter.getSearchCriteria())
                        .sortCriteria(searchResultFilter.getSort())
                        .build());
        return ((BigInteger) query.getSingleResult()).longValue();
    }

}
