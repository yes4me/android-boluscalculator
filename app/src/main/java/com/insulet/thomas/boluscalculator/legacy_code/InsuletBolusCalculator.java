package com.insulet.thomas.boluscalculator.legacy_code;

/**
 * Created by tnguyen on 6/8/2017.
 */

public class InsuletBolusCalculator {
    /* -----------------------------------------------------
    Library
    ----------------------------------------------------- */

	public static class Number {
		// Number of digit before the comma
		public static int numberInteger(double number) {
			int decimal = (int) number;
			return String.valueOf(decimal).length();
		}

		// Number of digit after the comma
		public static int numberFractional(double number) {
			String value = String.valueOf(number);
			value = value.replaceAll(".0$", "");
			int index = value.indexOf('.');
			return (index < 0) ? 0 : value.length() - index - 1;
		}

		public static double round(double value, double precision) {
			double numberFractional = Math.pow(10, numberFractional(precision));
			double factor = Math.floor(value / precision);
			double b = Math.floor(factor * precision * numberFractional);
			return b / numberFractional;
		}
	}

	public static class Display {
		public static void print(String text, double value) {
			String format = "%-25s:%6s\n";
			System.out.format(format, text, value);
		}

		public static void print(String text, boolean value) {
			String format = "%-25s:%6s\n";
			System.out.format(format, text, value);
		}
	}

    /* -----------------------------------------------------
    Bolus calculator object
    ----------------------------------------------------- */

	public static class BolusCalculator {
		double bgCalc_min = 70;
		double bgCalc_max = 600;
		double durationBolusCalculatorOn	= 4;	// in hours
		double durationInsulinAction		= 4;	// in hours

		// SCENARIO #3:		bgCurrent unknown => correction bolus = 0
		/*
		boolean reverseCorrection = false;
		double bgCurrent		= -1;			// use negative value when it is not recorded
		double bgTarget			= 120;			// TRUE FACT TO ALWAYS CHECK: bgTarget < bgCorrectAbove
		double bgCorrectAbove	= 160;			// bgCurrent needs to be above the bgCorrectAbove to be corrected
		double correctionFactor = 50;
		double adjustmentMeal_IOB = 0.6;		// IOB for the previous food
		double adjustmentCorrection_IOB = 0.6;

		double mealCarbs		= 47;			// in g
		double meal_ic_ratio	= 15;			// in g/U
		*/

		// SCENARIO #2:		bgTarget < bgCorrectAbove < bgCurrent => possible remaining correction
		/*
		boolean reverseCorrection = false;
		double bgCurrent		= 170;	// use negative value when it is not recorded
		double bgTarget		= 120;	// TRUE FACT TO ALWAYS CHECK: bgTarget < bgCorrectAbove
		double bgCorrectAbove	= 160;	// bgCurrent needs to be above the bgCorrectAbove to be corrected
		double correctionFactor = 50;
		double adjustmentMeal_IOB = 0.6;
		double adjustmentCorrection_IOB = 0.6;

		double mealCarbs		= 47;			// in g
		double meal_ic_ratio	= 15;			// in g/U
		*/

		/*
		// SCENARIO #1:		bgTarget < bgCurrent < bgCorrectAbove => correction bolus = 0
		boolean reverseCorrection = false;
		double bgCurrent		= 170;	// use negative value when it is not recorded
		double bgTarget		= 120;	// TRUE FACT TO ALWAYS CHECK: bgTarget < bgCorrectAbove
		double bgCorrectAbove	= 175;	// bgCurrent needs to be above the bgCorrectAbove to be corrected
		double correctionFactor = 50;
		double adjustmentMeal_IOB = 0.6;
		double adjustmentCorrection_IOB = 0;

		double mealCarbs		= 47;			// in g
		double meal_ic_ratio	= 15;			// in g/U
		*/

		// SCENARIO #4:		Min < bgCurrent < bgTarget => correction bolus < 0 => bgCurrent is less than correction bolus => 0 unless reverseCorrection is true
		/*
		boolean reverseCorrection = true;
		double bgCurrent		= 90;	// use negative value when it is not recorded
		double bgTarget		= 120;	// TRUE FACT TO ALWAYS CHECK: bgTarget < bgCorrectAbove
		double bgCorrectAbove	= 175;	// bgCurrent needs to be above the bgCorrectAbove to be corrected
		double correctionFactor = 50;
		double adjustmentMeal_IOB = 0.6;
		double adjustmentCorrection_IOB = 0.6;

		double mealCarbs		= 47;			// in g
		double meal_ic_ratio	= 15;			// in g/U
		*/

		// SCENARIO #5:		Min < bgCurrent < bgTarget => correction bolus < 0 => bgCurrent is less than correction bolus => 0 unless reverseCorrection is true
		/*
		boolean reverseCorrection = true;
		double bgCurrent		= 100;  // use negative value when it is not recorded
		double bgTarget			= 120;  // TRUE FACT TO ALWAYS CHECK: bgTarget < bgCorrectAbove
		double bgCorrectAbove	= 130;	// bgCurrent needs to be above the bgCorrectAbove to be corrected
		double correctionFactor = 50;
		double adjustmentMeal_IOB = 0.6;
		double adjustmentCorrection_IOB = 0.6;

		double mealCarbs = 0;			// in g
		double meal_ic_ratio = 15;      // in g/U
		*/

