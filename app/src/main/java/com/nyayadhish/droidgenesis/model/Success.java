package com.nyayadhish.droidgenesis.model;

/**
 * Created by Nikhil Nyayadhish on 26 Mar 2019 at 12:02.
 */
public class Success {

    String status;
    String message;
    String errorCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
