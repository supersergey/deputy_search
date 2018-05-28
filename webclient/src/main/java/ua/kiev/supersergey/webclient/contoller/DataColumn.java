package ua.kiev.supersergey.webclient.contoller;

public enum DataColumn {
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
            if (webName.equalsIgnoreCase(dc.getDbName())) {
                return dc;
            }
        }
        return null;
    }
}
