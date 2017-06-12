package com.insulet.thomas.boluscalculator;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BolusCalculatorTest3 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction text_view_bg_calc_min       = onView(allOf(withId(R.id.edit_text_bg_calc_min)));
        ViewInteraction text_view_bg_calc_max       = onView(allOf(withId(R.id.edit_text_bg_calc_max)));
        ViewInteraction edit_text_bg_target         = onView(allOf(withId(R.id.edit_text_bg_target)));
        ViewInteraction edit_text_bg_correctabove   = onView(allOf(withId(R.id.edit_text_bg_correctabove)));
        ViewInteraction edit_text_bg_current        = onView(allOf(withId(R.id.edit_text_bg_current)));
        ViewInteraction edit_text_correction_factor = onView(allOf(withId(R.id.edit_text_correction_factor)));
        ViewInteraction edit_text_meal_iob          = onView(allOf(withId(R.id.edit_text_meal_iob)));
        ViewInteraction edit_text_correction_iob    = onView(allOf(withId(R.id.edit_text_correction_iob)));
        ViewInteraction edit_text_meal_carbs        = onView(allOf(withId(R.id.edit_text_meal_carbs)));
        ViewInteraction edit_text_meal_ratio        = onView(allOf(withId(R.id.edit_text_meal_ratio)));
        ViewInteraction button_reverse_correction   = onView(allOf(withId(R.id.button_reverse_correction)));
        ViewInteraction text_view_result            = onView(allOf(withId(R.id.text_view_result)));

        text_view_bg_calc_min.perform(replaceText("70"), closeSoftKeyboard());
        text_view_bg_calc_max.perform(replaceText("600"), closeSoftKeyboard());
        edit_text_bg_target.perform(replaceText("120"), closeSoftKeyboard());
        edit_text_bg_correctabove.perform(replaceText("160"), closeSoftKeyboard());
        edit_text_bg_current.perform(replaceText("-1"), closeSoftKeyboard());
        edit_text_correction_factor.perform(replaceText("50"), closeSoftKeyboard());
        edit_text_meal_iob.perform(replaceText("0.6"), closeSoftKeyboard());
        edit_text_correction_iob.perform(replaceText("0.6"), closeSoftKeyboard());
        edit_text_meal_carbs.perform(replaceText("47"), closeSoftKeyboard());
        edit_text_meal_ratio.perform(replaceText("15"), closeSoftKeyboard());
        // button_reverse_correction.perform(click());

        text_view_result.check(matches(isDisplayed()));
        text_view_result.check(matches(withText("correction bolus = 0\nmeal bolus = 3.1\nTOTAL BOLUS = 3.1")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
