package ua.kiev.supersergey.deputysearch.commonlib.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;

import java.util.List;

/**
 * Created by supersergey on 23.04.18.
 */
@Repository
public interface InfoCardRepository extends PagingAndSortingRepository<InfoCard, String> {
    Iterable<InfoCard> findInfoCardByFirstNameIgnoreCaseAndPatronymicIgnoreCaseAndLastNameIgnoreCase(String firstName, String patronymic, String lastName);
    @Query(value = "select ic from InfoCard ic join ic.companies c join c.searchResults sr where sr.status='PARSED_OK'")
    List<InfoCard> findInfoCardsWithResults(Pageable pageRequest);
}
