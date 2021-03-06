package ua.kiev.supersergey.deputysearch.webclient.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.kiev.supersergey.deputysearch.webclient.dao.querybuilder.SearchResultFilter;
import ua.kiev.supersergey.deputysearch.webclient.contoller.helper.UiRequestDecoder;
import ua.kiev.supersergey.deputysearch.webclient.dto.Response;
import ua.kiev.supersergey.deputysearch.webclient.dto.SearchResultDto;
import ua.kiev.supersergey.deputysearch.webclient.dto.converter.EntityToDtoConverter;
import ua.kiev.supersergey.deputysearch.webclient.service.DataAccessService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by supersergey on 21.05.18.
 */
@Controller
public class LandingPageController {
    private DataAccessService dataAccessService;

    @Autowired
    public LandingPageController(DataAccessService dataAccessService) {
        this.dataAccessService = dataAccessService;
    }

    @RequestMapping(path = "/")
    public String fetchLandingPage() {
        return "index.html";
    }

    @RequestMapping(path = "/grid")
    @ResponseBody
    public Response fetchGridData(
            @RequestParam Map<String, String> params
            ) {
        SearchResultFilter searchResultFilter = UiRequestDecoder.decodeParams(params);
        List<SearchResultDto> resultDtos = dataAccessService
                .fetchNonEmptySearchResults(searchResultFilter)
                .map(EntityToDtoConverter::toDto)
                .collect(Collectors.toList());
        long totalNumberOfRecords = dataAccessService.fetchNonEmptySearchResultsCount(searchResultFilter);
        return Response.builder()
                .rows(resultDtos)
                .page(searchResultFilter.getPage())
                .records(totalNumberOfRecords)
                .total(totalNumberOfRecords / searchResultFilter.getSize() + 1)
                .build();
    }
}
