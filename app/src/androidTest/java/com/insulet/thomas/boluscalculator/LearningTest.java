package com.insulet.thomas.boluscalculator;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.insulet.thomas.boluscalculator.page_object.BolusCalculatorPage;
import com.insulet.thomas.boluscalculator.utils.JsonBase;
import com.insulet.thomas.boluscalculator.utils.JsonBolus;
import com.insulet.thomas.boluscalculator.utils.JsonBolusInput;
import com.insulet.thomas.boluscalculator.utils.JsonBolusParser;
import com.insulet.thomas.boluscalculator.utils.JsonBolusResult;
import com.insulet.thomas.boluscalculator.utils.MyEspresso;

import junit.framework.Assert;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class LearningTest {
    private static final String TAG = LearningTest.class.getSimpleName();
    public final String FILENAME = "BolusCalculatorTest.json";

    private ViewInteraction text_view_result            = onView(allOf(withId(R.id.text_view_result)));

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void MyExpressoTest() {
        ViewInteraction test_button_id = onView(allOf(withId(R.id.test_button_id)));
        MyEspresso.checkNotVisible(test_button_id);

        ViewInteraction text_view_bg_calc_min = onView(allOf(withId(R.id.text_view_bg_calc_min)));
        MyEspresso.checkText(text_view_bg_calc_min, "min");
    }

    @Test
    public void MainActivityTest() {
        MainActivity activity = mActivityTestRule.getActivity();
        double bgCalcMax = activity.getValueBgCalcMax();

        Assert.assertEquals(bgCalcMax, 600);
    }

    /* =============================================================================================
    AUTO GENERATED
    ============================================================================================= */

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
