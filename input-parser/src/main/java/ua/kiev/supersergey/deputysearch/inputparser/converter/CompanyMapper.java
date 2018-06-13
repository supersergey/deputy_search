package ua.kiev.supersergey.deputysearch.inputparser.converter;

import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.CompanyJson;

/**
 * Created by supersergey on 25.04.18.
 */
public class CompanyMapper {
    public static Company toEntity(CompanyJson companyJson) {
        return Company.builder()
                .name(companyJson.getName())
                .infocardGuid(companyJson.getGuid())
                .build();
    }
}
