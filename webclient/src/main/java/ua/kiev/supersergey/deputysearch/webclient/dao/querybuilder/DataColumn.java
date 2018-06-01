package ua.kiev.supersergey.deputysearch.webclient.dao.querybuilder;

public enum DataColumn {
    ID("id", "id"),
    BENEFICIARY("beneficiary", "ic.last_name"),
    COMPANY("company", "c.name"),
    FREIGHT_DESC("freightDesc", "sr.freight_desc"),
    SENDER("sedner", "sr.sender_name"),
    RECEPIENT("recepient", "sr.recepient_name");

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
