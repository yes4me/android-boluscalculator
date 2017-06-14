package com.insulet.thomas.boluscalculator;


import android.content.Context;
import android.content.res.AssetManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.KeyEventAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.JsonReader;
import android.util.Log;
import android.view.KeyEvent;
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
import org.json.simple.parser.JSONParser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.security.AccessController.getContext;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class BolusCalculatorTest {
    private static final String TAG = BolusCalculatorTest.class.getSimpleName();
    public final String FILENAME = "BolusCalculatorTest.json";

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
    static boolean reverse_correction_status = false;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        int bg_calc_min = 0;
        int bg_calc_max = 0;
        int bg_target = 0;
        int bg_correctabove = 0;
        int bg_current = 0;
        int correction_factor = 0;
        double meal_iob = 0.0;
        double correction_iob = 0.0;
        int meal_carbs = 0;
        int meal_ratio = 0;
        boolean reverse_correction = false;

        double correction_bolus = 0.0;
        double meal_bolus       = 0.0;
        double total_bolus      = 0.0;
        String text_displayed   = "";

        try {
            MyJson myJson = new JsonParser();
            List<JsonBolus> bolusDataList = myJson.readJsonStream(FILENAME);
            for (JsonBolus bolusData : bolusDataList) {
                // Input
                JsonBolusInput jsonBolusInput = bolusData.getInput();
                bg_calc_min = jsonBolusInput.getBg_calc_min();
                bg_calc_max     = jsonBolusInput.getBg_calc_max();
                bg_target       = jsonBolusInput.getBg_target();
                bg_correctabove = jsonBolusInput.getBg_correctabove();

                bg_current      = jsonBolusInput.getBg_current();
                correction_factor = jsonBolusInput.getCorrection_factor();
                meal_iob        = jsonBolusInput.getMeal_iob();
                correction_iob  = jsonBolusInput.getCorrection_iob();
                meal_carbs      = jsonBolusInput.getMeal_carbs();
                meal_ratio      = jsonBolusInput.getMeal_ratio();
                reverse_correction = jsonBolusInput.isReverse_correction();

//                edit_text_bg_calc_min.perform(replaceText(String.valueOf(bg_calc_min)), closeSoftKeyboard());
//                edit_text_bg_calc_max.perform(replaceText(String.valueOf(bg_calc_max)), closeSoftKeyboard());
//                edit_text_bg_target.perform(replaceText(String.valueOf(bg_target)), closeSoftKeyboard());
//                edit_text_bg_correctabove.perform(replaceText(String.valueOf(bg_correctabove)), closeSoftKeyboard());
//                edit_text_bg_current.perform(replaceText(String.valueOf(bg_current)), closeSoftKeyboard());
//                edit_text_correction_factor.perform(replaceText(String.valueOf(correction_factor)), closeSoftKeyboard());
//                edit_text_meal_iob.perform(replaceText(String.valueOf(meal_iob)), closeSoftKeyboard());
//                edit_text_correction_iob.perform(replaceText(String.valueOf(correction_iob)), closeSoftKeyboard());
//                edit_text_meal_carbs.perform(replaceText(String.valueOf(meal_carbs)), closeSoftKeyboard());
//                edit_text_meal_ratio.perform(replaceText(String.valueOf(meal_ratio)), closeSoftKeyboard());
                MyEspresso.enterText(edit_text_bg_calc_min, String.valueOf(bg_calc_min));
                MyEspresso.enterText(edit_text_bg_calc_max, String.valueOf(bg_calc_max));
                MyEspresso.enterText(edit_text_bg_target, String.valueOf(bg_target));
                MyEspresso.enterText(edit_text_bg_correctabove, String.valueOf(bg_correctabove));
                MyEspresso.enterText(edit_text_bg_current, String.valueOf(bg_current));
                MyEspresso.enterText(edit_text_correction_factor, String.valueOf(correction_factor));
                MyEspresso.enterText(edit_text_meal_iob, String.valueOf(meal_iob));
                MyEspresso.enterText(edit_text_correction_iob, String.valueOf(correction_iob));
                MyEspresso.enterText(edit_text_meal_carbs, String.valueOf(meal_carbs));
                MyEspresso.enterText(edit_text_meal_ratio, String.valueOf(meal_ratio));

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
