package ua.kiev.supersergey.webclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ua.kiev.supersergey.deputysearch.commonlib.dao.CompanyRepository;
import ua.kiev.supersergey.deputysearch.commonlib.dao.InfoCardRepository;
import ua.kiev.supersergey.deputysearch.commonlib.dao.SearchResultsRepository;
import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.webclient.contoller.SearchResultFilter;

import javax.sql.rowset.Predicate;
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

    public int fetchNonEmptySearchResultsCount() {
        return searchResultsRepository.fetchNonEmptySearchResultsCount();
    }

    @Transactional
    public List<SearchResult> fetchNonEmptySearchResults(SearchResultFilter searchResultFilter) {
        SearchResultFilter.SearchCriteria searchCriteria = searchResultFilter.getSearchCriteria();
        if (!searchCriteria.isSearchEnabled()
                || searchCriteria.getSearchKey() == null
                || StringUtils.isEmpty(searchCriteria.getSearchKey().getSecond())
                ) {
            return searchResultsRepository.fetchNonEmptySearchResults(preparePageRequest(searchResultFilter));
        } else {
            switch (searchCriteria.getSearchKey().getFirst()) {
                case COMPANY:
                case SENDER:
                case RECEPIENT:
                    return searchResultsRepository.fetchNonEmptySearchResults_SearchByCompany(
                            preparePageRequest(searchResultFilter),
                            searchResultFilter.getSearchCriteria().getSearchKey().getSecond());
                case BENEFICIARY:
                    return
                case FREIGHT_DESC:
                    break;
                default:
                    return null;
            }
        }
        PathBuilder<InfoCard> entityPath = new PathBuilder<>(MyUser.class, "user");

    }

    private PageRequest preparePageRequest(SearchResultFilter searchResultFilter) {
        return PageRequest.of
                (searchResultFilter.getPage(), searchResultFilter.getSize(), searchResultFilter.getSort());
    }
}
