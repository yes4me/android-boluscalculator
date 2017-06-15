package com.insulet.thomas.boluscalculator;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.insulet.thomas.boluscalculator.utils.JsonBolus;
import com.insulet.thomas.boluscalculator.utils.JsonBolusInput;
import com.insulet.thomas.boluscalculator.utils.JsonBolusResult;
import com.insulet.thomas.boluscalculator.utils.JsonParser;
import com.insulet.thomas.boluscalculator.utils.MyEspresso;
import com.insulet.thomas.boluscalculator.utils.MyJson;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class BolusCalculatorTest {
    private static final String TAG = BolusCalculatorTest.class.getSimpleName();
    public final String FILENAME = "BolusCalculatorTest.json";
    public static boolean reverse_correction_status = false;

    ViewInteraction edit_text_bg_calc_min       = onView(allOf(withId(R.id.edit_text_bg_calc_min)));
    ViewInteraction edit_text_bg_calc_max       = onView(allOf(withId(R.id.edit_text_bg_calc_max)));
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

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        int bg_calc_min = -2;
        int bg_calc_max = -2;
        int bg_target   = -2;
        int bg_correctabove = -2;
        int bg_current  = -2;
        int correction_factor = -2;
        double meal_iob = -1.0;
        double correction_iob = -1.0;
        int meal_carbs  = -2;
        int meal_ratio  = -2;
        boolean reverse_correction = false;

        double correction_bolus = -2.0;
        double meal_bolus       = -2.0;
        double total_bolus      = -2.0;
        String text_displayed   = "";

        try {
            MyJson myJson = new JsonParser();
            List<JsonBolus> bolusDataList = myJson.readJsonStream(FILENAME);
            for (JsonBolus bolusData : bolusDataList) {
                // Input
                JsonBolusInput jsonBolusInput = bolusData.getInput();
                bg_calc_min     = updateField(edit_text_bg_calc_min, bg_calc_min, jsonBolusInput.getBg_calc_min());
                bg_calc_max     = updateField(edit_text_bg_calc_max, bg_calc_max, jsonBolusInput.getBg_calc_max());
                bg_target       = updateField(edit_text_bg_target, bg_target, jsonBolusInput.getBg_target());
                bg_correctabove = updateField(edit_text_bg_correctabove, bg_correctabove, jsonBolusInput.getBg_correctabove());
                bg_current      = updateField(edit_text_bg_current, bg_current, jsonBolusInput.getBg_current());
                correction_factor = updateField(edit_text_correction_factor, correction_factor, jsonBolusInput.getCorrection_factor());
                meal_iob        = updateField(edit_text_meal_iob, meal_iob, jsonBolusInput.getMeal_iob());
                correction_iob  = updateField(edit_text_correction_iob, correction_iob, jsonBolusInput.getCorrection_iob());
                meal_carbs      = updateField(edit_text_meal_carbs, meal_carbs, jsonBolusInput.getMeal_carbs());
                meal_ratio      = updateField(edit_text_meal_ratio, meal_ratio, jsonBolusInput.getMeal_ratio());

                reverse_correction = jsonBolusInput.isReverse_correction();

                if (reverse_correction_status != reverse_correction) {
                    button_reverse_correction.perform(click());
                    reverse_correction_status = reverse_correction;
                }

                // Results
                JsonBolusResult jsonBolusResult = bolusData.getResult();
                // correction_bolus = jsonBolusResult.getCorrection_bolus();
                // meal_bolus       = jsonBolusResult.getMeal_bolus();
                // total_bolus      = jsonBolusResult.getTotal_bolus();
                text_displayed   = jsonBolusResult.getText_displayed();

                // Compare to the displayed result
                text_view_result.check(matches(withText(text_displayed)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update the edit view only when the value changes from oldValue to newValue
    private int updateField(ViewInteraction viewInteraction, int oldValue, int newValue) {
        if (oldValue != newValue) {
            MyEspresso.enterTextFromKeyboard(viewInteraction, String.valueOf(newValue));
        }
        return newValue;
    }
    private double updateField(ViewInteraction viewInteraction, double oldValue, double newValue) {
        if (oldValue != newValue) {
            MyEspresso.enterTextFromKeyboard(viewInteraction, String.valueOf(newValue));
        }
        return newValue;
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
