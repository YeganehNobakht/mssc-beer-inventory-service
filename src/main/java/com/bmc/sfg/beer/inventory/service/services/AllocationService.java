package com.bmc.sfg.beer.inventory.service.services;

import com.bmc.sfg.brewery.model.BeerOrderDto;

/**
 * @author Masoumeh Yeganeh
 * @created 26/06/2023
 */
public interface AllocationService {

    Boolean allocateOrder(BeerOrderDto beerOrderDto);
}
