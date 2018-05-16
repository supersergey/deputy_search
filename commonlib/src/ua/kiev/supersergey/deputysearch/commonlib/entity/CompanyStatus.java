package ua.kiev.supersergey.deputysearch.commonlib.entity;

/**
 * Created by supersergey on 17.05.18.
 */
public enum CompanyStatus {
    PARSED_OK("PARSED_OK"),
    PARSED_FAIL("PARSED_FAIL"),
    NOT_FOUND("NOT_FOUND");

    private String status;

    CompanyStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
