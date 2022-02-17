package com.assignment2.entities;

import com.assignment2.enums.CoinStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Contains the all the details of a coin including rank, symbol, name, price and the volume.
 */
public class Coin {

    private int rank;
    private String name;
    private String symbol;
    private double price;
    private long volume;
    private CoinStatus status;

    /**
     * @return Gives the Status of the coin.
     */
    public CoinStatus getStatus() {
        return status;
    }

    /**
     * @param coinStatus Require CoinStatus enum value to set the value of the status.
     */
    public void setStatus(CoinStatus coinStatus) {
        this.status = coinStatus;
    }

    /**
     * @return return integer i.e. rank of the coin in the Market.
     */
    public int getRank() {
        return rank;
    }

    /**
     * @param rank Require integer value to set the rank of the coin;
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * @return Return String i.e. name of the coin.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Require String value to set the name of the coin;
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Return String value i.e. current Coin's symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @param symbol Require String value to set the symbol of the coin.
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * @return Return double value i.e. price of the current coin.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price Require double value to set the price of the coin.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return Return long value i.e. volume of the current coin.
     */
    public long getVolume() {
        return volume;
    }

    /**
     * @param volume Require long value to set the volume of the coin.
     */
    public void setVolume(long volume) {
        this.volume = volume;
    }

    /**
     * @param o Coin object which we want to compare current coin with.
     * @return Return boolean value true if the coins have all the properties(like name,price etc.) similar.
     * else return false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coin)) return false;
        Coin coin = (Coin) o;
        return rank == coin.rank && Double.compare(coin.price, price) == 0
                && volume == coin.volume && Objects.equals(name, coin.name)
                && Objects.equals(symbol, coin.symbol);
    }

    /**
     * @return Returns the integer value that will be calculated on the basis of price and volume.
     */
    @Override
    public int hashCode() {
        return (int) (price / volume);
    }

    /**
     * @param coin Coin object which we want to compare current coin with.
     * @return if the hashcode of both the coins are equal then it will return 0 if current
     * coin have greater has code than other return 1 else return -1;
     */
    public int compare(@NotNull Coin coin) {
        if (this.hashCode() > coin.hashCode())
            return 1;
        else if (this.hashCode() < coin.hashCode())
            return -1;
        else
            return 1;
    }

    /**
     * @return Return String which will have all the details of the current coin.
     */
    @Override
    public String toString() {
        return "Coin{" +
                "rank=" + rank +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                '}';
    }
}
