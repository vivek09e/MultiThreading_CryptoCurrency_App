package com.assignment2.callingClasses;

import com.assignment2.enums.TransactionType;
import com.assignment2.exceptions.*;
import com.assignment2.jsonReader.JSONTransactionFileReader;
import com.assignment2.menufunctionlities.MenuDriven;
import com.assignment2.operationEntities.*;
import com.assignment2.transactions.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TransactionPerforming {

    public static void main(String[] args) {

        try {
            Transaction.readData();
            Thread menuDriven = new MenuDriven();
            menuDriven.start();
            JSONArray transaction = JSONTransactionFileReader.JSONFileReader
                    ("src/main/java/com/assignment2/data/small_transaction.json");
            for (Object obj : transaction) {
                JSONObject temp = (JSONObject) obj;
                if (JSONTransactionFileReader.parseTransactionObjectType(temp) == TransactionType.SELL) {
                    try {
                        Sell sell = new Sell(JSONTransactionFileReader
                                .parseTransactionObjectDetails(temp));
                        Thread sellCoinTransaction = new SellCoinTransaction(sell);
                        sellCoinTransaction.start();
                    } catch (CoinNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (JSONTransactionFileReader.parseTransactionObjectType(temp) == TransactionType.UPDATE_PRICE) {
                    try {
                        UpdatePrice updatePrice = new UpdatePrice(JSONTransactionFileReader
                                .parseTransactionObjectDetails(temp));
                        Thread updatePriceTransaction = new UpdatePriceTransaction(updatePrice);
                        updatePriceTransaction.start();
                    } catch (CoinNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (JSONTransactionFileReader.parseTransactionObjectType(temp) == TransactionType.ADD_VOLUME) {
                    try {
                        AddVolume addVolume = new AddVolume(JSONTransactionFileReader
                                .parseTransactionObjectDetails(temp));
                        Thread addVolumeTransaction = new AddVolumeTransaction(addVolume);
                        addVolumeTransaction.start();
                    } catch (CoinNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (JSONTransactionFileReader
                        .parseTransactionObjectType(temp) == TransactionType.BUY) {
                    try {
                        Buy buy = new Buy(JSONTransactionFileReader.parseTransactionObjectDetails(temp));
                        Thread buyCoinTransaction = new BuyCoinTransaction(buy);
                        buyCoinTransaction.start();
                    } catch (CoinNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                }
            }
        } catch (TransactionTypeNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
