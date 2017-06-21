package com.insulet.thomas.boluscalculator.utils;

import android.support.test.espresso.ViewInteraction;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

/**
 * Created by thomas on 6/14/2017.
 */

public class MyEspresso {
    public static ViewInteraction getViewInteraction(String text) {
        return onView(withText(equalToIgnoringCase(text)));
    }

    /* =============================================================================================
    Text
    ============================================================================================= */

    public static void enterTextFromKeyboard(ViewInteraction viewInteraction, int number) {
        String text = String.valueOf(number);
        enterTextFromKeyboard(viewInteraction, text);
    }
    public static void enterTextFromKeyboard(ViewInteraction viewInteraction, double number) {
        String text = String.valueOf(number);
        enterTextFromKeyboard(viewInteraction, text);
    }
    public static void enterTextFromKeyboard(ViewInteraction viewInteraction, String text) {
        viewInteraction.perform( clearText() );
        viewInteraction.perform( typeText(text) );
        viewInteraction.perform( pressImeActionButton() );
        //viewInteraction.perform(replaceText(String.valueOf("Hello")), closeSoftKeyboard());
        // viewInteraction.perform(ViewActions.pressKey( KeyEvent.KEYCODE_ENTER ));
        viewInteraction.perform( closeSoftKeyboard() );
    }

    public static void checkText(ViewInteraction viewInteraction, String text) {
        viewInteraction.check(matches(withText(containsString(text))));
    }

    /* =============================================================================================
    Button
    ============================================================================================= */

    public static void clickButton(ViewInteraction viewInteraction) {
        viewInteraction.perform(click());
    }

    /* =============================================================================================
    Others
    ============================================================================================= */

    public static void checkVisible(ViewInteraction viewInteraction) {
        viewInteraction.check(matches(isDisplayed()));
    }
    public static void checkNotVisible(ViewInteraction viewInteraction) {
        viewInteraction.check(matches( not(isDisplayed()) ));
    }
}
