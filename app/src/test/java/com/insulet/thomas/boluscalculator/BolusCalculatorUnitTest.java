package com.insulet.thomas.boluscalculator;

import com.insulet.thomas.boluscalculator.bolus.BolusCalculator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * Created by thomas on 6/20/2017.
 */

public class BolusCalculatorUnitTest {
    private BolusCalculator bolusCalculator;

    @BeforeMethod
    public void setupMock() {
        bolusCalculator = mock(BolusCalculator.class);
        when(bolusCalculator.getBgCalc_min()).thenReturn(70.0);
        when(bolusCalculator.getBgCalc_max()).thenReturn(600.0);
    }

    @Test
    public void barGreets2() {
        assertEquals(bolusCalculator.getBgCalc_min(), 70.0);
        assertEquals(bolusCalculator.getBgCalc_max(), 600.0);
    }
}