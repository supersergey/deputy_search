package ua.kiev.supersergey.deputysearch.inputparser.db.service;

import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;

import java.util.List;

/**
 * Created by supersergey on 23.04.18.
 */
public interface InfoCardService {
    InfoCard save(InfoCard infoCard);
    void saveAll(List<InfoCard> infoCards);
}
