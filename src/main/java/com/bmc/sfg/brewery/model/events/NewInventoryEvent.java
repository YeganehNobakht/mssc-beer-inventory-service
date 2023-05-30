package com.bmc.sfg.brewery.model.events;

import lombok.NoArgsConstructor;

/**
 * @author Masoumeh Yeganeh
 * @created 24/05/2023
 */
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
