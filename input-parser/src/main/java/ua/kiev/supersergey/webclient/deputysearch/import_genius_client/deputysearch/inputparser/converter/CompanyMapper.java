package ua.kiev.supersergey.webclient.deputysearch.import_genius_client.deputysearch.inputparser.converter;

import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.webclient.deputysearch.import_genius_client.deputysearch.inputparser.json.entity.CompanyJson;

/**
 * Created by supersergey on 25.04.18.
 */
public class CompanyMapper {
    public static Company toEntity(CompanyJson companyJson) {
        Company company = new Company();
        company.setName(companyJson.getName());
        company.setInfocardGuid(companyJson.getGuid());
        return company;
    }
}