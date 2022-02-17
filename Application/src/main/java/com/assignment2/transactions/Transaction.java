package com.assignment2.transactions;

import com.assignment2.csvReader.ReadCoinCSV;
import com.assignment2.csvReader.ReadTraderCSV;
import com.assignment2.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Parent class for all kind of transaction.
 */
public class Transaction extends Thread {
    private String transactionId;
    /**
     * ConcurrentHashMap that contains all the coin on the basis of coin Symbol.
     */
    public static ConcurrentHashMap<String, Coin> symbolWiseCoinMap= new ConcurrentHashMap<>();
    /**
     * ArrayList that contains all the coin.
     */
    public static List<Coin> allCoinList= new ArrayList<>();
    /**
     *  ConcurrentHashMap that contains all the Trader on the basis of wallet Address.
     */
    public static ConcurrentHashMap<String,Trader> traderMap =new ConcurrentHashMap<>();
    /**
     * ArrayList that contains all the Trader.
     */
    public static List<Trader> allTraderList= new ArrayList<>();

    /**
     * @return Return String value i.e. id of the Transaction.
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId Require String to set the value of the Transaction ID.
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    /**
     * Read all the data from the CSV using the CSVReader classes and store the value in the
     * symbolWiseCoinMap, allCoinList, traderMap, and allTraderList
     */
    public static void readData(){
        ReadCoinCSV.readCoinDataFromCSV("src/main/java/com/assignment2/data/coins.csv",symbolWiseCoinMap,allCoinList);
        ReadTraderCSV.readTraderDataFromCSV("src/main/java/com/assignment2/data/traders.csv",traderMap,allTraderList);

    }
}
