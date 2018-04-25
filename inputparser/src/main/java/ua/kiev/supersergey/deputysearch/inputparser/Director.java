package ua.kiev.supersergey.deputysearch.inputparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Signal;
import ua.kiev.supersergey.deputysearch.inputparser.converter.CompanyMapper;
import ua.kiev.supersergey.deputysearch.inputparser.converter.InfocardMapper;
import ua.kiev.supersergey.deputysearch.inputparser.db.entity.Company;
import ua.kiev.supersergey.deputysearch.inputparser.db.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.db.entity.helper.InfoCardCompanySetter;
import ua.kiev.supersergey.deputysearch.inputparser.db.service.InfoCardService;
import ua.kiev.supersergey.deputysearch.inputparser.json.deserializer.Deserializer;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.CompanyJson;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.InfoCardJson;
import ua.kiev.supersergey.deputysearch.inputparser.json.filereader.JsonInputFileReader;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

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
            Flux<InfoCard> infoCards = objects
                    .filter(Objects::nonNull)
                    .filter(i -> i.getClass().equals(InfoCardJson.class))
                    .map(i -> (InfoCardJson) i)
                    .map(InfocardMapper::toEntity);
            Flux<Company> companies = objects
                    .filter(Objects::nonNull)
                    .filter(i -> i.getClass().equals(CompanyJson.class))
                    .distinct()
                    .map(i -> (CompanyJson) i)
                    .map(CompanyMapper::toEntity);
            companies.subscribe(System.out::println);
            System.out.println(companies.count().toString());

        } catch (IOException ex) {
            throw new RuntimeException("Cannot read input data", ex);
        }
    }
}
