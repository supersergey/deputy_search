package ua.kiev.supersergey.deputysearch.inputparser.db.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InfoCardServiceHelperTest {
    List<Company> newCompanies;
    List<Company> companiesFromDb;

    @Before
    public void init() {
        Company c1 = new Company();
        c1.setName("c1");
        c1.setUuid("c1");
        Company c2 = new Company();
        c2.setName("c2");
        c2.setUuid("c2");
        Company c3_db = new Company();
        c3_db.setName("c3");
        c3_db.setUuid("c3_db");
        Company c3 = new Company();
        c3.setName("c3");
        c3.setUuid("c3_db");
        companiesFromDb = new ArrayList<>();
        newCompanies = new ArrayList<>();
        companiesFromDb.add(c3);
        newCompanies.add(c1);
        newCompanies.add(c2);
        newCompanies.add(c3);
    }

    @Test
    public void replaceCompaniesByName() {
        Collection<Company> result = InfoCardServiceHelper.mergeCompaniesByName(newCompanies, companiesFromDb);
        assertFalse(CollectionUtils.isEmpty(result));
        assertEquals(3, result.size());
        assertEquals(new HashSet<>(Arrays.asList("c1", "c2", "c3_db")),
                result.stream().map(Company::getUuid).collect(Collectors.toSet()));
    }
}