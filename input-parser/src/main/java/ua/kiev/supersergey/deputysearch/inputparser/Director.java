package ua.kiev.supersergey.deputysearch.inputparser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.codec.Decoder;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ua.kiev.supersergey.deputysearch.commonlib.entity.Company;
import ua.kiev.supersergey.deputysearch.commonlib.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.converter.InfocardMapper;
import ua.kiev.supersergey.deputysearch.inputparser.db.grouper.InfoCardCompanyMatcher;
import ua.kiev.supersergey.deputysearch.inputparser.json.deserializer.DeclarationsResponseDeserializer;
import ua.kiev.supersergey.deputysearch.inputparser.json.deserializer.Deserializer;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.CompanyJson;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.DeclarationsResponse;
import ua.kiev.supersergey.deputysearch.inputparser.json.entity.InfoCardJson;
import ua.kiev.supersergey.deputysearch.inputparser.json.filereader.JsonInputFileReader;
import ua.kiev.supersergey.deputysearch.inputparser.converter.CompanyMapper;
import ua.kiev.supersergey.deputysearch.inputparser.db.service.InfoCardService;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
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
    @Value("${input.url}")
    private String url;

    @Autowired
    public Director(Deserializer deserializer, InfoCardService infoCardService) {
        this.deserializer = deserializer;
        this.infoCardService = infoCardService;
    }

    public void runFlow() {
//        try {
//            URL resource = this.getClass().getClassLoader().getResource(fileName);
//
//            WebClient client = WebClient.builder()
//                    .baseUrl(url)
//                    .codecs
//                    .build();
//            Map<String, String> params = new HashMap<>();
//            params.put("page", String.valueOf(2));
//            client.get()
//                    .uri(url, params)
//                    .accept(MediaType.APPLICATION_JSON)
//                    .retrieve()
//                    .bodyToMono(DeclarationsResponse.class)
//                    .log()
//                    .block();
//
//
//            JsonInputFileReader.read(Paths.get(resource.getFile()));
//            Flux<?> objects = deserializer.deserialize(rawJson);
//            Flux<InfoCard> infoCardsFlux = getInfoCardsFlux(objects);
//            Flux<Company> companiesFlux = getCompaniesFlux(objects);
//            infoCardService.saveAll(InfoCardCompanyMatcher.match(infoCardsFlux, companiesFlux));
//        } catch (IOException ex) {
//            throw new RuntimeException("Cannot read input data", ex);
//        }
    }

    protected Flux<Company> getCompaniesFlux(Flux<?> objects) {
        return objects
                        .filter(Objects::nonNull)
                        .filter(i -> i.getClass().equals(CompanyJson.class))
                        .distinct()
                        .cast(CompanyJson.class)
                        .map(CompanyMapper::toEntity);
    }

    protected Flux<InfoCard> getInfoCardsFlux(Flux<?> objects) {
        return objects
                        .filter(Objects::nonNull)
                        .filter(i -> i.getClass().equals(InfoCardJson.class))
                        .cast(InfoCardJson.class)
                        .map(InfocardMapper::toEntity);
    }
}
