package ua.kiev.supersergey.deputysearch.inputparser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import reactor.core.publisher.Flux;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.CompanyJson;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.InfoCardJson;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class DirectorTest {

    private Director director = new Director(null, null);
    private Flux<?> flux;

    @Before
    public void init() {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CompanyJson companyJson = new CompanyJson();
            companyJson.setGuid(String.valueOf(i));
            list.add(companyJson);
        }
        for (int i = 0; i < 5; i++) {
            InfoCardJson infoCardJson = new InfoCardJson();
            infoCardJson.setGuid(String.valueOf(i));
            list.add(infoCardJson);
        }
        flux = Flux.fromIterable(list);
    }

    @Test
    public void getCompaniesFlux() {
        Flux<Company> companiesFlux = director.getCompaniesFlux(flux);
        assertEquals(3, companiesFlux.collectList().block().size());
    }

    @Test
    public void getInfoCardsFlux() {
        Flux<InfoCard> infoCardFlux = director.getInfoCardsFlux(flux);
        assertEquals(5, infoCardFlux.collectList().block().size());
    }
}