package ua.kiev.supersergey.deputysearch.commonlib.entity;

/**
 * Created by supersergey on 20.05.18.
 */
public enum CompanyStatus {
    FOUND("FOUND"), // there are valid search results for import genius
    NOT_FOUND("NOT_FOUND"), // there are no valid search results
    FAIL("FAIL"), // error occurred while searching
    FORBIDDEN("FORBIDDEN") ;

    private String status;

    CompanyStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
