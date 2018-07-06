package ua.kiev.supersergey.deputysearch.google_search_client.db;

import org.springframework.stereotype.Service;
import ua.kiev.supersergey.deputysearch.commonlib.dao.CompanyRepository;
import ua.kiev.supersergey.deputysearch.commonlib.dao.SearchResultsRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;

import java.util.function.Consumer;

@Service
public class SearchResultService implements Consumer<SearchResult>{
    private final CompanyRepository companyRepository;

    public SearchResultService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void accept(SearchResult searchResult) {

    }
}
