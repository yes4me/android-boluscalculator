package com.insulet.thomas.boluscalculator.bolus;

import android.content.Context;
import android.util.Log;

import com.insulet.thomas.boluscalculator.R;
import com.insulet.thomas.boluscalculator.util.ConversionUtil;
import com.insulet.thomas.boluscalculator.util.NumberUtil;
import com.insulet.thomas.boluscalculator.util.ResourceUtil;
import com.insulet.thomas.boluscalculator.util.StringUtil;

/**
 * Created by thomas on 6/11/2017.
 */

public class BolusCalculator {
    private static final String TAG = BolusCalculator.class.getSimpleName();

    // Input values
    private double bgCalc_min = 70;
    private double bgCalc_max = 600;
    private double durationBolusCalculatorOn	= 4;	// in hours
    private double durationInsulinAction		= 4;	// in hours
    private double bgTarget			= 110;  // TRUE FACT TO ALWAYS CHECK: bgTarget < bgCorrectAbove
    private double bgCorrectAbove	= 160;	// bgCurrent needs to be above the bgCorrectAbove to be corrected
    private double bgCurrent		= 90;   // use negative value when it is not recorded
    private double correctionFactor = 50;
    private double adjustmentMeal_IOB = 0;      // = meal IOB
    private double adjustmentCorrection_IOB = 0;// = correction IOB
    private double mealCarbs		= 47;		// in g
    private double meal_ic_ratio	= 15;		// in g/U
    private boolean reverseCorrection = false;

    // Result values
    private double correctionBolus	= 0;
    private double mealBolus		= 0;

    /* =============================================================================================
    CONSTRUCTOR
    ============================================================================================= */

    public BolusCalculator(Context context) {
        ResourceUtil resourceUtil = new ResourceUtil(context);

        /*
        // Initialize the input values
        bgCalc_min      = ConversionUtil.convertToDouble( context.getString(R.string.edit_text_bg_calc_min) );
        bgCalc_max      = ConversionUtil.convertToDouble( context.getString(R.string.edit_text_bg_calc_max) );
        // durationBolusCalculatorOn is not used yet
        // durationInsulinAction is not used yet
        bgTarget        = ConversionUtil.convertToDouble( context.getString(R.string.edit_text_bg_target) );
        bgCorrectAbove	= ConversionUtil.convertToDouble( context.getString(R.string.edit_text_bg_correctabove) );
        bgCurrent       = ConversionUtil.convertToDouble( context.getString(R.string.edit_text_bg_current) );
        correctionFactor= ConversionUtil.convertToDouble( context.getString(R.string.edit_text_correction_factor) );
        adjustmentMeal_IOB = ConversionUtil.convertToDouble( context.getString(R.string.edit_text_meal_iob) );
        adjustmentCorrection_IOB = ConversionUtil.convertToDouble( context.getString(R.string.edit_text_correction_iob) );
        mealCarbs		= ConversionUtil.convertToDouble( context.getString(R.string.edit_text_meal_carbs) );
        meal_ic_ratio	= ConversionUtil.convertToDouble( context.getString(R.string.edit_text_meal_ratio) );
        */

        // Initialize the input values
        bgCalc_min      = resourceUtil.getDouble("edit_text_bg_calc_min");
        bgCalc_max      = resourceUtil.getDouble("edit_text_bg_calc_max");
        // durationBolusCalculatorOn is not used yet
        // durationInsulinAction is not used yet
        bgTarget        = resourceUtil.getDouble("edit_text_bg_target");
        bgCorrectAbove	= resourceUtil.getDouble("edit_text_bg_correctabove");
        bgCurrent       = resourceUtil.getDouble("edit_text_bg_current");
        correctionFactor= resourceUtil.getDouble("edit_text_correction_factor");
        adjustmentMeal_IOB = resourceUtil.getDouble("edit_text_meal_iob");
        adjustmentCorrection_IOB = resourceUtil.getDouble("edit_text_correction_iob");
        mealCarbs		= resourceUtil.getDouble("edit_text_meal_carbs");
        meal_ic_ratio	= resourceUtil.getDouble("edit_text_meal_ratio");
    }

    /* =============================================================================================
    GETTER AND SETTERS
    ============================================================================================= */

    public void setDurationBolusCalculatorOn(double durationBolusCalculatorOn) {
        Log.d(TAG, "durationBolusCalculatorOn = " + durationBolusCalculatorOn);
        this.durationBolusCalculatorOn = durationBolusCalculatorOn;
    }

    public double getDurationBolusCalculatorOn() {
        return durationBolusCalculatorOn;
    }

    public void setDurationInsulinAction(double durationInsulinAction) {
        Log.d(TAG, "durationInsulinAction = " + durationInsulinAction);
        this.durationInsulinAction = durationInsulinAction;
    }

    public double getDurationInsulinAction() {
        return durationInsulinAction;
    }


    public void setBgCalc_min(double bgCalc_min) {
        Log.d(TAG, "bgCalc_min = " + bgCalc_min);
        if (bgCalc_min <= bgTarget)
            this.bgCalc_min = bgCalc_min;
    }

    public double getBgCalc_min() {
        return bgCalc_min;
    }

    public void setBgCalc_max(double bgCalc_max) {
        Log.d(TAG, "bgCalc_max = " + bgCalc_max);
        if (bgCorrectAbove <= bgCalc_max)
            this.bgCalc_max = bgCalc_max;
    }

    public double getBgCalc_max() {
        return bgCalc_max;
    }

    public void setReverseCorrection(boolean reverseCorrection) {
        Log.d(TAG, "reverseCorrection = " + reverseCorrection);
        this.reverseCorrection = reverseCorrection;
    }

    public boolean isReverseCorrection() {
        return reverseCorrection;
    }

