package ua.kiev.supersergey.deputysearch.commonlib.dao.helper;

import ua.kiev.supersergey.deputysearch.commonlib.entity.filter.SearchResultFilter;

public class QueryBuilder {
    private StringBuilder baseQuery;

    private QueryBuilder(boolean isCountOnly) {
        baseQuery = new StringBuilder("select ");
        if (isCountOnly) {
            baseQuery.append("count(*)");
        } else {
            baseQuery.append("sr");
        }
        baseQuery.append(" from SearchResult sr where sr.status = 'PARSED_OK'");
    }

    public static QueryBuilder newBuilder(boolean isCountOnly) {
        return new QueryBuilder(isCountOnly);
    }

    public QueryBuilder searchCriteria(SearchResultFilter.SearchCriteria searchCriteria) {
        if (searchCriteria.isEnabled()) {
            searchCriteria.getKeys().forEach(sc -> baseQuery.append(
                    String.format(" AND lower(%s) LIKE lower(%s)",
                       sc.getFirst().getDbName(),
                       "concat('%', '" + sc.getSecond() + "', '%')"))
            );
        }
        return this;
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
