package ua.kiev.supersergey.deputysearch.inputparser.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.supersergey.deputysearch.commonlib.dao.InfoCardRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.InputParserApp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(classes = InputParserApp.class)
@RunWith(SpringRunner.class)
public class InfoCardServiceImplTest {

    @Autowired
    private InfoCardService infoCardService;

    @Autowired
    private InfoCardRepository infoCardRepository;

    @Test
    @Transactional
    public void testSimpleSave() {
        InfoCard ic = createInfoCard("1", "1", "1");
        Company c = createCompany("ABCD");
        ic.getCompanies().add(c);
        infoCardService.save(ic);
    }

    @Test
    @Transactional
    public void testSaveTwoCompanies() {
        InfoCard ic = createInfoCard("1", "1", "1");
        Company c1 = createCompany("c1");
        Company c2 = createCompany("c2");
        ic.getCompanies().add(c1);
        ic.getCompanies().add(c2);
        infoCardService.save(ic);
        InfoCard infocard = infoCardRepository.findFirstByFirstNameAndPatronymicAndLastNameAllIgnoreCase("1", "1", "1");
        assertNotNull(infocard);
        assertNotNull(infocard.getCompanies());
        assertEquals(2, infocard.getCompanies().size());
    }

    @Test
    @Transactional
    public void testSaveNewCompanyToExistingInfoCard() {
        InfoCard ic1 = createInfoCard("1", "1", "1");
        Company c1 = createCompany("c1");
        ic1.getCompanies().add(c1);
        infoCardService.save(ic1);

        InfoCard ic2 = createInfoCard("1", "1", "1");
        Company c2 = createCompany("c2");
        ic2.getCompanies().add(c2);
        infoCardService.save(ic2);

        InfoCard infoCardFromDb = infoCardRepository.findFirstByFirstNameAndPatronymicAndLastNameAllIgnoreCase("1", "1", "1");
        assertNotNull(infoCardFromDb);
        assertNotNull(infoCardFromDb.getCompanies());
        assertEquals(2, infoCardFromDb.getCompanies().size());
        assertEquals(new HashSet<>(Arrays.asList("c1", "c2")),
                infoCardFromDb.getCompanies().stream().map(Company::getName).collect(Collectors.toSet()));
    }

    @Test
    @Transactional
    public void testSaveExistingCompanyToExistingInfoCard() {
        InfoCard ic1 = createInfoCard("1", "1", "1");
        Company c1 = createCompany("c1");
        ic1.getCompanies().add(c1);
        infoCardService.save(ic1);

        InfoCard ic2 = createInfoCard("1", "1", "1");
        Company c2 = createCompany("c1");
        ic2.getCompanies().add(c2);
        infoCardService.save(ic2);

        InfoCard infoCardFromDb = infoCardRepository.findFirstByFirstNameAndPatronymicAndLastNameAllIgnoreCase("1", "1", "1");
        assertNotNull(infoCardFromDb);
        assertNotNull(infoCardFromDb.getCompanies());
        assertEquals(1, infoCardFromDb.getCompanies().size());
        assertEquals("c1", infoCardFromDb.getCompanies().get(0).getName());
    }

    @Test
    @Transactional
    public void testSaveMixedCompaniesToExistingInfoCard() {
        InfoCard ic1 = createInfoCard("1", "1", "1");
        Company c1 = createCompany("c1");
        ic1.getCompanies().add(c1);
        infoCardService.save(ic1);

        InfoCard ic2 = createInfoCard("1", "1", "1");
        Company c2 = createCompany("c2");
        ic2.getCompanies().add(c2);
        Company c3 = createCompany("c3");
        ic2.getCompanies().add(c3);
        infoCardService.save(ic2);

        InfoCard infoCardFromDb = infoCardRepository.findFirstByFirstNameAndPatronymicAndLastNameAllIgnoreCase("1", "1", "1");
        assertNotNull(infoCardFromDb);
        assertNotNull(infoCardFromDb.getCompanies());
        assertEquals(3, infoCardFromDb.getCompanies().size());
        assertEquals(new HashSet<>(Arrays.asList("c1", "c2", "c3")),
                infoCardFromDb.getCompanies().stream().map(Company::getName).collect(Collectors.toSet()));
    }

