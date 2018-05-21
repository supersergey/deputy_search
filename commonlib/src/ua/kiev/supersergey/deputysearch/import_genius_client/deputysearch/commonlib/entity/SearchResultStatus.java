package ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.entity;

/**
 * Created by supersergey on 17.05.18.
 */
public enum SearchResultStatus {

    PARSED_OK("PARSED_OK"), // import genius page successfully parsed
    PARSED_FAIL("PARSED_FAIL"); // failed to parse import genius page

    private String status;

    SearchResultStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
