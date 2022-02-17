package com.assignment2.comparators;

import com.assignment2.entities.Trader;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * Comparator to compare trader on the basis of profit.
 */
public class SortByProfit implements Comparator<Trader> {

    /**
     * @param trader1 first Trader
     * @param trader2 Second Trader
     * @return Return 1.0 if trader1's profit greater than trader2's profit else return 0.0;
     */
    public int compare(@NotNull Trader trader1, @NotNull Trader trader2) {
        return Double.compare(trader1.getProfit(), trader2.getProfit());

    }
}