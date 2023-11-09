package uz.best.company.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import uz.best.company.config.ApplicationProperties;
import uz.best.company.rabbitmq.producer.RabbitMqCompanyProducer;
import uz.best.company.service.LocalizationService;

import java.util.Collections;
import java.util.function.Consumer;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 31.07.2023 15:31
 */
@Component
@RequiredArgsConstructor
public class BaseClient {

    @Autowired
    protected WebClient webClient;
    @Autowired
    protected ApplicationProperties applicationProperties;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected LocalizationService localizationService;
    @Autowired
    protected RabbitMqCompanyProducer rabbitMqCompanyProducer;

    public static Consumer<HttpHeaders> getHeaders(String username, String password) {
        return httpHeaders -> {
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password))
                httpHeaders.setBasicAuth(username, password);
        };
    }
}
