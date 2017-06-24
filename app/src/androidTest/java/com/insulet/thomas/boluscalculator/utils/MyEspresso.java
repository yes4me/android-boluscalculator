package com.insulet.thomas.boluscalculator.utils;

import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Root;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.uiautomator.UiDevice;
import android.view.WindowManager;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
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
    public static void checkNoText(String text) {
        onView(withText(text)).check(doesNotExist());
    }

    /* =============================================================================================
    Button
    ============================================================================================= */

    public static boolean clickButton(ViewInteraction viewInteraction) {
        try {
            viewInteraction.perform(click());
            return true;
        } catch (Exception e) {}
        return false;
    }
    public static boolean doubleClickButton(ViewInteraction viewInteraction) {
        try {
            viewInteraction.perform(doubleClick());
            return true;
        } catch (Exception e) {}
        return false;
    }
    public static boolean longClickButton(ViewInteraction viewInteraction) {
        try {
            viewInteraction.perform(longClick());
            return true;
        } catch (Exception e) {}
        return false;
    }

    public static void clickBack() {
        // This is not working the activity is die by pressing the back button
        // onView(isRoot()).perform(ViewActions.pressBack());
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressBack();
    }
    public static void clickMenu() {
        onView(isRoot()).perform(ViewActions.pressMenuKey());
    }

    public static boolean isClickable(ViewInteraction viewInteraction) {
        viewInteraction.perform(click());
        return true;
    }

    /* =============================================================================================
    Spinner
    ============================================================================================= */

    public static boolean clickSpinner(ViewInteraction viewInteraction, String text) {
        try {
            viewInteraction.perform(click());
            onData(allOf(is(instanceOf(String.class)), is(text))).perform(click());
            return true;
        } catch (Exception e) {}
        return false;
    }

    public static boolean clickSpinner(ViewInteraction viewInteraction, int position) {
        try {
            viewInteraction.perform(click());
            onData(allOf(is(instanceOf(String.class)))).atPosition(position).perform(click());
            return true;
        } catch (Exception e) {}
        return false;
    }

    /* =============================================================================================
    Hand swipe
    ============================================================================================= */

    /*
    Example:
        ViewInteraction nestedScrollView = onView(allOf(withId(R.id.nestedScrollView)));
        nestedScrollView.perform(swipeUp());
     */
    public static void handSwipe(ViewInteraction viewInteraction, String direction) {
        viewInteraction.perform(closeSoftKeyboard());
        switch(direction.toLowerCase()) {
            case "up":      viewInteraction.perform(swipeUp()); break;
            case "down":    viewInteraction.perform(swipeDown()); break;
            case "left":    viewInteraction.perform(swipeLeft()); break;
            case "right":    viewInteraction.perform(swipeRight()); break;
            default: break;
        }
    }

    public static void handSwipe(int startX, int startY, int endX, int endY, int steps) {
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.swipe(startX, startY, endX, endY, steps);
    }

    /* =============================================================================================
    Toast
    ============================================================================================= */

    private static class ToastMatcher extends TypeSafeMatcher<Root> {
        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    //means this window isn't contained by any other windows.
                    return true;
                }
            }
            return false;
        }
    }

    /*
    Example:
        ViewInteraction button_reverse_correction = onView(allOf(withId(R.id.button_reverse_correction), isClickable()));
        MyEspresso.clickButton(button_reverse_correction);
        MyEspresso.checkToastText("hello world");
     */
    public static void checkToastText(String text) {
        onView(withText(text)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }
    public static void checkNoToastText(String text) {
        onView(withText(text)).inRoot(new ToastMatcher()).check(matches(not(isDisplayed())));
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
