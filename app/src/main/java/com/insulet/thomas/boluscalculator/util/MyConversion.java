package com.insulet.thomas.boluscalculator.util;

/**
 * Created by thomas on 6/17/2017.
 */

public class MyConversion {
    public static int convertToInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            return 0;
        }
    }
    public static double convertToDouble(String text) {
        try {
            return Double.parseDouble(text);
        } catch (Exception e) {
            return 0;
        }
    }
}
