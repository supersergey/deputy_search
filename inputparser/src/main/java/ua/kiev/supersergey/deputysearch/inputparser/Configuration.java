package ua.kiev.supersergey.deputysearch.inputparser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import ua.kiev.supersergey.deputysearch.inputparser.entity.Company;
import ua.kiev.supersergey.deputysearch.inputparser.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.deserializer.CompanyDeserializer;
import ua.kiev.supersergey.deputysearch.inputparser.deserializer.Deserializer;
import ua.kiev.supersergey.deputysearch.inputparser.deserializer.InfoCardDeserializer;

import java.util.List;

/**
 * Created by supersergey on 21.04.18.
 */
@SpringBootConfiguration
public class Configuration {
    @Bean
    @Qualifier("InfoCardDeserializer")
    public Deserializer<List<InfoCard>> infoCardDeserializer() {
        return new InfoCardDeserializer(companyDeserializer());
    }

    @Bean
    @Qualifier("CompanyCardDeserializer")
    public Deserializer<List<Company>> companyDeserializer() {
        return new CompanyDeserializer();
    }
}
