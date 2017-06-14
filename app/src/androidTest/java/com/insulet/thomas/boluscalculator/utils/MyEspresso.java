package com.insulet.thomas.boluscalculator.utils;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.view.KeyEvent;

import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;

/**
 * Created by thomas on 6/14/2017.
 */

public class MyEspresso {
    public static void enterText(ViewInteraction viewInteraction, String text) {
        //  viewInteraction.perform(clearText());
        viewInteraction.perform( typeText(text) );
        viewInteraction.perform(ViewActions.pressKey( KeyEvent.KEYCODE_ENTER ));
        viewInteraction.perform(closeSoftKeyboard());
    }
}
