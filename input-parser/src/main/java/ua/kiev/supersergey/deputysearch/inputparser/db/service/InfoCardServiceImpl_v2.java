package ua.kiev.supersergey.deputysearch.inputparser.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ua.kiev.supersergey.deputysearch.commonlib.dao.CompanyRepository;
import ua.kiev.supersergey.deputysearch.commonlib.dao.InfoCardRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;

import java.util.List;
import java.util.UUID;

@Service
@Qualifier("InfoCardServiceImpl_v2")
public class InfoCardServiceImpl_v2 implements InfoCardService {
    private InfoCardRepository icRepository;
    private CompanyRepository companyRepository;

    public InfoCardServiceImpl_v2(InfoCardRepository icRepository, CompanyRepository companyRepository) {
        this.icRepository = icRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public InfoCard save(InfoCard newInfoCard) {
        icRepository.save(newInfoCard);
//        Iterable<InfoCard> existingInfoCards = icRepository.findInfoCardByFirstNameIgnoreCaseAndPatronymicIgnoreCaseAndLastNameIgnoreCase(
//                newInfoCard.getFirstName(), newInfoCard.getPatronymic(), newInfoCard.getLastName()
//        );
//        if (existingInfoCards.iterator().hasNext()) {
//            InfoCard existingInfoCard = existingInfoCards.iterator().next();
////            List<Company> existingCompanies = existingInfoCard.getCompanies();
//            newInfoCard.getCompanies().forEach(newInfoCardCompany -> {
//                        if (existingCompanies.stream()
//                                .map(Company::getName)
//                                .noneMatch(i -> i.equals(newInfoCardCompany.getName()))) {
//                            existingInfoCard.getCompanies().add(newInfoCardCompany);
//                            newInfoCardCompany.getInfoCards().add(existingInfoCard);
//                        }
//                    }
//            );
//            icRepository.save(existingInfoCard);
//            return existingInfoCard;
//        } else {
//            List<Company> newCompanies = newInfoCard.getCompanies();
//            newCompanies.forEach(nc -> {
//                List<Company> existingCompany = companyRepository.findCompanyByName(nc.getName());
//                if (!CollectionUtils.isEmpty(existingCompany)) {
//                    existingCompany.get(0).getInfoCards().add(newInfoCard);
//                    newInfoCard.getCompanies().add(existingCompany.get(0));
//                }
//            });
//            newInfoCard.setGuid(UUID.randomUUID().toString());
//            icRepository.save(newInfoCard);
//            return newInfoCard;
//        }
        return null;
    }

    @Override
    public Iterable<InfoCard> findByFirstNamePatroNymicLastName(String firstName, String patronymic, String lastName) {
        return icRepository.findInfoCardByFirstNameIgnoreCaseAndPatronymicIgnoreCaseAndLastNameIgnoreCase(firstName, patronymic, lastName);
    }

    @Override
    public void saveAll(List<InfoCard> infoCards) {

    }
}
