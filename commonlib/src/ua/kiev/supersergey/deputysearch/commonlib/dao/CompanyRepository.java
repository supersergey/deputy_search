package ua.kiev.supersergey.deputysearch.commonlib.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;

import java.util.List;

/**
 * Created by supersergey on 17.05.18.
 */
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long>{
    List<Company> findWhereCompanyStatusIsNotNull(Pageable pageable);
}
