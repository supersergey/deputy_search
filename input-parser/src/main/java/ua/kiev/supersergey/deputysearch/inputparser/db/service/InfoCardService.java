package ua.kiev.supersergey.deputysearch.inputparser.db.service;

import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;

import java.util.List;

/**
 * Created by supersergey on 23.04.18.
 */
public interface InfoCardService {
    InfoCard save(InfoCard infoCard);
    Iterable<InfoCard> findByFirstNamePatroNymicLastName(String firstName, String patronymic, String lastName);
    void saveAll(List<InfoCard> infoCards);
}
