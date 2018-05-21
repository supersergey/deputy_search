package ua.kiev.supersergey.webclient.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.kiev.supersergey.deputysearch.commonlib.entity.SearchResult;
import ua.kiev.supersergey.webclient.service.DataAccessService;

import java.util.List;

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
    public String fetchLandingPage(@RequestParam(required = false, defaultValue = "0") int page,
                                         @RequestParam(required = false, defaultValue = "30") int size,
                                         Model model) {
        List<SearchResult> searchResults = dataAccessService.fetchNonEmptySearchResults(page, size);
        model.addAttribute("searchResults", searchResults);
        return "landingpage";
    }
}
