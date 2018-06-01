package ua.kiev.supersergey.deputysearch.webclient.dao.querybuilder;

import org.springframework.data.util.Pair;

public class QueryBuilder {
    private StringBuilder baseQuery;

    private QueryBuilder(boolean isCountOnly) {
        baseQuery = new StringBuilder("select ");

        if (isCountOnly) {
            baseQuery.append(" count(*) ");
        } else {
            baseQuery.append(" sr.recepient_name as recepientName, ");
            baseQuery.append(" sr.recepient_address as recepientAddress, ");
            baseQuery.append(" sr.sender_name as senderName, ");
            baseQuery.append(" sr.sender_address as senderAddress, ");
            baseQuery.append(" sr.freight_desc as freightDesc, ");
            baseQuery.append(" sr.url as url, ");
            baseQuery.append(" c.name as companyName, ");
            baseQuery.append(" ic.first_name as firstName, ");
            baseQuery.append(" ic.patronymic as patronymic, ");
            baseQuery.append(" ic.last_name as lastName, ");
            baseQuery.append(" ic.guid as beneficiaryGuid, ");
            baseQuery.append(" c.uuid as companyUuid, ");
            baseQuery.append(" sr.id as searchResultId ");
        }

        baseQuery.append(" FROM search_results sr");
        baseQuery.append(" JOIN company c ON sr.company_uuid = c.uuid");
        baseQuery.append(" JOIN infocard_company ic_c ON c.uuid = ic_c.company_uuid ");
        baseQuery.append(" JOIN info_card ic ON ic.guid = ic_c.infocard_guid ");
        baseQuery.append(" where sr.status = 'PARSED_OK'");
    }

    public static QueryBuilder newBuilder(boolean isCountOnly) {
        return new QueryBuilder(isCountOnly);
    }

    public QueryBuilder searchCriteria(SearchResultFilter.SearchCriteria searchCriteria) {
        if (searchCriteria.isEnabled()) {
            searchCriteria.getKeys().forEach(this::addSearchKey);
        }
        return this;
    }

    private StringBuilder addSearchKey(Pair<DataColumn, String> sc) {
        baseQuery.append(
                String.format(" AND lower(%s) LIKE lower(%s)",
                        sc.getFirst().getDbName(),
                        "concat('%', '" + sc.getSecond() + "', '%')")
        );
        return baseQuery;
    }

    public QueryBuilder sortCriteria(SearchResultFilter.SortCriteria sortCriteria) {
        if (sortCriteria.isEnabled()) {
            baseQuery.append(
                    String.format(" ORDER BY %s %s",
                            sortCriteria.getColumn().getDbName(),
                            sortCriteria.getDirection().getValue())
            );
        }
        return this;
    }

    public String build() {
        return baseQuery.toString();
    }
}
