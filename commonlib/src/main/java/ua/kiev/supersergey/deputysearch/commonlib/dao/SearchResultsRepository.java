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
    @Query(value = BASE_QUERY)
    List<SearchResult> fetchNonEmptySearchResults(Pageable pageRequest);
    @Query(value = BASE_QUERY + " AND lower(sr.company.infoCard.lastName) = lower(:lastName) ")
    List<SearchResult> fetchNonEmptySearchResults_SearchByLastName(Pageable pageRequest, String lastName);
    @Query(value = BASE_QUERY + " AND (" +
            "lower(sr.company.name) like lower(concat('%', :companyName, '%')) OR " +
            "lower(sr.senderName) like lower(concat('%', :companyName, '%')) OR " +
            "lower(sr.recepientName) like lower(concat('%', :companyName, '%'))" +
            ")"
    )
    List<SearchResult> fetchNonEmptySearchResults_SearchByCompany(Pageable pageRequest, String companyName);
    @Query(value = BASE_QUERY + " AND lower(sr.freightDesc) like lower(concat('%', freightDesc, '%'))")
    List<SearchResult> fetchNonEmptySearchResults_SearchByFreightDesc(Pageable pageRequest, String freightDesc);
    @Query(value = "select count(sr) from SearchResult sr where sr.status='PARSED_OK'")
    int fetchNonEmptySearchResultsCount();
}
