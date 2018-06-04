package ua.kiev.supersergey.deputysearch.inputparser.db.grouper;

import reactor.core.publisher.Flux;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

/**
 * Collects and matches infocards and companies together by infocard guid
 */
public class InfoCardCompanyMatcher {

    public static List<InfoCard> match(Flux<InfoCard> infoCardFlux, Flux<Company> companyFlux) {
        List<InfoCard> infoCards = infoCardFlux.collectList().block();
        List<Company> companies = companyFlux.collectList().block();
//        infoCards.forEach(ic -> {
//            ic.setCompanies(
//                    companies.stream()
//                            .filter(c -> ic.getGuid().equals(c.getInfocardGuid()))
//                            .collect(toList())
//            );
//            ic.getCompanies().forEach(c -> c.getInfoCards().add(ic));
//        });
        return infoCards;
    }
}
