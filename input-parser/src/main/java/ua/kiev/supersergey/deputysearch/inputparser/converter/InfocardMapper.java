package ua.kiev.supersergey.deputysearch.inputparser.converter;

import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.InfoCardJson;

/**
 * Created by supersergey on 26.04.18.
 */
public class InfocardMapper {
    public static InfoCard toEntity(InfoCardJson infoCardJson) {
        return InfoCard.builder()
                .firstName(infoCardJson.getFirstName())
                .patronymic(infoCardJson.getPatronymic())
                .lastName(infoCardJson.getLastName())
                .guid(infoCardJson.getGuid())
                .url(infoCardJson.getUrl())
                .build();
    }
}
