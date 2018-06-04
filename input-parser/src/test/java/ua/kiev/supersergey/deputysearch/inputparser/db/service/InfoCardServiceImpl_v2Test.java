package ua.kiev.supersergey.deputysearch.inputparser.db.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.supersergey.deputysearch.commonlib.dao.CompanyRepository;
import ua.kiev.supersergey.deputysearch.commonlib.dao.InfoCardRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.CompanyStatus;
import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.InputParserApp;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest(classes = InputParserApp.class)
@RunWith(SpringRunner.class)
public class InfoCardServiceImpl_v2Test {

    @Autowired
    @Qualifier("InfoCardServiceImpl_v2")
    private InfoCardService infoCardService;

    @Autowired
    private InfoCardRepository infoCardRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    @Transactional
    public void testSimpleSave() {
        InfoCard ic = new InfoCard();
        ic.setGuid(UUID.randomUUID().toString());
        ic.setFirstName("1");
        ic.setPatronymic("1");
        ic.setLastName("1");

        Company c = new Company();
        c.setName("ABCD");
        c.setUrlTimeStamp(new Date());
//        c.getInfoCards().add(ic);
        ic.getCompanies().add(c);
        System.out.println(ic.getCompanies().iterator().next().getName());


        infoCardRepository.save(ic);

//        companyRepository.save(c1);
        Iterable<InfoCard> infocards = infoCardRepository.findInfoCardByFirstNameIgnoreCaseAndPatronymicIgnoreCaseAndLastNameIgnoreCase(ic.getFirstName(), ic.getPatronymic(), ic.getLastName());
        assertTrue(infocards.iterator().hasNext());
        assertEquals(1, infocards.iterator().next().getCompanies().size());
        assertEquals("ABCD", infocards.iterator().next().getCompanies().iterator().next().getName());
    }


}