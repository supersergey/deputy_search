package ua.kiev.supersergey.deputysearch.commonlib.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;

import java.util.List;

/**
 * Created by supersergey on 20.05.18.
 */
@Repository
public interface SearchResultsRepository extends PagingAndSortingRepository<SearchResult, Long> {
    String BASE_QUERY = "select sr from SearchResult sr where sr.status = 'PARSED_OK'";

    @Query(value = "select sr from SearchResult sr where sr.status is NULL")
    List<SearchResult> fetchNotParsedUrls(Pageable pageRequest);
}
