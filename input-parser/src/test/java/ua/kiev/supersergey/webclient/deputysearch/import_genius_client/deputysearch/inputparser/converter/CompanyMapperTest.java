package ua.kiev.supersergey.webclient.deputysearch.import_genius_client.deputysearch.inputparser.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.webclient.deputysearch.import_genius_client.deputysearch.inputparser.json.entity.CompanyJson;

import static org.junit.Assert.assertEquals;

/**
 * Created by supersergey on 28.04.18.
 */
@RunWith(JUnit4.class)
public class CompanyMapperTest {
    private CompanyJson companyJson;
    private static final String GUID = "123456";
    private static final String COMPANY_NAME = "Home Company";

    public CompanyMapperTest() {
        companyJson = new CompanyJson();
        companyJson.setGuid(GUID);
        companyJson.setName(COMPANY_NAME);
    }

    @Test
    public void toEntity() throws Exception {
        Company company = CompanyMapper.toEntity(companyJson);
        assertEquals(GUID, company.getInfocardGuid());
        assertEquals(COMPANY_NAME, company.getName());
    }

}