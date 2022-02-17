package com.assignment2.operationEntities;

import com.assignment2.entities.Coin;
import com.assignment2.enums.TransactionStatus;
import com.assignment2.exceptions.CoinNotFoundException;
import com.assignment2.transactions.Transaction;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

/**
 * Act as a container for UpdatePrice transaction i.e. it holds all the properties that a UpdatePrice Transaction have.
 */
public class UpdatePrice {
    private double price;
    private Coin coin;
    private TransactionStatus status;

    /**
     * @param obj Require a transaction JSONObject to parse and
     *            set all the data of UpdatePrice like Coin, Price .
     * @throws CoinNotFoundException When coin is not present on which UpdatePrice transaction will be done.
     */
    public UpdatePrice(@NotNull JSONObject obj) throws CoinNotFoundException {
        if (Transaction.symbolWiseCoinMap.containsKey((String) obj.get("coin")))
            this.coin = Transaction.symbolWiseCoinMap.get((String) obj.get("coin"));
        else
            throw new CoinNotFoundException("Coin not Found for the given Symbol: " + obj.get("coin"));
        this.price = (Double) obj.get("price");
        this.status = TransactionStatus.IN_PROGRESS;
    }

    /**
     * @return Return the Status of the UpdatePrice transaction.
     */
    public TransactionStatus getStatus() {
        return status;
    }

    /**
     * @param status Require TransactionStatus enum value to set the UpdatePrice transaction status.
     */
    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    /**
     * @return Return double value i.e. the price which is need to be updated for the given coin.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price Require double value to set price which is need to be updated for the given coin.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return Returns Coin object on which UpdatePrice transaction will be done.
     */
    public Coin getCoin() {
        return coin;
    }

    /**
     * @param coin Require Coin object on which UpdatePrice transaction will be done.
     */
    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    /**
     * @return Return String that contains all the Details of the current updatePrice Transaction.
     */
    @Override
    public String toString() {
        return "UpdatePrice{" +
                "price=" + price +
                ", coin=" + coin +
                '}';
    }
}
