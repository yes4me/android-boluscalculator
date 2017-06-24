package com.insulet.thomas.boluscalculator.utils;

/**
 * Created by thomas on 6/14/2017.
 */

public class JsonBolusResult {
    double correction_bolus = 0.0;
    double meal_bolus = 0.0;
    double total_bolus = 0.0;
    String text_displayed = "";

    public JsonBolusResult(double correction_bolus, double meal_bolus, double total_bolus, String text_displayed) {
        this.correction_bolus = correction_bolus;
        this.meal_bolus = meal_bolus;
        this.total_bolus = total_bolus;
        this.text_displayed = text_displayed;
    }

    /* =============================================================================================
    GETTER
    ============================================================================================= */

    public double getCorrection_bolus() {
        return correction_bolus;
    }

    public double getMeal_bolus() {
        return meal_bolus;
    }

    public double getTotal_bolus() {
        return total_bolus;
    }

    public String getText_displayed() {
        return text_displayed;
    }
}
