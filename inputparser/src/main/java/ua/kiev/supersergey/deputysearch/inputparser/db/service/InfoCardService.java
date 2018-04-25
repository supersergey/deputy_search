package ua.kiev.supersergey.deputysearch.inputparser.db.service;

import org.springframework.transaction.annotation.Transactional;
import ua.kiev.supersergey.deputysearch.inputparser.entity.InfoCard;

import java.util.List;
import java.util.Observer;

/**
 * Created by supersergey on 23.04.18.
 */
public interface InfoCardService {
    @Transactional
    InfoCard save(InfoCard infoCard);

    @Transactional
    void saveAll(List<InfoCard> infoCards);
}
