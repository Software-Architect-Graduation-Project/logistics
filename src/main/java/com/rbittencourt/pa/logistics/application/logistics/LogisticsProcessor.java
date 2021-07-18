package com.rbittencourt.pa.logistics.application.logistics;

import com.rbittencourt.pa.logistics.infrastructure.ecommerceorder.EcommerceOrder;
import com.rbittencourt.pa.logistics.infrastructure.logistics.LogisticsRecord;
import com.rbittencourt.pa.logistics.infrastructure.logistics.LogisticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class LogisticsProcessor {

    @Autowired
    private LogisticsRepository logisticsRepository;

    @Autowired
    private KafkaTemplate<String, EcommerceOrder> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(LogisticsProcessor.class);

    @KafkaListener(topics = "stock_separated", containerFactory = "ecommerceOrderKafkaListenerContainerFactory")
    public void process(EcommerceOrder ecommerceOrder) {
        kafkaTemplate.send("organizing_logistics_started", ecommerceOrder);

        LogisticsRecord logisticsRecord = new LogisticsRecord(ecommerceOrder.getId(), ecommerceOrder.getClientId());
        logisticsRepository.save(logisticsRecord);

        kafkaTemplate.send("ready_to_delivery", ecommerceOrder);

        logger.info("Order " + ecommerceOrder.getId() + " is ready to delivery");
    }

}
