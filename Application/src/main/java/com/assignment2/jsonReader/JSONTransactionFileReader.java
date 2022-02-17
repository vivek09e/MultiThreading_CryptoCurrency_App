package com.assignment2.jsonReader;

import com.assignment2.enums.TransactionType;
import com.assignment2.exceptions.TransactionTypeNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

/**
 * Contains all the Functionalities of the JSON Object Reader.
 */
public class JSONTransactionFileReader {

    /**
     * @param path Path where file is located.
     * @return JSONArray have all the transactions in the file.
     */
    public static JSONArray JSONFileReader(String path) {
        JSONParser jsonParser = new JSONParser();
        JSONArray transaction = new JSONArray();
        try (FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);
            transaction = (JSONArray) obj;
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return transaction;
    }

    /**
     * @param transaction Require JSONObject to parse it.
     * @return Type of the Transaction in the JSONObject.
     * @throws TransactionTypeNotFoundException when type is not among the 4 transaction type
     * i.e. BUY , SELL, UPDATE_PRICE, ADD_VOLUME.
     */
    public static @Nullable TransactionType parseTransactionObjectType(@NotNull JSONObject transaction)
            throws TransactionTypeNotFoundException {
        String type = (String) transaction.get("type");
        if (type.equals("SELL"))
            return TransactionType.SELL;
        else if (type.equals("BUY"))
            return TransactionType.BUY;
        else if (type.equals("UPDATE_PRICE"))
            return TransactionType.UPDATE_PRICE;
        else if (type.equals("ADD_VOLUME"))
            return TransactionType.ADD_VOLUME;
        else
            throw new TransactionTypeNotFoundException("Invalid Transaction Type!");
    }

    /**
     * @param transaction Require a JSONObject of Transaction having both type and Data.
     * @return JSONObject having the data part of the Transaction JSONObject.
     */
    public static JSONObject parseTransactionObjectDetails(@NotNull JSONObject transaction) {
        return (JSONObject) transaction.get("data");
    }

}
