package ua.kiev.supersergey.deputysearch.import_genius_client;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ua.kiev.supersergey.deputysearch.commonlib.dao.SearchResultsRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResultStatus;
import ua.kiev.supersergey.deputysearch.import_genius_client.parser.ImportGeniusPageParser;
import ua.kiev.supersergey.deputysearch.import_genius_client.exception.InvalidSearchResultException;
import ua.kiev.supersergey.deputysearch.import_genius_client.httpclient.ImportGeniusClient;
import ua.kiev.supersergey.deputysearch.import_genius_client.parser.validator.SearchResultValidator;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by supersergey on 21.05.18.
 */
@Service
@Slf4j
public class ImportGeniusDirector {
    @Value("${importgenius-parser.timeout}")
    private int timeout;
    private final static Random RND = new Random();
    private SearchResultsRepository resultsRepository;

    @Autowired
    public ImportGeniusDirector(SearchResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    public void parse() throws InterruptedException{
        do {
            List<SearchResult> urlsToParse = resultsRepository.fetchNotParsedUrls(PageRequest.of(0, 10));
            if (CollectionUtils.isEmpty(urlsToParse)) {
                break;
            } else {
                for (SearchResult searchResult : urlsToParse) {
                    log.info("Attempting to parse url: " + searchResult.getUrl());
                    searchResult = doParsing(searchResult);
                    resultsRepository.save(searchResult);
                    log.info("Successfully saved parse result for url: " + searchResult.getUrl());
                    int currentTimeout = timeout + RND.nextInt(100);
                    log.info("Sleeping for timeout: " + currentTimeout);
                    Thread.sleep(TimeUnit.SECONDS.toMillis(currentTimeout));
                }
            }
        } while (true);
    }

    private SearchResult doParsing(SearchResult searchResult) {
        long id = searchResult.getId();
        Company company = searchResult.getCompany();
        String url = searchResult.getUrl();
        try {
            Document document = ImportGeniusClient.fetchImportGeniusPage(searchResult.getUrl());
            searchResult = ImportGeniusPageParser.parseDocument(document);
            if (!SearchResultValidator.isValid(searchResult, searchResult.getCompany().getName()))
            {
                searchResult.setStatus(SearchResultStatus.IRRELEVANT);
            }
        } catch (InvalidSearchResultException ex) {
            log.info("Parsing of url failed: " + searchResult.getUrl());
            searchResult.setStatus(SearchResultStatus.PARSED_FAIL);
            searchResult.setErrorMessage(ex.getMessage());
        }
        finally {
            searchResult.setId(id);
            searchResult.setCompany(company);
            searchResult.setUrl(url);
        }
        return searchResult;
    }
}
