package ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.inputparser.converter;

import ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.inputparser.json.entity.InfoCardJson;

/**
 * Created by supersergey on 26.04.18.
 */
public class InfocardMapper {
    public static InfoCard toEntity(InfoCardJson infoCardJson) {
        InfoCard infoCard = new InfoCard();
        infoCard.setFirstName(infoCardJson.getFirstName());
        infoCard.setPatronymic(infoCardJson.getPatronymic());
        infoCard.setLastName(infoCardJson.getLastName());
        infoCard.setGuid(infoCardJson.getGuid());
        infoCard.setUrl(infoCardJson.getUrl());
        infoCard.setCreatedDate(infoCardJson.getCreatedDate());
        return infoCard;
    }
}
