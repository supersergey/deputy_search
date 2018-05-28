package ua.kiev.supersergey.webclient.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.webclient.dto.SearchResultDto;
import ua.kiev.supersergey.webclient.dto.converter.EntityToDtoConverter;
import ua.kiev.supersergey.webclient.dto.Response;
import ua.kiev.supersergey.webclient.service.DataAccessService;

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
    public Response fetchGridData(@RequestParam Map<String, String> params) {
        List<SearchResult> searchResults = dataAccessService.fetchNonEmptySearchResults(LandingPageControllerHelper.decodeParams(params));
        List<SearchResultDto> resultDtos = searchResults.stream().map(EntityToDtoConverter::toDto).collect(Collectors.toList());
        int totalNumberOfRecords = dataAccessService.fetchNonEmptySearchResultsCount();
        return Response.builder()
                .rows(resultDtos)
                .page(page)
                .records(totalNumberOfRecords)
                .total(totalNumberOfRecords/size + 1)
                .build();
    }
}
