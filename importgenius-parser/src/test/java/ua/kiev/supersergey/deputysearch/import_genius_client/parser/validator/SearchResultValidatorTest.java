package ua.kiev.supersergey.deputysearch.import_genius_client.parser.validator;

import org.junit.Before;
import org.junit.Test;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SearchResultValidatorTest {

    private List<SearchResult> searchResults;
    private SearchResult invalidSearchResult;
    private SearchResultValidator validator;

    @Before
    public void init() {
        Company company = new Company();
        company.setName("ООО ТОВ \"Интербуд\"");
        searchResults = Arrays.asList(
                SearchResult.builder()
                .company(company)
                .recepientName("интербуд")
                .senderName(null)
                .build(),
                SearchResult.builder()
                        .company(company)
                        .recepientName(null)
                        .senderName("интербуд")
                        .build(),
                SearchResult.builder()
                        .company(company)
                        .recepientName("")
                        .senderName("Интербудбуд")
                        .build());
        invalidSearchResult = SearchResult.builder()
                .company(company)
                .recepientName("Рога и копыта")
                .senderName("")
                .build();
        validator = new SearchResultValidator();
    }

    @Test
    public void isValid() {
        assertEquals(3, searchResults.stream().filter(validator).collect(Collectors.toList()).size());
    }

    @Test
    public void isInvalid() {
        assertFalse(validator.test(invalidSearchResult));
    }

    @Test
    public void abc() {
        String s = "this is supertest";
        String max = Arrays.stream(s.split(" ")).sorted((o1, o2) -> Integer.compare(o2.length(), o1.length())).findFirst().get();
        System.out.println(max);

    }
}