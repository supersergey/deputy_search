package ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.entity;

/**
 * Created by supersergey on 17.05.18.
 */
public enum CompanyStatus {
    FOUND("FOUND"), // there are valid search results for import genius
    NOT_FOUND("NOT_FOUND"), // there are no valid search results
    PARSED_OK("PARSED_OK"), // import genius page successfully parsed
    PARSED_FAIL("PARSED_FAIL"); // failed to parse import genius page

    private String status;

    CompanyStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
