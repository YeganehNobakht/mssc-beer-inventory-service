package com.bmc.sfg.beer.inventory.service.services;

import com.bmc.sfg.beer.inventory.service.config.JmsConfig;
import com.bmc.sfg.brewery.model.events.AllocateOrderRequest;
import com.bmc.sfg.brewery.model.events.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Masoumeh Yeganeh
 * @created 26/06/2023
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationListener {
    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(AllocateOrderRequest request){
        AllocateOrderResult.AllocateOrderResultBuilder builder = AllocateOrderResult.builder();
        builder.beerOrderDto(request.getBeerOrderDto());

        try{
            Boolean allocationResult = allocationService.allocateOrder(request.getBeerOrderDto());

            if (allocationResult){
                builder.pendingInventory(false);
            } else {
                builder.pendingInventory(true);
            }

            builder.allocationError(false);
        } catch (Exception e){
            log.error("Allocation failed for Order Id:" + request.getBeerOrderDto().getId());
            builder.allocationError(true);
        }

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE,
                builder.build());

    }
}