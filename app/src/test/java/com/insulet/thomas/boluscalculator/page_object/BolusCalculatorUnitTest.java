package com.insulet.thomas.boluscalculator.page_object;

import com.insulet.thomas.boluscalculator.bolus.BolusCalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class BolusCalculatorUnitTest {
    @Test
    public void bolusCalculator() throws Exception {
        BolusCalculator bolusCalculator = new BolusCalculator();
        bolusCalculator.updateResult();
        //System.out.println( bolusCalculator.getResult() );
        assertEquals(bolusCalculator.getCorrectionBolus(), 0, 0);
        assertEquals(bolusCalculator.getMealBolus(), 3.1, 0);
    }
}