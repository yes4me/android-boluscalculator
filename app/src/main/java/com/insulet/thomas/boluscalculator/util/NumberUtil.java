package com.insulet.thomas.boluscalculator.util;

/**
 * Created by thomas on 6/11/2017.
 */

public class NumberUtil {
    // NumberUtil of digits before the comma
    public static int numberInteger(double number) {
        int decimal = (int) number;
        return String.valueOf(decimal).length();
    }

    // Number of digits after the comma
    public static int numberFractional(double number) {
        String value = String.valueOf(number);
        value = value.replaceAll(".0$", "");
        int index = value.indexOf('.');
        return (index < 0) ? 0 : value.length() - index - 1;
    }

    public static double round(double value, double precision) {
        double numberFractional = Math.pow(10, numberFractional(precision));
        double factor = Math.floor(value / precision);
        double b = Math.floor(factor * precision * numberFractional);
        return b / numberFractional;
    }
}
