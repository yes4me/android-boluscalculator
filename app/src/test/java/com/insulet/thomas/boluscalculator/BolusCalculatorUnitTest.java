package com.insulet.thomas.boluscalculator;

import com.insulet.thomas.boluscalculator.bolus.BolusCalculator;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class BolusCalculatorUnitTest {
    @Test
    public void bolusCalculator() throws Exception {
        /*
        double bgCalc_min = 70;
        double bgCalc_max = 600;
        double durationBolusCalculatorOn	= 4;	// in hours
        double durationInsulinAction		= 4;	// in hours

        // SCENARIO #3:		bgCurrent unknown => correction bolus = 0
		boolean reverseCorrection = false;
		double bgTarget			= 120;			// TRUE FACT TO ALWAYS CHECK: bgTarget < bgCorrectAbove
		double bgCorrectAbove	= 160;			// bgCurrent needs to be above the bgCorrectAbove to be corrected
        double bgCurrent		= -1;			// use negative value when it is not recorded
		double correctionFactor = 50;
		double adjustmentMeal_IOB = 0.6;		// IOB for the previous food
		double adjustmentCorrection_IOB = 0.6;
		double mealCarbs		= 47;			// in g
		double meal_ic_ratio	= 15;			// in g/U

        //BolusCalculator bolusCalculator = new BolusCalculator();
        bolusCalculator.setBgCalc_min(bgCalc_min);
//        bolusCalculator.setBgCalc_max(bgCalc_max);
//        bolusCalculator.setBgTarget(bgTarget);
//        bolusCalculator.setBgCorrectAbove(bgCorrectAbove);
//        bolusCalculator.setBgCurrent(bgCurrent);
//        bolusCalculator.setCorrectionFactor(correctionFactor);
//        bolusCalculator.setAdjustmentMeal_IOB(adjustmentMeal_IOB);
//        bolusCalculator.setAdjustmentCorrection_IOB(adjustmentCorrection_IOB);
//        bolusCalculator.setMealCarbs(mealCarbs);
//        bolusCalculator.setMeal_ic_ratio(meal_ic_ratio);
//
//        bolusCalculator.updateResult();
//        double correctionBolus = bolusCalculator.getCorrectionBolus();
//        double mealBolus = bolusCalculator.getMealBolus();
        //System.out.println(correctionBolus +"___"+ mealBolus);
        */
        assertEquals(4, 2 + 2);
    }
}