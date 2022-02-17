package com.assignment2.exceptions;

/**
 * Contains the Coin not Found Exception.
 */
public class CoinNotFoundException extends Exception
{
    /**
     * @param str Require String value i.e. Message to throw when CoinNotFoundException Exception occur.
     */
    public CoinNotFoundException(String str)
    {
        super(str);
    }
}