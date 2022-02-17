package com.assignment2.exceptions;

/**
 * Contains the Not in Range Exception.
 */
public class NotInRangeException extends Exception {

    /**
     * @param str Require String value i.e. Message to throw when NotInRangeException Exception occur.
     */
    public NotInRangeException(String str) {
        super(str);
    }
}
