package com.assignment2.operationEntities;

import com.assignment2.entities.Coin;
import com.assignment2.enums.TransactionStatus;
import com.assignment2.exceptions.CoinNotFoundException;
import com.assignment2.transactions.Transaction;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

/**
 * Act as a container for AddVolume transaction i.e. it holds all the properties that a addVolume Transaction have.
 */
public class AddVolume  {
    private long volume;
    private Coin coin;
    private TransactionStatus status;

    /**
     * @param obj Require a transaction JSONObject to parse and set all the data of the AddVolume like Coin and quantity.
     * @throws CoinNotFoundException When coin is not present on which addVolume transaction will be done.
     */
    public AddVolume(@NotNull JSONObject obj) throws CoinNotFoundException {
        this.volume=(Long) obj.get("volume");
        if(Transaction.symbolWiseCoinMap.containsKey((String) obj.get("coin")))
            this.coin= Transaction.symbolWiseCoinMap.get((String) obj.get("coin"));
        else
            throw new CoinNotFoundException("Coin not Found for the given Symbol: "+obj.get("coin"));
        status=TransactionStatus.IN_PROGRESS;

    }

    /**
     * @return Return the Status of the addVolume transaction
     */
    public TransactionStatus getStatus() {
        return status;
    }

    /**
     * @param status Require TransactionStatus enum value to set the current transaction status.
     */
    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    /**
     * @return Return long Value i.e. the volume need to be added in the coin.
     */
    public long getVolume() {
        return volume;
    }

    /**
     * @param volume Require long value to set the volume that need to be added in the coin.
     */
    public void setVolume(long volume) {
        this.volume = volume;
    }

    /**
     * @return Returns Coin object on which transaction will be done.
     */
    public Coin getCoin() {
        return coin;
    }

    /**
     * @param coin Require Coin object on which transaction will be done.
     */
    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    /**
     * @return Returns a String having all the Details of the current AddVolume Transaction.
     */
    @Override
    public String toString() {
        return "AddVolume{" +
                "volume=" + volume +
                ", coin=" + coin +
                ", status='" + status + '\'' +
                '}';
    }
}
