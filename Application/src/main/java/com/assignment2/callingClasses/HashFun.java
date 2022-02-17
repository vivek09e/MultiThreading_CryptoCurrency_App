package com.assignment2.callingClasses;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Introducing delay mimicking complex
 * calculation being performed.
 */
public class HashFun {
    /**
     * @return Mimicking the delay due to transaction.
     */
    public static @NotNull String getBlockHash() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        Random rnd = new Random();
        for(double i=0;i<199999999; i++){
            i = i;
        }
        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            transactionHash.append(SALTCHARS.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }

}
