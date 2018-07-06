package ua.kiev.supersergey.deputysearch.google_search_client.httpclient.exception;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Slf4j
public class SearchForbiddenException extends Exception{
    public SearchForbiddenException() {
        super();
    }
}
