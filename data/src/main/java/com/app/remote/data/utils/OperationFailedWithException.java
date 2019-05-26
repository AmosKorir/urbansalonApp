package com.app.remote.data.utils;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
public class OperationFailedWithException extends Exception{
    public OperationFailedWithException(String message) {
        super(message);
    }

    public OperationFailedWithException() {
    }
}
