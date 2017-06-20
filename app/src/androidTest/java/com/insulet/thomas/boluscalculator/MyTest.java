package com.insulet.thomas.boluscalculator;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import org.junit.runner.RunWith;

/**
 * Created by thomas on 6/20/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MyTest {
    /* =============================================================================================
    FOR TESTING PURPOSE
    ============================================================================================= */

    public static void main(String[] args) {
        UiObject textField = new UiObject(new UiSelector()
                .className("android.widget.edit_text_bg_calc_max").instance(1));

        System.out.println("Hello");
        Log.d("XXX", "==>"+ textField.toString());
    }
}