		/*
		// TEST#2
		boolean reverseCorrection = true;
		double bgCurrent		= 170;  // use negative value when it is not recorded
		double bgTarget			= 120;  // TRUE FACT TO ALWAYS CHECK: bgTarget < bgCorrectAbove
		double bgCorrectAbove	= 160;	// bgCurrent needs to be above the bgCorrectAbove to be corrected
		double correctionFactor = 50;
		double adjustmentMeal_IOB = 1.8;
		double adjustmentCorrection_IOB = 1.8;

		double mealCarbs = 47;			// in g
		double meal_ic_ratio = 15;      // in g/U
		*/

		/*
		// pROBLEM WITH ADDITION
		boolean reverseCorrection = true;
		double bgCurrent		= 90;  // use negative value when it is not recorded
		double bgTarget			= 120;  // TRUE FACT TO ALWAYS CHECK: bgTarget < bgCorrectAbove
		double bgCorrectAbove	= 160;	// bgCurrent needs to be above the bgCorrectAbove to be corrected
		double correctionFactor = 50;
		double adjustmentMeal_IOB = 1.2;
		double adjustmentCorrection_IOB = 1.2;

		double mealCarbs		= 47;		// in g
		double meal_ic_ratio	= 15;		// in g/U
		*/

		boolean reverseCorrection = true;
		double bgTarget			= 110;  // TRUE FACT TO ALWAYS CHECK: bgTarget < bgCorrectAbove
		double bgCorrectAbove	= 115;	// bgCurrent needs to be above the bgCorrectAbove to be corrected
		double bgCurrent		= 210;  // use negative value when it is not recorded
		double correctionFactor = 50;
		double adjustmentMeal_IOB = 0.5;
		double adjustmentCorrection_IOB = 0.6;

		double mealCarbs		= 47;		// in g
		double meal_ic_ratio	= 15;		// in g/U


		// Variables to hold the result
		private double correctionBolus	= 0;
		private double mealBolus		= 0;

		public BolusCalculator() {
		}

		private void updateResult() {
			// bgCurrent<0 <=>BG is not recorded
			if (bgCurrent < 0) {
				correctionBolus	= 0;
				mealBolus		= Number.round(mealCarbs / meal_ic_ratio, 0.05);
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
			if (bgCorrectAbove < bgCurrent) {
				correctionBolus = (bgCurrent - bgTarget) / correctionFactor;
				correctionBolus = Number.round(correctionBolus - adjustmentMeal_IOB, 0.05);
				if (correctionBolus < 0) {
					correctionBolus = 0;
				}

				correctionBolus = Number.round(correctionBolus - adjustmentCorrection_IOB, 0.05);
				if (correctionBolus < 0) {
					adjustmentCorrection_IOB = Math.abs(correctionBolus);
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

			mealBolus = Number.round(mealCarbs / meal_ic_ratio - adjustmentCorrection_IOB, 0.05);
			if (mealBolus < 0)
				mealBolus = 0;
		}

		public void printAllParameters() {
			System.out.println("PARAMETERS:");
			System.out.println("-----------");
			Display.print("BG calculation MIN", bgCalc_min);
			Display.print("BG calculation MAX", bgCalc_max);
			Display.print("reverse correction", reverseCorrection);
			Display.print("BG current", bgCurrent);
			Display.print("BG target", bgTarget);
			Display.print("BG correct above", bgCorrectAbove);
			Display.print("correction factor", correctionFactor);
			Display.print("adjustment meal IOB", adjustmentMeal_IOB);
			Display.print("adjustment correction IOB", adjustmentCorrection_IOB);
			Display.print("meal carbs", mealCarbs);
			Display.print("meal ic ratio", meal_ic_ratio);
			System.out.println("\n");
		}

		public void printResult() {
			System.out.println("RESULTS:");
			System.out.println("-----------");

			if (bgCorrectAbove < bgTarget) {
				System.out.println("BG target must always be less than BG CorrectAbove");
				return;
			}
			if (bgCurrent > 0) {
				// BG is recorded, check for special cases
				if (bgCurrent < bgCalc_min) {
					System.out.println("Too LOW BG! Go to hospital!");
					return;
				} else if (bgCurrent > bgCalc_max) {
					System.out.println("Too HIGH BG! Go to hospital!");
					return;
				}
			}
			if (durationBolusCalculatorOn < durationInsulinAction) {
				System.out.println("Bolus calculator is turned OFF during the Duration of Insulin Action (DIA)");
				return;
			}

			Display.print("correction bolus", correctionBolus);
			Display.print("meal bolus", mealBolus);

			double totalBolus = correctionBolus + mealBolus;
			if (totalBolus < 0)
				totalBolus = 0;
			Display.print("TOTAL BOLUS", totalBolus);
		}
	}

    /* -----------------------------------------------------
    For testing purpose
    ----------------------------------------------------- */

	public static void main(String[] args) {
		BolusCalculator bolusCalculator = new BolusCalculator();
		bolusCalculator.printAllParameters();
		bolusCalculator.updateResult();
		bolusCalculator.printResult();
	}
}
