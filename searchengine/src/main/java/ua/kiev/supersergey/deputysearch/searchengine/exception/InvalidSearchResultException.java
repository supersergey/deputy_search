package ua.kiev.supersergey.deputysearch.searchengine.exception;

/**
 * Created by supersergey on 17.05.18.
 */
public class InvalidSearchResultException extends Exception {
    public InvalidSearchResultException(Throwable cause) {
        super(cause);
    }

    public InvalidSearchResultException(String message) {
        super(message);
    }

    public InvalidSearchResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSearchResultException() {
    }
}
