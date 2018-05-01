package ua.kiev.supersergey.searchengine.deputysearch.inputparser.db.grouper;

import reactor.core.publisher.Flux;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.db.entity.Company;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.db.entity.InfoCard;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

/**
 * Created by supersergey on 26.04.18.
 */
public class Grouper {

    public static List<InfoCard> group(Flux<InfoCard> infoCardFlux, Flux<Company> companyFlux) {
        List<InfoCard> infoCards = infoCardFlux.collectList().block();
        List<Company> companies = companyFlux.collectList().block();
        Map<String, List<String>> guidsByOwner = collectOwnerGuids(infoCards);
        Map<String, List<Company>> companiesByOwner = collectOwnerCompanies(companies, guidsByOwner);

        infoCards.forEach(i -> {
            String owner = joinOwnerName(i);
            i.setCompanies(companiesByOwner.get(owner));
            i.getCompanies().forEach(c -> c.setInfoCard(i));
            i.setGuid(guidsByOwner.get(joinOwnerName(i)).get(0));
            i.setCreatedDate(new Date());
        });
        return infoCards;
    }

    private static Map<String, List<Company>> collectOwnerCompanies(List<Company> companies, Map<String, List<String>> guidsByOwner) {
        Map<String, List<Company>> companiesByOwner = new HashMap<>();
        for (Map.Entry<String, List<String>> e : guidsByOwner.entrySet()) {
            List<Company> companiesOfOwner = companies.stream()
                    .filter(c -> e.getValue().contains(c.getInfocardGuid()))
                    .collect(Collectors.toList());
            companiesByOwner.put(e.getKey(), companiesOfOwner);
        }
        return companiesByOwner;
    }

    private static Map<String, List<String>> collectOwnerGuids(List<InfoCard> infoCards) {
        return infoCards.stream().collect(
                Collectors.groupingBy(
                        Grouper::joinOwnerName, Collectors.mapping(InfoCard::getGuid, toList())));
    }

    private static String joinOwnerName(InfoCard infoCard) {
        return String.join(";", infoCard.getFirstName(), infoCard.getPatronymic(), infoCard.getLastName());
    }
}
