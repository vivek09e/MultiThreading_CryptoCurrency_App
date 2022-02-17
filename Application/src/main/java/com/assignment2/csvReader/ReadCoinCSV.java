package com.assignment2.csvReader;

import com.assignment2.entities.Coin;
import com.assignment2.enums.CoinStatus;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.*;

/**
 * Class containing function to read data of coin from the CSV.
 */
public class ReadCoinCSV {
    /**
     * @param path               Path of the file where it is located.
     * @param symbolWiseCoinsMap ConcurrentHashMap to store the Coin data on the basis of coin Symbol from CSV.
     * @param allCoinList        List to store all the coin from CSV.
     */
    public static void readCoinDataFromCSV(String path, Map<String, Coin> symbolWiseCoinsMap, List<Coin> allCoinList) {
        String line = "";
        int lineNumber = 0;
        try (FileReader filereader = new FileReader(path)) {
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (lineNumber != 0) {
                    Coin coin = new Coin();
                    coin.setRank(Integer.parseInt(nextRecord[1]));
                    coin.setName(nextRecord[2]);
                    coin.setSymbol(nextRecord[3]);
                    coin.setPrice(Double.parseDouble(nextRecord[4]));
                    coin.setVolume(Long.parseLong(nextRecord[5]));
                    coin.setStatus(CoinStatus.AVAILABLE);
                    symbolWiseCoinsMap.put(coin.getSymbol(), coin);
                    allCoinList.add(coin);
                }
                lineNumber++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