    @Test
    @Transactional
    public void testSaveMixedCompanies_OwnedAndNewCompaniesMix_toExistingInfocard() {
        InfoCard ic1 = createInfoCard("1", "1", "1");
        Company c1 = createCompany("c1");
        ic1.getCompanies().add(c1);
        infoCardService.save(ic1);

        InfoCard ic2 = createInfoCard("2", "2", "2");
        Company c2 = new Company();
        c2.setName("c2");
        ic2.getCompanies().add(c2);
        Company c3 = new Company();
        c3.setName("c3");
        ic2.getCompanies().add(c3);
        infoCardService.save(ic2);

        InfoCard ic3 = createInfoCard("1", "1", "1");
        Company c1_1 = new Company();
        c1_1.setName("c1");
        Company c1_2 = new Company();
        c1_2.setName("c2");
        Company c1_3 = new Company();
        c1_3.setName("c3");
        ic3.getCompanies().add(c1_1);
        ic3.getCompanies().add(c1_2);
        ic3.getCompanies().add(c1_3);
        infoCardService.save(ic3);

        InfoCard infocard = infoCardService.findByFirstNamePatroNymicLastName("1", "1", "1");
        assertNotNull(infocard);
        assertNotNull(infocard.getCompanies());
        assertEquals(new HashSet<>(Arrays.asList("c1", "c2", "c3")),
                infocard.getCompanies().stream().map(Company::getName)
                .collect(Collectors.toSet())
        );
    }

    @Test
    @Transactional
    public void testSaveMixedCompanies_ownedAndForeignCompaniesMix_toNewInfocard() {
        InfoCard ic1 = createInfoCard("1", "1", "1");
        Company c1 = createCompany("c1");
        ic1.getCompanies().add(c1);
        infoCardService.save(ic1);

        InfoCard ic2 = createInfoCard("2", "2", "2");
        Company c2 = new Company();
        c2.setName("c2");
        ic2.getCompanies().add(c2);

        Company c3 = new Company();
        c3.setName("c3");

        InfoCard ic3 = createInfoCard("3", "3", "3");
        Company c1_1 = new Company();
        c1_1.setName("c1");
        Company c1_2 = new Company();
        c1_2.setName("c2");
        Company c1_3 = new Company();
        c1_3.setName("c3");
        ic3.getCompanies().add(c1_1);
        ic3.getCompanies().add(c1_2);
        ic3.getCompanies().add(c1_3);
        infoCardService.save(ic3);

        InfoCard infocard = infoCardService.findByFirstNamePatroNymicLastName("3", "3", "3");
        assertNotNull(infocard);
        assertNotNull(infocard.getCompanies());
        assertEquals(new HashSet<>(Arrays.asList("c1", "c2", "c3")),
                infocard.getCompanies().stream().map(Company::getName)
                        .collect(Collectors.toSet())
        );
    }

    @Test
    @Transactional
    public void testSaveNoCompaniesAndThenAddCompanies() {
        InfoCard ic1 = createInfoCard("1", "1", "1");
        infoCardService.save(ic1);
        InfoCard ic2 = createInfoCard("1", "1", "1");
        Company c1 = Company.builder().name("c1").build();
        ic2.getCompanies().add(c1);
        infoCardService.save(ic2);

        InfoCard infoCard = infoCardService.findByFirstNamePatroNymicLastName("1", "1", "1");
        assertNotNull(infoCard);
        assertNotNull(infoCard.getCompanies());
        assertEquals("c1", infoCard.getCompanies().get(0).getName());
    }

    private Company createCompany(String name) {
        return Company.builder()
                    .name(name)
                    .build();
    }

    private InfoCard createInfoCard(String firstName, String patronymic, String lastName) {
        return InfoCard.builder()
                    .guid(UUID.randomUUID().toString())
                    .firstName(firstName)
                    .patronymic(patronymic)
                    .lastName(lastName)
                    .build();
    }


}