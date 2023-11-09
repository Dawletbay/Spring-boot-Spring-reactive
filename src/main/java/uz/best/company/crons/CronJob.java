package uz.best.company.crons;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.best.company.rabbitmq.producer.RabbitMqCompanyProducer;
import uz.best.company.repository.CompanyRepository;
import uz.best.company.service.BankService;
import uz.best.company.service.CompanyService;

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
                .syncCTSCompanyTins()
                .block();
    }
}
