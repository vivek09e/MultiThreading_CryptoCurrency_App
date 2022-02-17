package com.assignment2.operationEntities;

import com.assignment2.entities.Coin;
import com.assignment2.enums.TransactionStatus;
import com.assignment2.exceptions.CoinNotFoundException;
import com.assignment2.transactions.Transaction;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

/**
 * Act as a container for sell transaction i.e. it holds all the properties that a sell Transaction have.
 */
public class Sell {
    private Coin coin;
    private long quantity;
    private String walletAddress;
    private TransactionStatus status;

    /**
     * @param obj Require a transaction JSONObject to parse and
     *            set all the data of sell like Coin, quantity and walletAddress.
     * @throws CoinNotFoundException When coin is not present on which sell transaction will be done.
     */
    public Sell(@NotNull JSONObject obj) throws CoinNotFoundException {
        if (Transaction.symbolWiseCoinMap.containsKey((String) obj.get("coin")))
            this.coin = Transaction.symbolWiseCoinMap.get((String) obj.get("coin"));
        else
            throw new CoinNotFoundException("Coin not Found for the given Symbol: " + obj.get("coin"));
        this.walletAddress = (String) obj.get("wallet_address");
        this.quantity = (Long) obj.get("quantity");
        this.status = TransactionStatus.IN_PROGRESS;
    }

    /**
     * @return Return the Status of the sell transaction
     */
    public TransactionStatus getStatus() {
        return status;
    }

    /**
     * @param status Require TransactionStatus enum value to set the sell transaction status.
     */
    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    /**
     * @return Returns Coin object on which sell transaction will be done.
     */
    public Coin getCoin() {
        return coin;
    }

    /**
     * @param coin Require Coin object on which sell transaction will be done.
     */
    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    /**
     * @return Return long Value i.e. the volume of coin that will sell.
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * @param quantity Require long value to set the quantity of coin that will sell.
     */
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    /**
     * @return Return String value i.e. the walletAddress of the trader performing sell operation.
     */
    public String getWalletAddress() {
        return walletAddress;
    }

    /**
     * @param walletAddress Require String value to set the walletAddress of the trader performing sell operation.
     */
    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    /**
     * @return Return String that contains all the Details of the current sell Transaction.
     */
    @Override
    public String toString() {
        return "Sell{" +
                "coin=" + coin +
                ", quantity=" + quantity +
                ", walletAddress='" + walletAddress + '\'' +
                ", status=" + status +
                '}';
    }
}
