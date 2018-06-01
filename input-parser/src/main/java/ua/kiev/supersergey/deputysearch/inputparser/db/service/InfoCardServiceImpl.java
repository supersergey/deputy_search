package ua.kiev.supersergey.deputysearch.inputparser.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.supersergey.deputysearch.commonlib.dao.InfoCardRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public InfoCard save(InfoCard newEntry) {
        Iterable<InfoCard> entries = repository.findInfoCardByFirstNameIgnoreCaseAndPatronymicIgnoreCaseAndLastNameIgnoreCase(newEntry.getFirstName(), newEntry.getPatronymic(), newEntry.getLastName());
        if (entries.iterator().hasNext()) {
            InfoCard existingEntry = entries.iterator().next();
//            newEntry.getCompanies().forEach(i -> i.setInfoCard(existingEntry));
            existingEntry.getCompanies().addAll(newEntry.getCompanies().stream().filter(i -> !existingEntry.getCompanies().contains(i)).collect(Collectors.toList()));
            return existingEntry;
        } else {
            return repository.save(newEntry);
        }
    }

    @Override
    @Transactional
    public void saveAll(List<InfoCard> infoCards) {
        infoCards.forEach(newInfoCard -> {
                    Optional<InfoCard> existingInfoCard = repository.findById(newInfoCard.getGuid());
                    if (existingInfoCard.isPresent()) {
                        addNewCompaniesToExistingInfoCard(newInfoCard, existingInfoCard.get());
                    } else {
                        repository.save(newInfoCard);
                    }
                }
            );
        }

    private void addNewCompaniesToExistingInfoCard(InfoCard newInfoCard, InfoCard existingInfoCard) {
        List<Company> existingCompanies = existingInfoCard.getCompanies();
        List<Company> newCompanies = newInfoCard.getCompanies().stream()
                .filter(nic -> !existingCompanies.contains(nic))
                .collect(Collectors.toList());
        existingCompanies.addAll(newCompanies);
//        existingCompanies.forEach(c -> c.setInfoCard(existingInfoCard));
    }
}
