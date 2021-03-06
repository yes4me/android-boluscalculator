package com.insulet.thomas.boluscalculator.page_object;

import android.support.test.espresso.ViewInteraction;

import com.insulet.thomas.boluscalculator.MainActivity;
import com.insulet.thomas.boluscalculator.R;
import com.insulet.thomas.boluscalculator.utils.MyEspresso;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by thomas on 6/15/2017.
 */

public class BolusCalculatorPage {
    private final ViewInteraction edit_text_bg_calc_min       = onView(allOf(withId(R.id.edit_text_bg_calc_min)));
    private final ViewInteraction edit_text_bg_calc_max       = onView(allOf(withId(R.id.edit_text_bg_calc_max)));
    private final ViewInteraction edit_text_bg_target         = onView(allOf(withId(R.id.edit_text_bg_target)));
    private final ViewInteraction edit_text_bg_correctabove   = onView(allOf(withId(R.id.edit_text_bg_correctabove)));
    private final ViewInteraction edit_text_bg_current        = onView(allOf(withId(R.id.edit_text_bg_current)));
    private final ViewInteraction edit_text_correction_factor = onView(allOf(withId(R.id.edit_text_correction_factor)));
    private final ViewInteraction edit_text_meal_iob          = onView(allOf(withId(R.id.edit_text_meal_iob)));
    private final ViewInteraction edit_text_correction_iob    = onView(allOf(withId(R.id.edit_text_correction_iob)));
    private final ViewInteraction edit_text_meal_carbs        = onView(allOf(withId(R.id.edit_text_meal_carbs)));
    private final ViewInteraction edit_text_meal_ratio        = onView(allOf(withId(R.id.edit_text_meal_ratio)));
    private final ViewInteraction button_reverse_correction   = onView(allOf(withId(R.id.button_reverse_correction)));
    private final ViewInteraction text_view_result            = onView(allOf(withId(R.id.text_view_result)));

    // Input values
    private Integer bg_calc_min = null;
    private Integer bg_calc_max = null;
    private Integer bg_target   = null;
    private Integer bg_correctabove = null;
    private Integer bg_current  = null;
    private Integer correction_factor = null;
    private Double meal_iob     = null;
    private Double correction_iob = null;
    private Integer meal_carbs  = null;
    private Integer meal_ratio  = null;
    private boolean reverse_correction = false;

    private double mealCarbs		= 47;		// in g
    private double meal_ic_ratio	= 15;		// in g/U

    // Result values
    private Double correction_bolus = null;
    private Double meal_bolus       = null;
    private Double total_bolus      = null;
    private String text_displayed   = "";

    MainActivity activity;

    public BolusCalculatorPage() {}
    public BolusCalculatorPage(MainActivity activity) {
        this.activity = activity;
    }

    /* =============================================================================================
    GETTERS(From the mainActivity) AND SETTERS
    ============================================================================================= */

    public int getBg_calc_min() {
        return activity.getValueBgCalcMin();
    }

    public boolean setBg_calc_min(Integer bg_calc_min) {
        if ((this.bg_calc_min == null) || (this.bg_calc_min.intValue() != bg_calc_min.intValue())) {
            this.bg_calc_min = bg_calc_min;
            MyEspresso.enterTextFromKeyboard(edit_text_bg_calc_min, bg_calc_min);
            return true;
        }
        return false;
    }

    public int getBg_calc_max() {
        return activity.getValueBgCalcMax();
    }

    public boolean setBg_calc_max(Integer bg_calc_max) {
        if ((this.bg_calc_max == null) || (this.bg_calc_max.intValue() != bg_calc_max.intValue())) {
            this.bg_calc_max = bg_calc_max;
            MyEspresso.enterTextFromKeyboard(edit_text_bg_calc_max, bg_calc_max);
            return true;
        }
        return false;
    }

    public int getBg_target() {
        return activity.getValueBgTarget();
    }

    public boolean setBg_target(Integer bg_target) {
        if ((this.bg_target == null) || (this.bg_target.intValue() != bg_target.intValue())) {
            this.bg_target = bg_target;
            MyEspresso.enterTextFromKeyboard(edit_text_bg_target, bg_target);
            return true;
        }
        return false;
    }

    public int getBg_correctabove() {
        return activity.getValueBgCorrectAbove();
    }

    public boolean setBg_correctabove(Integer bg_correctabove) {
        if ((this.bg_correctabove == null) || (this.bg_correctabove.intValue() != bg_correctabove.intValue())) {
            this.bg_correctabove = bg_correctabove;
            MyEspresso.enterTextFromKeyboard(edit_text_bg_correctabove, bg_correctabove);
            return true;
        }
        return false;
    }

    public int getBg_current() {
        return activity.getValueBgCurrent();
    }

    public boolean setBg_current(Integer bg_current) {
        if ((this.bg_current == null) || (this.bg_current.intValue() != bg_current.intValue())) {
            this.bg_current = bg_current;
            MyEspresso.enterTextFromKeyboard(edit_text_bg_current, bg_current);
            return true;
        }
        return false;
    }

    public int getCorrection_factor() {
        return activity.getValueCorrectionFactor();
    }

    public boolean setCorrection_factor(Integer correction_factor) {
        if ((this.correction_factor == null) || (this.correction_factor.intValue() != correction_factor.intValue())) {
            this.correction_factor = correction_factor;
            MyEspresso.enterTextFromKeyboard(edit_text_correction_factor, correction_factor);
            return true;
        }
        return false;
    }

    public double getMeal_iob() {
        return activity.getValueMealIob();
    }

    public boolean setMeal_iob(Double meal_iob) {
        if ((this.meal_iob == null) || (this.meal_iob.doubleValue() != meal_iob.doubleValue())) {
            this.meal_iob = meal_iob;
            MyEspresso.enterTextFromKeyboard(edit_text_meal_iob, meal_iob);
            return true;
        }
        return false;
    }

    public double getCorrection_iob() {
        return activity.getValueCorrectionIob();
    }

    public boolean setCorrection_iob(Double correction_iob) {
        if ((this.correction_iob == null) || (this.correction_iob.doubleValue() != correction_iob.doubleValue())) {
            this.correction_iob = correction_iob;
            MyEspresso.enterTextFromKeyboard(edit_text_correction_iob, correction_iob);
            return true;
        }
        return false;
    }

    public int getMeal_carbs() {
        return activity.getValueMealCarbs();
    }

    public boolean setMeal_carbs(Integer meal_carbs) {
        if ((this.meal_carbs == null) || (this.meal_carbs.intValue() != meal_carbs.intValue())) {
            this.meal_carbs = meal_carbs;
            MyEspresso.enterTextFromKeyboard(edit_text_meal_carbs, meal_carbs);
            return true;
        }
        return false;
    }

    public int getMeal_ratio() {
        return activity.getValueMealRatio();
    }

    public boolean setMeal_ratio(Integer meal_ratio) {
        if ((this.meal_ratio == null) || (this.meal_ratio.intValue() != meal_ratio.intValue())) {
            this.meal_ratio = meal_ratio;
            MyEspresso.enterTextFromKeyboard(edit_text_meal_ratio, meal_ratio);
            return true;
        }
        return false;
    }

    public boolean isReverse_correction() {
        return reverse_correction;
    }

    public boolean setReverse_correction(boolean reverse_correction) {
        if (this.reverse_correction != reverse_correction) {
            this.reverse_correction = reverse_correction;
            MyEspresso.clickButton(button_reverse_correction);
            return true;
        }
        return false;
    }
}
