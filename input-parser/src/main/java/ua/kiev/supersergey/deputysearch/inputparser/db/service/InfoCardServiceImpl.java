package ua.kiev.supersergey.deputysearch.inputparser.db.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.supersergey.deputysearch.commonlib.dao.CompanyRepository;
import ua.kiev.supersergey.deputysearch.commonlib.dao.InfoCardRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InfoCardServiceImpl implements InfoCardService {
    private InfoCardRepository icRepository;
    private CompanyRepository companyRepository;

    public InfoCardServiceImpl(InfoCardRepository icRepository, CompanyRepository companyRepository) {
        this.icRepository = icRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public InfoCard save(InfoCard newInfoCard) {
        log.info(String.format("Saving infocard: %s %s %s",
                newInfoCard.getFirstName(), newInfoCard.getPatronymic(), newInfoCard.getLastName()));
        InfoCard existingInfoCard = icRepository.findFirstByFirstNameAndPatronymicAndLastNameAllIgnoreCase(
                newInfoCard.getFirstName(), newInfoCard.getPatronymic(), newInfoCard.getLastName()
        );
        if (existingInfoCard == null) {
            List<Company> allDbCompanies = fetchCompaniesFromDb(newInfoCard.getCompanies());
            List<Company> toSave = InfoCardServiceHelper.mergeCompaniesByName(
                    newInfoCard.getCompanies(), allDbCompanies);
            newInfoCard.setCompanies(toSave);
            return icRepository.save(newInfoCard);
        } else {
            List<Company> companiesToSave = new ArrayList<>();
            List<Company> newInfoCardCompanies = newInfoCard.getCompanies();
            existingInfoCard.getCompanies().forEach(c -> {
                Company toSave = getNewOrDbEntity(c, existingInfoCard.getCompanies());
                companiesToSave.add(toSave);
            });

            List<Company> dbCompanies = fetchCompaniesFromDb(newInfoCardCompanies);
            newInfoCardCompanies.forEach(nic -> {
                Company toSave = getNewOrDbEntity(nic, dbCompanies);
                if (companiesToSave.stream().noneMatch(cs -> cs.getName().equals(toSave.getName()))) {
                    companiesToSave.add(toSave);
                }
            });
            existingInfoCard.setCompanies(companiesToSave);
            return icRepository.save(existingInfoCard);
        }
    }

    private Company getNewOrDbEntity(Company c, List<Company> companies) {
        return companies.stream()
                .filter(ec -> ec.getName().equals(c.getName()))
                .findFirst().orElse(c);
    }

    private List<Company> fetchCompaniesFromDb(List<Company> newCompanies) {
        return companyRepository.findByNameIn(newCompanies
                .stream()
                .map(Company::getName)
                .collect(Collectors.toSet())
        );
    }


    @Override
    public InfoCard findByFirstNamePatroNymicLastName(String firstName, String patronymic, String lastName) {
        return icRepository.findFirstByFirstNameAndPatronymicAndLastNameAllIgnoreCase(firstName, patronymic, lastName);
    }

    @Override
    @Transactional
    public void saveAll(List<InfoCard> infoCards) {
        infoCards.forEach(this::save);
    }
}
