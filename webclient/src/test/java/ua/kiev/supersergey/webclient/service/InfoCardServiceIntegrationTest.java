package ua.kiev.supersergey.webclient.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ua.kiev.supersergey.webclient.WebClientApp;
import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by supersergey on 21.05.18.
 */
@SpringBootTest(classes = WebClientApp.class)
@RunWith(SpringRunner.class)
public class InfoCardServiceIntegrationTest {
    @Autowired
    private DataAccessService infoCardService;


}