    public void setBgCurrent(double bgCurrent) {
        Log.d(TAG, "bgCurrent = " + bgCurrent);
        this.bgCurrent = bgCurrent;
    }

    public double getBgCurrent() {
        return bgCurrent;
    }

    public void setBgTarget(double bgTarget) {
        Log.d(TAG, "bgTarget = " + bgTarget);
        if ((bgCalc_min <= bgTarget) && (bgTarget <= bgCorrectAbove))
            this.bgTarget = bgTarget;
    }

    public double getBgTarget() {
        return bgTarget;
    }

    public void setBgCorrectAbove(double bgCorrectAbove) {
        Log.d(TAG, "bgCorrectAbove = " + bgCorrectAbove);
        if ((bgTarget <= bgCorrectAbove) && (bgCorrectAbove <= bgCalc_max))
            this.bgCorrectAbove = bgCorrectAbove;
    }

    public double getBgCorrectAbove() {
        return bgCorrectAbove;
    }

    public void setCorrectionFactor(double correctionFactor) {
        Log.d(TAG, "correctionFactor = " + correctionFactor);
        this.correctionFactor = correctionFactor;
    }

    public double getCorrectionFactor() {
        return correctionFactor;
    }

    public void setAdjustmentMeal_IOB(double adjustmentMeal_IOB) {
        Log.d(TAG, "adjustmentMeal_IOB = " + adjustmentMeal_IOB);
        if (adjustmentMeal_IOB >=0)
            this.adjustmentMeal_IOB = adjustmentMeal_IOB;
    }

    public double getAdjustmentMeal_IOB() {
        return adjustmentMeal_IOB;
    }

    public void setAdjustmentCorrection_IOB(double adjustmentCorrection_IOB) {
        Log.d(TAG, "adjustmentCorrection_IOB = " + adjustmentCorrection_IOB);
        if (adjustmentCorrection_IOB >=0)
            this.adjustmentCorrection_IOB = adjustmentCorrection_IOB;
    }

    public double getAdjustmentCorrection_IOB() {
        return adjustmentCorrection_IOB;
    }

    public void setMealCarbs(double mealCarbs) {
        Log.d(TAG, "mealCarbs = " + mealCarbs);
        if (mealCarbs >=0)
            this.mealCarbs = mealCarbs;
    }

    public double getMealCarbs() {
        return mealCarbs;
    }

    public void setMeal_ic_ratio(double meal_ic_ratio) {
        Log.d(TAG, "meal_ic_ratio = " + meal_ic_ratio);
        if (meal_ic_ratio >=0)
            this.meal_ic_ratio = meal_ic_ratio;
    }

    public double getMeal_ic_ratio() {
        return meal_ic_ratio;
    }

    public double getCorrectionBolus() {
        return correctionBolus;
    }

    public double getMealBolus() {
        return mealBolus;
    }

    /* =============================================================================================
    CALCULATION
    ============================================================================================= */

    public void updateResult() {
        // bgCurrent<0 <=>BG is not recorded
        if (bgCurrent < 0) {
            correctionBolus	= 0;
            mealBolus		= NumberUtil.round(mealCarbs / meal_ic_ratio, 0.05);
            return;
        }


        // Check for input error
        if (bgCorrectAbove < bgTarget) {
            return;
        }
        if (bgCurrent > 0) {
            // BG is recorded, check for special cases
            if (bgCurrent < bgCalc_min) {
                return;
            } else if (bgCurrent > bgCalc_max) {
                return;
            }
        }
        if (durationBolusCalculatorOn < durationInsulinAction) {
            return;
        }


        // Total Bolus calculation
        double adjustmentCorrection_IOB_transfer = adjustmentCorrection_IOB;
        if (bgCorrectAbove < bgCurrent) {
            correctionBolus = (bgCurrent - bgTarget) / correctionFactor;
            correctionBolus = NumberUtil.round(correctionBolus - adjustmentMeal_IOB, 0.05);
            if (correctionBolus < 0) {
                correctionBolus = 0;
            }

            correctionBolus = NumberUtil.round(correctionBolus - adjustmentCorrection_IOB, 0.05);
            if (correctionBolus < 0) {
                adjustmentCorrection_IOB_transfer = Math.abs(correctionBolus);
                correctionBolus = 0;
            }
        } else if (bgTarget < bgCurrent) {
            correctionBolus = 0;
        } else {
            if (reverseCorrection) {
                correctionBolus = (bgCurrent - bgTarget) / correctionFactor;
            } else {
                correctionBolus = 0;
            }
        }

        mealBolus = NumberUtil.round(mealCarbs / meal_ic_ratio - adjustmentCorrection_IOB_transfer, 0.05);
        if (mealBolus < 0)
            mealBolus = 0;
    }

    public String getResult() {
        if (bgCorrectAbove < bgTarget) {
            return "BG target must always be less than BG CorrectAbove";
        }
        if (bgCurrent > 0) {
            // BG is recorded, check for special cases
            if (bgCurrent < bgCalc_min) {
                return "Too LOW BG! Go to hospital!";
            } else if (bgCurrent > bgCalc_max) {
                return "Too HIGH BG! Go to hospital!";
            }
        }
        if (durationBolusCalculatorOn < durationInsulinAction) {
            return "Bolus calculator is turned OFF during the Duration of Insulin Action (DIA)";
        }

        String result;
        result = "correction bolus = " + StringUtil.simplify(correctionBolus, 2);
        result += "\nmeal bolus = " + StringUtil.simplify(mealBolus, 2);

        double totalBolus = correctionBolus + mealBolus;
        if (totalBolus < 0)
            totalBolus = 0;
        result += "\nTOTAL BOLUS = " + StringUtil.simplify(totalBolus, 2);
        return result;
    }
}
