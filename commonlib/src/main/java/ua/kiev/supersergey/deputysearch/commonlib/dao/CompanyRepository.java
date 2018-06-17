package ua.kiev.supersergey.deputysearch.commonlib.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;

import java.util.List;
import java.util.Set;

/**
 * Created by supersergey on 17.05.18.
 */
@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long>{
    @Query(value = "select c from Company c where c.status is NULL")
    List<Company> findCompaniesUnprocessedByGoogle(Pageable pageable);
    @Query
    Company findFirstByNameIgnoreCase(String name);
    @Query
    List<Company> findByNameIn(Set<String> names);
}
