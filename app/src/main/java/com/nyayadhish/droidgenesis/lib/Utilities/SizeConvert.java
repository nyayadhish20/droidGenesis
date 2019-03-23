package com.nyayadhish.droidgenesis.lib.Utilities;

/**
 * Created by root on 16/11/17.
 */

public class SizeConvert {
    //1000,000 Bytes = 1 MB
    public static double ByteToMB(double bytes){
        return bytes/1000000;
    }

    //+00
    public static double KiloByteToMB(double kiloBytes) {
        return kiloBytes/100000000;
    }
}
