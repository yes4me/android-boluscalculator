package com.insulet.thomas.boluscalculator;


import android.app.Activity;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.insulet.thomas.boluscalculator.utils.MyEspresso;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class LearningTest {
    private static final String TAG = LearningTest.class.getSimpleName();
    public final String FILENAME = "BolusCalculatorTest.json";

    private Activity mActivity = null;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setActivity() {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void MyExpressoTest() throws Exception {
        // This will fail because the button button_reverse_correction need to have the following code:
        // Toast.makeText(getApplicationContext(), "hello world", Toast.LENGTH_LONG).show();

        ViewInteraction button_reverse_correction = onView(allOf(withId(R.id.button_reverse_correction), isClickable()));
        MyEspresso.clickButton(button_reverse_correction);
        MyEspresso.checkToastText("hello world");
        // Assert.assertEquals(600, 600);
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
