package com.assignment2.csvReader;

import com.assignment2.entities.*;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.*;

/**
 * Class containing function to read data of Trader from the CSV.
 */
public class ReadTraderCSV {
    /**
     * @param path       Path of the file where it is located.
     * @param tradersMap ConcurrentHashMap to store the Trader data on the basis of wallet address from CSV.
     * @param allTrader  List to store all the Trader from CSV.
     */
    public static void readTraderDataFromCSV(String path, Map<String, Trader> tradersMap, List<Trader> allTrader) {
        String line = "";
        int lineNumber = 0;
        try (FileReader filereader = new FileReader(path)) {
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (lineNumber != 0) {
                    Trader trader = new Trader();
                    trader.setFirstName(nextRecord[1]);
                    trader.setLastName(nextRecord[2]);
                    trader.setPhone(nextRecord[3]);
                    trader.setWalletAddress(nextRecord[4]);
                    tradersMap.put(trader.getWalletAddress(), trader);
                    allTrader.add(trader);
                }
                lineNumber++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
