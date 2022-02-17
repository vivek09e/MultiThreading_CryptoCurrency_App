package com.assignment2.comparators;

import com.assignment2.entities.Coin;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * Comparator to compare the coin on the basis of price.
 */
public class SortByPrice implements Comparator<Coin> {


    /**
     * @param coin1 first coin
     * @param coin2 Second coin
     * @return return 1 if coin1 price is greater than coin2 price else return 0.
     */
    public int compare(@NotNull Coin coin1, @NotNull Coin coin2) {
        return Double.compare(coin1.getPrice(), coin2.getPrice());

    }
}
