package com.nyayadhish.droidgenesis.lib.Utilities;

/**
 * Created by root on 16/11/17.
 */

public class NumberManager {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
