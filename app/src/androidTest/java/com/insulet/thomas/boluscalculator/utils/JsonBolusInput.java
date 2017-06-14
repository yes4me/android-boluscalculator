package com.insulet.thomas.boluscalculator.utils;

/**
 * Created by thomas on 6/14/2017.
 */

public class JsonBolusInput {
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

    public JsonBolusInput(int bg_calc_min, int bg_calc_max,int bg_target, int bg_correctabove, int bg_current, int correction_factor, double meal_iob, double correction_iob, int meal_carbs, int meal_ratio, boolean reverse_correction) {
        this.bg_calc_min = bg_calc_min;
        this.bg_calc_max = bg_calc_max;
        this.bg_target = bg_target;
        this.bg_correctabove = bg_correctabove;
        this.bg_current = bg_current;
        this.correction_factor = correction_factor;
        this.meal_iob = meal_iob;
        this.correction_iob = correction_iob;
        this.meal_carbs = meal_carbs;
        this.meal_ratio = meal_ratio;
        this.reverse_correction = reverse_correction;
    }

    /* =============================================================================================
    GETTER
    ============================================================================================= */

    public int getBg_calc_min() {
        return bg_calc_min;
    }

    public int getBg_calc_max() {
        return bg_calc_max;
    }

    public int getBg_target() {
        return bg_target;
    }

    public int getBg_correctabove() {
        return bg_correctabove;
    }

    public int getBg_current() {
        return bg_current;
    }

    public int getCorrection_factor() {
        return correction_factor;
    }

    public double getMeal_iob() {
        return meal_iob;
    }

    public double getCorrection_iob() {
        return correction_iob;
    }

    public int getMeal_carbs() {
        return meal_carbs;
    }

    public int getMeal_ratio() {
        return meal_ratio;
    }

    public boolean isReverse_correction() {
        return reverse_correction;
    }
}
