package ua.kiev.supersergey.deputysearch.inputparser.db.service;

import org.springframework.transaction.annotation.Transactional;
import ua.kiev.supersergey.deputysearch.inputparser.db.entity.InfoCard;

import java.util.List;

/**
 * Created by supersergey on 23.04.18.
 */
public interface InfoCardService {
    @Transactional
    InfoCard save(InfoCard infoCard);

    @Transactional
    void saveAll(List<InfoCard> infoCards);
}
