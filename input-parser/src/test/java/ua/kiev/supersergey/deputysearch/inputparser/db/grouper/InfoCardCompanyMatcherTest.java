package ua.kiev.supersergey.deputysearch.inputparser.db.grouper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import reactor.core.publisher.Flux;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.Director;
import ua.kiev.supersergey.deputysearch.inputparser.db.service.InfoCardService;
import ua.kiev.supersergey.deputysearch.inputparser.json.deserializer.Deserializer;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.CompanyJson;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.InfoCardJson;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class InfoCardCompanyMatcherTest {
    private Flux<?> flux;

    @Before
    public void init() {
        InfoCardJson ic1 = new InfoCardJson();
        ic1.setGuid("1");
        CompanyJson c1_1 = new CompanyJson();
        c1_1.setName("1");
        c1_1.setGuid("1");


        InfoCardJson ic2 = new InfoCardJson();
        ic2.setGuid("2");
        CompanyJson c1_2 = new CompanyJson();
        c1_2.setGuid("2");
        c1_2.setName("1_2");
        CompanyJson c2_2 = new CompanyJson();
        c2_2.setGuid("2");
        c2_2.setName("2_2");


        InfoCardJson ic3 = new InfoCardJson();
        ic3.setGuid("3");

        CompanyJson c3_0 = new CompanyJson();
        c3_0.setGuid("0000");
        c3_0.setName("3_0");

        flux = Flux.fromArray(new Object[] {c1_1, ic1, c1_2, c2_2, ic2, c3_0, ic3});
    }

    @Test
    public void match() {
        TestDirector director = new TestDirector(null, null);
        Flux<Company> companiesFlux = director.getCompaniesFlux(flux);
        Flux<InfoCard> infoCardsFlux = director.getInfoCardsFlux(flux);
        List<InfoCard> matchedData = InfoCardCompanyMatcher.match(infoCardsFlux, companiesFlux);
        assertEquals(3, matchedData.size());
        assertEquals(2, matchedData.stream()
                .filter(infoCard -> infoCard.getGuid().equals("2"))
                .findFirst().get().getCompanies().size());

        assertEquals(true, matchedData.stream()
                .noneMatch(infoCard -> infoCard.getGuid().equals("0000")));

    }

    class TestDirector extends Director {
        public TestDirector(Deserializer deserializer, InfoCardService infoCardService) {
            super(deserializer, infoCardService);
        }

        @Override
        public Flux<Company> getCompaniesFlux(Flux<?> objects) {
            return super.getCompaniesFlux(objects);
        }

        @Override
        public Flux<InfoCard> getInfoCardsFlux(Flux<?> objects) {
            return super.getInfoCardsFlux(objects);
        }
    }


}