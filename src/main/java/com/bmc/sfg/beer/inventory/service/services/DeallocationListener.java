package com.bmc.sfg.beer.inventory.service.services;

import com.bmc.sfg.beer.inventory.service.config.JmsConfig;
import com.bmc.sfg.brewery.model.events.DeallocateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Masoumeh Yeganeh
 * @created 26/06/2023
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class DeallocationListener {

    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateOrderRequest request){
        allocationService.deallocateOrder(request.getBeerOrderDto());
    }
}
