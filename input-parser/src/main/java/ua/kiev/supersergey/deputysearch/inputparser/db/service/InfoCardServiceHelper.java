package ua.kiev.supersergey.deputysearch.inputparser.db.service;

import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;

import java.util.*;

public class InfoCardServiceHelper {
    public static List<Company> mergeCompaniesByName(List<Company> newCompanies, List<Company> companiesFromDb) {
        newCompanies.forEach(nc -> {
                    if (!isCompanyInDb(nc, companiesFromDb)) {
                        companiesFromDb.add(nc);
                    }
                });
        return companiesFromDb;
    }

    private static boolean isCompanyInDb(Company nc, List<Company> companiesFromDb) {
        return companiesFromDb.stream()
                .anyMatch(cfdb ->
                        cfdb.getName().equals(nc.getName()));
    }
}
