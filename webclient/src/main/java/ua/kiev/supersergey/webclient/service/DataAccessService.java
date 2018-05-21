package ua.kiev.supersergey.webclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.supersergey.deputysearch.commonlib.dao.CompanyRepository;
import ua.kiev.supersergey.deputysearch.commonlib.dao.InfoCardRepository;
import ua.kiev.supersergey.deputysearch.commonlib.dao.SearchResultsRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;

import java.util.List;

/**
 * Created by supersergey on 21.05.18.
 */
@Service
public class DataAccessService {
    private InfoCardRepository infoCardRepository;
    private SearchResultsRepository searchResultsRepository;
    private CompanyRepository companyRepository;

    @Autowired
    public DataAccessService(InfoCardRepository infoCardRepository, SearchResultsRepository searchResultsRepository, CompanyRepository companyRepository) {
        this.infoCardRepository = infoCardRepository;
        this.searchResultsRepository = searchResultsRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public List<SearchResult> fetchNonEmptySearchResults(int page, int size) {
        return searchResultsRepository.fetchNonEmptySearchResults(
                PageRequest.of(page, size)
        );
    }
}
