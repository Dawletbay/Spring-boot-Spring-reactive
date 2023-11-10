package com.best.company.crons;

import com.best.company.rabbitmq.producer.RabbitMqCompanyProducer;
import com.best.company.repository.CompanyRepository;
import com.best.company.service.BankService;
import com.best.company.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 27.07.2023 17:23
 */

@Component
@Slf4j
@AllArgsConstructor
public class CronJob {

    BankService bankService;
    CompanyRepository companyRepository;
    RabbitMqCompanyProducer rabbitMqCompanyProducer;
    CompanyService companyService;


    @Scheduled(fixedRate = 7776000000l)
    public void syncBanks() {
        bankService
                .sync()
                .block();
    }

    @Scheduled(fixedRate = 7776000000l)
    public void syncBestCompanyTins() {
        companyService
                .syncCompanyTins()
                .block();
    }
}
