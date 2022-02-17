package com.assignment2.menufunctionlities;

import com.assignment2.comparators.*;
import com.assignment2.entities.*;
import com.assignment2.exceptions.CoinNotFoundException;
import com.assignment2.exceptions.NotInRangeException;
import com.assignment2.exceptions.TraderNotFoundException;
import com.assignment2.transactions.Transaction;

import java.util.*;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;


/**
 * Contains the 5 menu function implementation.
 */
public class MenuFunctions {

    /**
     * Search and print the coin details on the basis of the Name or Symbol.
     * @throws CoinNotFoundException when coin is not present in the symbolWiseCoinMap or allCoinList.
     */
    public static String searchCoin() throws CoinNotFoundException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Want to proceed with Symbol(Y/N): ");
        String val = sc.nextLine();
        if (val.toUpperCase().equals("Y")) {
            System.out.println("Enter the Symbol: ");
            String coinSymbol = sc.nextLine();
            if(!Transaction.symbolWiseCoinMap.containsKey(coinSymbol.toUpperCase()))
                throw  new CoinNotFoundException("Coin not found with the given Symbol: "+coinSymbol);
            return Transaction.symbolWiseCoinMap.get(coinSymbol.toUpperCase()).toString();
        } else {
            System.out.println("Enter the Name: ");
            String coinName = sc.nextLine();
            for(Coin coin:Transaction.allCoinList){
                if(coin.getName().equalsIgnoreCase(coinName)) {
                    return coin.toString();
                }
            }
            throw new CoinNotFoundException("Coin not found with given Name: "+coinName);
        }
    }

    /**
     * @param n Require int value to print Top N coins on the basis of Price.
     * @throws NotInRangeException When required input is greater than the total number of coin.
     */
    public static void displayTopNCoins(int n) throws NotInRangeException{
        if(n>Transaction.symbolWiseCoinMap.size())
            throw new NotInRangeException("Number of coin are less than the number of expected Result!");
        HashMap<String, Coin> temp = Transaction.symbolWiseCoinMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue(new SortByPrice())))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        int curr = 1;
        System.out.println("Top " + n + " Coins based on the Price: ");
        for (String symbol : temp.keySet()) {
            if (curr == 51)
                break;
            System.out.println("Coin " + curr + " :" + temp.get(symbol).toString());
            curr++;
        }
    }

    /**
     * @param name Require String value to print the portfolio of the required Trader.
     * @throws TraderNotFoundException When the Required Trader is not present in the data.
     */
    public static void displayPortfolio(String name) throws TraderNotFoundException {
        int numberOfTrader=Transaction.allTraderList.size();
        for(int i=0;i<numberOfTrader;i++){
            if(name.equalsIgnoreCase(Transaction.allTraderList.get(i).getFullName())) {
                System.out.println(Transaction.allTraderList.get(i).toString());
                return;
            }
        }
        throw new TraderNotFoundException("Trader not found!");
    }

    /**
     * @param name Require String value to print the profit/loss of the required Trader.
     * @throws TraderNotFoundException When the Required Trader is not present in the data.
     */
    public static void displayProfitLossOfTrader(String name) throws TraderNotFoundException {
        int numberOfTrader=Transaction.allTraderList.size();
        for(int i=0;i<numberOfTrader;i++){
            if(name.equalsIgnoreCase(Transaction.allTraderList.get(i).getFullName())) {
                System.out.println("Trader have made a profit of: "+Transaction.allTraderList.get(i).getProfit());
                return;
            }
        }
        throw new TraderNotFoundException("Trader not found!");
    }

    /**
     * @param n Require int value to display top and bottom N trader.
     * @throws NotInRangeException When required input is greater than total number of traders.
     */
    public static void topNTrader(int n) throws NotInRangeException {
        if(n>Transaction.allTraderList.size())
            throw new NotInRangeException("Number of Traders are less than the number of expected Result!");
        else{
            List<Trader> topTrader=Transaction.allTraderList.stream()
                    .sorted(Collections.reverseOrder(new SortByProfit()))
                    .limit(n).collect(Collectors.toList());
            List<Trader> bottomTrader=Transaction.allTraderList.stream().
                    sorted(new SortByProfit()).limit(n).collect(Collectors.toList());
            System.out.println("Top "+n+" Traders!");
            for(Trader trader: topTrader){
                System.out.println(trader.toString());
            }
            System.out.println("Bottom "+n+" Traders!");
            for(Trader trader: bottomTrader){
                System.out.println(trader.toString());
            }
        }

    }
}
