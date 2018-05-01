package ua.kiev.supersergey.searchengine.deputysearch.inputparser.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.db.entity.InfoCard;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.json.entity.InfoCardJson;

import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by supersergey on 28.04.18.
 */
@RunWith(JUnit4.class)
public class InfocardMapperTest {
    private static final String GUID = "123456";
    private static final String FIRST_NAME = "First Name";
    private static final String LAST_NAME = "Last Name";
    private static final String PATRONYMIC = "Patronymic";
    private static final String URL = "url";
    private static final Date CREATED_DATE = Date.from(Instant.now());

    private InfoCardJson infoCardJson;

    public InfocardMapperTest() {
        this.infoCardJson = new InfoCardJson();
        infoCardJson.setGuid(GUID);
        infoCardJson.setFirstName(FIRST_NAME);
        infoCardJson.setLastName(LAST_NAME);
        infoCardJson.setPatronymic(PATRONYMIC);
        infoCardJson.setUrl(URL);
        infoCardJson.setCreatedDate(CREATED_DATE);
    }

    @Test
    public void name() throws Exception {
        InfoCard infoCard = InfocardMapper.toEntity(infoCardJson);
        assertEquals(GUID, infoCard.getGuid());
        assertEquals(FIRST_NAME, infoCard.getFirstName());
        assertEquals(LAST_NAME, infoCard.getLastName());
        assertEquals(PATRONYMIC, infoCard.getPatronymic());
        assertEquals(URL, infoCard.getUrl());
        assertEquals(CREATED_DATE, infoCard.getCreatedDate());
    }
}