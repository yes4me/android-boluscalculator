package com.insulet.thomas.boluscalculator;


import android.app.Activity;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.insulet.thomas.boluscalculator.bolus.BolusCalculator;
import com.insulet.thomas.boluscalculator.page_object.BolusCalculatorPage;
import com.insulet.thomas.boluscalculator.utils.JsonBolus;
import com.insulet.thomas.boluscalculator.utils.JsonBolusInput;
import com.insulet.thomas.boluscalculator.utils.JsonBolusResult;
import com.insulet.thomas.boluscalculator.utils.JsonBolusParser;
import com.insulet.thomas.boluscalculator.utils.JsonBase;
import com.insulet.thomas.boluscalculator.utils.MyEspresso;

import junit.framework.Assert;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
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
public class BolusCalculatorTest {
    private static final String TAG = BolusCalculatorTest.class.getSimpleName();
    public final String FILENAME = "BolusCalculatorTest.json";
    private Activity mActivity = null;

    private ViewInteraction text_view_result = onView(allOf(withId(R.id.text_view_result)));

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setActivity() {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void mainActivityTest() throws Exception {
        try {
            JsonBase jsonBase = new JsonBolusParser();
            List<JsonBolus> bolusDataList = jsonBase.readJsonStream(FILENAME);

            MainActivity activity = mActivityTestRule.getActivity();
            BolusCalculatorPage bolusCalculatorPage = new BolusCalculatorPage(activity);

            for (JsonBolus bolusData : bolusDataList) {
                // Input
                JsonBolusInput jsonBolusInput = bolusData.getInput();
                bolusCalculatorPage.setBg_calc_min( jsonBolusInput.getBg_calc_min() );
                bolusCalculatorPage.setBg_calc_max( jsonBolusInput.getBg_calc_max() );
                bolusCalculatorPage.setBg_target( jsonBolusInput.getBg_target() );

                // If target BG ends up being higher than the current value for correctabove, it is impossible to set a value for target
                boolean b = bolusCalculatorPage.setBg_correctabove( jsonBolusInput.getBg_correctabove() );
                bolusCalculatorPage.setBg_target( jsonBolusInput.getBg_target() );
                if (!b)
                    bolusCalculatorPage.setBg_correctabove( jsonBolusInput.getBg_correctabove() );

                bolusCalculatorPage.setBg_current( jsonBolusInput.getBg_current() );
                bolusCalculatorPage.setCorrection_factor( jsonBolusInput.getCorrection_factor() );
                bolusCalculatorPage.setMeal_iob( jsonBolusInput.getMeal_iob() );
                bolusCalculatorPage.setCorrection_iob( jsonBolusInput.getCorrection_iob() );
                bolusCalculatorPage.setMeal_carbs( jsonBolusInput.getMeal_carbs() );
                bolusCalculatorPage.setMeal_ratio( jsonBolusInput.getMeal_ratio() );
                bolusCalculatorPage.setReverse_correction( jsonBolusInput.isReverse_correction() );

                // Results
                JsonBolusResult jsonBolusResult = bolusData.getResult();
                // double correction_bolus = jsonBolusResult.getCorrection_bolus();
                // double meal_bolus       = jsonBolusResult.getMeal_bolus();
                // double total_bolus      = jsonBolusResult.getTotal_bolus();
                String text_displayed      = jsonBolusResult.getText_displayed();

                // Compare to the displayed result
                text_view_result.check(matches(withText(text_displayed)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
