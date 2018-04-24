package ua.kiev.supersergey.deputysearch.inputparser.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.kiev.supersergey.deputysearch.inputparser.db.dao.InfoCardRepository;
import ua.kiev.supersergey.deputysearch.inputparser.entity.InfoCard;

import java.util.Optional;

/**
 * Created by supersergey on 23.04.18.
 */
@Service
public class InfoCardServiceImpl implements InfoCardService {

    private InfoCardRepository repository;

    @Autowired
    public InfoCardServiceImpl(InfoCardRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Mono<InfoCard> save(InfoCard infoCard) {
        return Mono.just(repository.save(infoCard));
    }
}
