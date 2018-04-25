package ua.kiev.supersergey.deputysearch.inputparser.db.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.supersergey.deputysearch.inputparser.db.entity.InfoCard;

/**
 * Created by supersergey on 23.04.18.
 */
@Repository
public interface InfoCardRepository extends PagingAndSortingRepository<InfoCard, String> {
    Iterable<InfoCard> findInfoCardByFirstNameIgnoreCaseAndPatronymicIgnoreCaseAndLastNameIgnoreCase(String firstName, String patronymic, String lastName);
}
