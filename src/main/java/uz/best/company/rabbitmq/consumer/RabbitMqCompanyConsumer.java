package uz.best.company.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import uz.best.company.constants.MqConstants;
import uz.best.company.service.CompanyService;
import uz.best.company.dto.payload.CompanyTINPayload;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMqCompanyConsumer implements MqConstants {

    private final CompanyService companyService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(durable = "true", value = COMPANY_TIN_SYNC_QUEUE),
                    exchange = @Exchange(durable = "false", value = DELAY_EXCHANGE_NAME, delayed = "true"),
                    key = COMPANY_TIN_SYNC_QUEUE), containerFactory = "rabbitListenerContainerFactoryMin"
    )
    public void syncCompanyTins(CompanyTINPayload payload) {
        try {
            companyService
                    .getCompanyData(payload.getTin())
                    .block();
        } catch (Exception e) {
            log.error("Sync company tin queue error: ", e);
            throw new AmqpRejectAndDontRequeueException("Sync company by tin queue error: " + e.getMessage());
        }
    }
}
