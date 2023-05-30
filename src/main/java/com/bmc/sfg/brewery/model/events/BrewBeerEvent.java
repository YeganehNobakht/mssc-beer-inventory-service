package com.bmc.sfg.brewery.model.events;

import lombok.NoArgsConstructor;

/**
 * @author Masoumeh Yeganeh
 * @created 24/05/2023
 */
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
