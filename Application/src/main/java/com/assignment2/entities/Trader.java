package com.assignment2.entities;

import com.assignment2.transactions.Transaction;

import java.util.*;
import java.util.Objects;

/**
 * Contains all the details of a Trader including Firstname, lastName, FullName,
 * Wallet Address, phone number, expense, releasedRevenue, unReleasedRevenue,
 * profit and coinOwnByTheTrader.
 */
public class Trader {
    private String firstName;
    private String lastName;
    private String phone;
    private String walletAddress;
    private String fullName = "";
    /**
     * Amount of money spent by the trader while purchasing a coin (i.e. price * quantity of the coin bought)
     */
    private Double expense = 0.0;
    /**
     * Total Money generated by the trader while selling the coin (i.e. price * quantity of the coin bought)
     */
    private Double releasedRevenue = 0.0;
    /**
     * Total profit that a customer made throughout the transactions.
     */
    private Double profit = 0.0;
    /**
     * Total money generated by the trader due the coin hold by him/her
     * (i.e. sum of all the money generated by holding the coins)
     */
    private Double unReleasedRevenue = 0.0;
    /**
     * HashMap Containing all the coin Own by the Trader on the basis of their symbol.
     */
    public HashMap<String, Coin> coinOwnByTheTrader = new HashMap<>();


    /**
     * @return Return String value i.e. FirstName of the Trader.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName Require String Value to set the firstName of the Trader.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return Return String Value i.e. LastName of the Trader.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName Require String value to set the lastName of the Trader.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return Returns String value i.e. phone number of the Trader.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone Require String value to set the phone number of the Trader.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return Return String value i.e. the wallet Address of the Trader.
     */
    public String getWalletAddress() {
        return walletAddress;
    }

    /**
     * @param walletAddress Require String value to set the wallet address of the Trader.
     */
    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    /**
     * @return Returns String value i.e. the full name of the Trader.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * @param fullName Require String Value to set the Full name of the Trader.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return Returns Double value i.e. the Total Expense of the Trader while purchasing the coins.
     */
    public Double getExpense() {
        return expense;
    }

    /**
     * @param expense Require Double value to set the expense of the Trader.
     */
    public void setExpense(Double expense) {
        this.expense = expense;
    }

    /**
     * @return Return Double value i.e. the total released Revenue of the trader
     * while selling the coin own by hm/her.
     */
    public Double getReleasedRevenue() {
        return releasedRevenue;
    }

    /**
     * @param releasedRevenue Require Double Value to set the Released Revenue of the Trader.
     */
    public void setReleasedRevenue(Double releasedRevenue) {
        this.releasedRevenue = releasedRevenue;
    }


    /**
     * @return Returns Double value i.e.
     * total profit= releasedRevenue+ unreleasedRevenue -expense;
     */
    public Double getProfit() {
        return getReleasedRevenue() + getUnReleasedRevenue() - getExpense();
    }

    /**
     * Set Profit of a trader
     * i.e. total profit= releasedRevenue+ unreleasedRevenue -expense;
     */
    public void setProfit() {
        this.profit = getReleasedRevenue() + getUnReleasedRevenue() - getExpense();
    }

    /**
     * @return Returns Double value i.e. total money owned by the trader through owning the coins.
     */
    public Double getUnReleasedRevenue() {
        double val = 0.0;
        for (String coinSymbol : coinOwnByTheTrader.keySet()) {
            val += Transaction.symbolWiseCoinMap.get(coinSymbol).getPrice()
                    * coinOwnByTheTrader.get(coinSymbol).getVolume();
        }
        this.unReleasedRevenue = val;
        return unReleasedRevenue;
    }

    /**
     * @param o Trader object which we want to compare current Trader with.
     * @return Returns boolean value true if the Trader have all the properties(like name,wallet Address etc.) similar.
     * else return false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trader)) return false;
        Trader trader = (Trader) o;
        return firstName.equals(trader.firstName) && lastName.equals(trader.lastName)
                && phone.equals(trader.phone) && walletAddress.equals(trader.walletAddress);
    }

    /**
     * @return Return int value generated by the hash function.
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phone, walletAddress);
    }

    /**
     * @return Returns String value containing all the details of the Trader.
     */
    @Override
    public String toString() {
        return "Trader{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", walletAddress='" + walletAddress + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", expense=" + expense +
                ", releasedRevenue=" + getReleasedRevenue() +
                ", profit=" + getProfit() +
                ", unReleasedRevenue=" + getUnReleasedRevenue() +
                ", coinOwnByTheTrader=" + coinOwnByTheTrader +
                '}';
    }
}
