package com.insulet.thomas.boluscalculator.util;

import java.text.DecimalFormat;

/**
 * Created by thomas on 6/11/2017.
 */

public class StringUtil {
    public static void print(String text, double value) {
        String format = "%-25s:%6s\n";
        System.out.format(format, text, value);
    }

    public static void print(String text, boolean value) {
        String format = "%-25s:%6s\n";
        System.out.format(format, text, value);
    }

    public static String simplify(double value) {
        String text = String.valueOf(value);
        return simplify(text);
    }
    public static String simplify(double number, int digits) {
        String text = String.valueOf(number);
        String[] result = text.split("\\.");
        int length = result[1].length();

        if (length >= digits) {
            result[1] = result[1].substring(0, digits);
        } else {
            StringBuilder b = new StringBuilder(result[1]);
            for (int i=0; i<digits-length; i++)
                result[1] += "0";
        }
        return result[0] +"."+ result[1];
    }

    public static String simplify(String text)
    {
        text = text.trim();
        return text.replaceAll("\\.[0]*$", "");
    }

    /* =============================================================================================
    FOR TESTING PURPOSE
    ============================================================================================= */

    public static void main(String[] args) {
        System.out.println("Hello");
        double d = 1;
        System.out.println( simplify(d, 2) );

        d /=2;
        System.out.println( simplify(d, 2) );

        d /=3;
        System.out.println( simplify(d, 2) );
    }
}
