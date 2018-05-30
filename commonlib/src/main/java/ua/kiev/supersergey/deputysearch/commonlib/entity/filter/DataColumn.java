package ua.kiev.supersergey.deputysearch.commonlib.entity.filter;

public enum DataColumn {
    ID("id", "id"),
    BENEFICIARY("beneficiary", "company.infoCard.lastName"),
    COMPANY("company", "company.name"),
    FREIGHT_DESC("freightDesc", "freightDesc"),
    SENDER("sedner", "senderName"),
    RECEPIENT("recepient", "recepientName");

    private String webName;
    private String dbName;

    DataColumn(String webName, String dbName) {
        this.webName = webName;
        this.dbName = dbName;
    }

    public String getWebName() {
        return webName;
    }

    public String getDbName() {
        return dbName;
    }

    public static DataColumn findByWebName(String webName) {
        for (DataColumn dc : DataColumn.values()) {
            if (webName.equalsIgnoreCase(dc.getWebName())) {
                return dc;
            }
        }
        return null;
    }
}
