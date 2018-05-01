package ua.kiev.supersergey.searchengine.deputysearch.inputparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.converter.CompanyMapper;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.converter.InfocardMapper;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.db.entity.Company;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.db.entity.InfoCard;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.db.grouper.Grouper;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.db.service.InfoCardService;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.json.deserializer.Deserializer;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.json.entity.CompanyJson;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.json.entity.InfoCardJson;
import ua.kiev.supersergey.searchengine.deputysearch.inputparser.json.filereader.JsonInputFileReader;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Created by supersergey on 26.04.18.
 */
@Component
public class Director {
    @Value("${input.file.name}")
    private String fileName;
    private Deserializer deserializer;
    private InfoCardService infoCardService;

    @Autowired
    public Director(Deserializer deserializer, InfoCardService infoCardService) {
        this.deserializer = deserializer;
        this.infoCardService = infoCardService;
    }

    public void runFlow() {
        try {
            URL resource = this.getClass().getClassLoader().getResource(fileName);
            byte[] rawJson = JsonInputFileReader.read(Paths.get(resource.getFile()));
            Flux<?> objects = deserializer.deserialize(rawJson);
            Flux<InfoCard> infoCardFlux = getInfoCardFlux(objects);
            Flux<Company> companyFlux = getCompanyFlux(objects);
            infoCardService.saveAll(Grouper.group(infoCardFlux, companyFlux));
        } catch (IOException ex) {
            throw new RuntimeException("Cannot read input data", ex);
        }
    }

    private Flux<Company> getCompanyFlux(Flux<?> objects) {
        return objects
                        .filter(Objects::nonNull)
                        .filter(i -> i.getClass().equals(CompanyJson.class))
                        .distinct()
                        .cast(CompanyJson.class)
                        .map(CompanyMapper::toEntity);
    }

    private Flux<InfoCard> getInfoCardFlux(Flux<?> objects) {
        return objects
                        .filter(Objects::nonNull)
                        .filter(i -> i.getClass().equals(InfoCardJson.class))
                        .cast(InfoCardJson.class)
                        .map(InfocardMapper::toEntity);
    }
}
