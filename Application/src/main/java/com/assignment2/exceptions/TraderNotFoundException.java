package com.assignment2.exceptions;

/**
 * Contains the TraderNotFoundException Exception
 */
public class TraderNotFoundException extends Exception
{
    /**
     * @param str Require String value i.e. Message to throw when TraderNotFoundException Exception occur.
     */
    public TraderNotFoundException(String str)
    {
        super(str);
    }
}