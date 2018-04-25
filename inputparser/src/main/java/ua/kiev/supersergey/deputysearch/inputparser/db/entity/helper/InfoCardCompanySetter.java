package ua.kiev.supersergey.deputysearch.inputparser.db.entity.helper;

import reactor.core.publisher.Flux;
import ua.kiev.supersergey.deputysearch.inputparser.db.entity.Company;
import ua.kiev.supersergey.deputysearch.inputparser.db.entity.InfoCard;

/**
 * Created by supersergey on 26.04.18.
 */
public class InfoCardCompanySetter {
    public static InfoCard setCompanies(InfoCard infoCard, Flux<Company> companies) {
        infoCard.setCompanies(
                companies.distinct()
                .filter(i -> i.getInfocardGuid().equals(infoCard.getId()))
                .collectList()
                .block()
        );
        return infoCard;
    }
}
