package com.insulet.thomas.boluscalculator.util;

/**
 * Created by thomas on 6/11/2017.
 */

public class MyString {
    public static void print(String text, double value) {
        String format = "%-25s:%6s\n";
        System.out.format(format, text, value);
    }

    public static void print(String text, boolean value) {
        String format = "%-25s:%6s\n";
        System.out.format(format, text, value);
    }

    public static String simplify(String text) {
        return text.replaceAll("\\.[0]*$", "");
    }
    public static String simplify(double value) {
        String text = String.valueOf(value);
        return simplify(text);
    }
}
