package com.bmc.sfg.beer.inventory.service.services;

import com.bmc.sfg.beer.inventory.service.config.JmsConfig;
import com.bmc.sfg.beer.inventory.service.domain.BeerInventory;
import com.bmc.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import com.bmc.sfg.brewery.model.events.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author Masoumeh Yeganeh
 * @created 27/05/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event) {
        log.debug("Got Inventory: " + event.toString());

        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(event.getBeerDto().getId())
                .upc(event.getBeerDto().getUpc())
                .quantityOnHand(event.getBeerDto().getQuantityOnHand())
                .build());
    }
}
