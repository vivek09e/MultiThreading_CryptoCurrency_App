package com.assignment2.exceptions;

/**
 * Contains TransactionTypeNotFoundException Exception
 */
public class TransactionTypeNotFoundException extends Exception{

    /**
     * @param str Require String value i.e. Message to throw when TransactionTypeNotFoundException Exception occur.
     */
    public TransactionTypeNotFoundException(String str)
    {
        super(str);
    }
}
