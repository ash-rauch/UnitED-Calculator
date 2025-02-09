package testing;

import utilities.*;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Testing Conjunction
 * 
 * @author Zachary Williams
 * @date 4/11/2024
 *
 */
class ConjunctionTest {

	// Testing for formatting of calculate method:
	// "FormatOfResult"
	@Test
	public void testFormatResults() {
		Operand unitless = new Operand(6.0999900092, "");
		Operand unitless2 = new Operand(2, "");

		Operand calc = Conjunction.calculate(unitless, unitless2, Operator.MULTIPLY, 2);

		assertEquals(12.2, calc.getValue());

		Operand unitless3 = new Operand(123.9999999999909, "");
		Operand unitless4 = new Operand(12.333333392, "");

		Operand calc2 = Conjunction.calculate(unitless3, unitless4, Operator.MULTIPLY, 9);

		assertEquals(1529.333340608, calc2.getValue());

		Operand calc3 = Conjunction.calculate(unitless3, unitless4, Operator.MULTIPLY, 0);

		assertEquals(1529, calc3.getValue());

		Operand calc4 = Conjunction.calculate(unitless3, unitless4, Operator.MULTIPLY, -1);

		assertEquals(1529, calc4.getValue());

	}

	/// Testing POWER calculations:

	@Test
	public void testPowerCalculations() {
		Operand untiless = new Operand(6, "ln");
		Operand power = new Operand(2, "");

		Operand calc = Conjunction.calculate(untiless, power, Operator.POWER, 20);

		assertEquals("ln", calc.getUnit());

		assertEquals(36, calc.getValue());

	}

	@Test
	public void testMultiplyUntiless() {
		Operand untiless = new Operand(6, "");
		Operand ton = new Operand(2, "ton");

		Operand calc = Conjunction.calculate(untiless, ton, Operator.MULTIPLY, 20);

		assertEquals("ton", calc.getUnit());

	}

	// MULTIPLE CONJUNCTION TESTING

	// CUSTOM UNITS TESTING

	@Test
	public void testCustomUnitsAdditions() {
		Operand miles = new Operand(6, "xl");
		Operand hour = new Operand(2, "zl");

		Operand calc = Conjunction.calculate(miles, hour, Operator.ADD, 20);

		assertEquals(null, calc);

	}

	@Test
	public void testCustomUnitsMultiplication() {
		Operand miles = new Operand(6, "xl");
		Operand hour = new Operand(2, "zl");

		Operand calc = Conjunction.calculate(miles, hour, Operator.MULTIPLY, 20);

		assertEquals(12, calc.getValue());
		assertEquals("xl-zl", calc.getUnit());
	}

	@Test
	public void testCustomUnitsMultiplicationthenDivision() {
		Operand miles = new Operand(6, "xl");
		Operand hour = new Operand(2, "zl");

		Operand calc1 = Conjunction.calculate(miles, hour, Operator.MULTIPLY, 20);

		assertEquals(12, calc1.getValue());
		assertEquals("xl-zl", calc1.getUnit());

		Operand calc2 = Conjunction.calculate(calc1, hour, Operator.DIVIDE, 20);

		assertEquals(6, calc2.getValue());
		assertEquals("xl-zl/zl", calc2.getUnit());
	}

	/// EXPONENT RECHECK TESTING-SECTION:

	@Test
	void testExponentAdd() {
		Operand feet2 = new Operand(6, "<html>ft<sup>2</sup><html>");
		Operand feet = new Operand(2, "ft");

		Operand finalCalc = Conjunction.calculate(feet2, feet, Operator.ADD, 20);

		assertEquals("<html>ft<sup>2</sup><html>", finalCalc.getUnit());
		assertEquals(8, finalCalc.getValue());

	}

	@Test
	void testExponentSubtract() {
		Operand feet2 = new Operand(8, "<html>ft<sup>2</sup><html>");
		Operand feet = new Operand(2, "ft");

		Operand finalCalc = Conjunction.calculate(feet2, feet, Operator.SUBTRACT, 20);

		assertEquals("<html>ft<sup>2</sup><html>", finalCalc.getUnit());
		assertEquals(6, finalCalc.getValue());

	}

//	@Test
//	void testExponentMultiply() {
//		Operand feet2 = new Operand(8, "<html>ft<sup>2</sup><html>");
//		Operand feet = new Operand(2, "ft");
//
//		Operand runningCalc1 = Conjunction.calculate(feet2, feet, Operator.MULTIPLY);
//
//		assertEquals("<html>ft<sup>3</sup><html>", runningCalc1.getUnit());
//		assertEquals(16, runningCalc1.getValue());
//
//		Operand runningCalc2 = Conjunction.calculate(runningCalc1, feet, Operator.MULTIPLY);
//
//		assertEquals("<html>ft<sup>4</sup><html>", runningCalc2.getUnit());
//		assertEquals(32, runningCalc2.getValue());
//	}

	@Test
	void testExponentDivide() {
		Operand feet2 = new Operand(32, "<html>ft<sup>4</sup><html>");
		Operand feet = new Operand(2, "ft");

		Operand runningCalc1 = Conjunction.calculate(feet2, feet, Operator.DIVIDE, 20);

		assertEquals("<html>ft<sup>3.0</sup><html>", runningCalc1.getUnit());
		assertEquals(16, runningCalc1.getValue());

		Operand runningCalc2 = Conjunction.calculate(runningCalc1, feet, Operator.DIVIDE, 20);

		assertEquals("<html>ft<sup>2.0</sup><html>", runningCalc2.getUnit());
		assertEquals(8, runningCalc2.getValue());

	}

	@Test
	public void testCustomUnitsExponent() {
		Operand miles = new Operand(6, "xl");
		Operand hour = new Operand(2, "xl");

		Operand calc = Conjunction.calculate(miles, hour, Operator.MULTIPLY, 20);

		assertEquals(12, calc.getValue());
		assertEquals("<html>xl<sup>2</sup><html>", calc.getUnit());

	}

//	@Test
//	void testMultiplicationInchesXInchesExponentCheck() {
//		Operand inch = new Operand(2, "in");
//		Operand inch2 = new Operand(2, "in");
//
//		Operand finalCalculation = Conjunction.calculate(inch, inch2, Operator.MULTIPLY);
//
//		assertEquals(4.0, finalCalculation.getValue());
//		assertEquals("<html>in<sup>2</sup><html>", finalCalculation.getUnit());
//
//		Operand inch3 = new Operand(4.0, "in");
//
//		Operand runOnCalc = Conjunction.calculate(finalCalculation, inch3, Operator.MULTIPLY);
//
////		assertEquals(16.0, runOnCalc.getValue());
////		assertEquals("<html>in<sup>3</sup><html>", runOnCalc.getUnit());
//
//		Operand inch4 = new Operand(10.0, "in");
//
//		Operand runOnCalc2 = Conjunction.calculate(runOnCalc, inch4, Operator.MULTIPLY);
//
//		assertEquals(160.0, runOnCalc2.getValue());
//		assertEquals("<html>in<sup>4</sup><html>", runOnCalc2.getUnit());
//
//		Operand inch5 = new Operand(10.0, "in");
//
//		Operand runOnCalc3 = Conjunction.calculate(runOnCalc2, inch4, Operator.MULTIPLY);
//
//		assertEquals(1600.0, runOnCalc3.getValue());
//		assertEquals("<html>in<sup>5</sup><html>", runOnCalc3.getUnit());
//	}

	// exponent/
	// same units
	// divide secondOperand == 0
	// equals return null

	@Test
	public void testSameUnitsDivideZeroReturnsNull() {
		Operand inch = new Operand(2, "<html>in<sup>2</sup><html>");
		Operand inch2 = new Operand(0, "in");

		Operand finalCalculation = Conjunction.calculate(inch, inch2, Operator.DIVIDE, 20);

		assertEquals(null, finalCalculation);
	}

	// case:
	// ft^2/ft
	// should equal = ft

	@Test
	public void testSameUnitsDivide2ExponentRemoved() {
		Operand inch = new Operand(2, "<html>in<sup>2</sup><html>");
		Operand inch2 = new Operand(4, "in");

		Operand finalCalculation = Conjunction.calculate(inch, inch2, Operator.DIVIDE, 20);

		assertEquals("in", finalCalculation.getUnit());
		assertEquals(0.5, finalCalculation.getValue());
	}

	// Test for basic arithmetic + ft
	// Ex: "8 + 9ft" = null

	@Test
	public void testBasicArithmeticTest() {
		Operand miles = new Operand(6, "");
		Operand hour = new Operand(2, "ft");

		Operand calc = Conjunction.calculate(miles, hour, Operator.ADD, 20);

		assertEquals(null, calc);

	}

	// UNITS TESTS

	// SPRINT 1 + 2 calculations

	@Test
	public void testConjunctionDefaultConstructor() {

		// Java add automatic constructor to all classes, even
		// utility classes, so this is a way to get full
		// coverage and create an instance of the utlity class
		Conjunction conjunction = new Conjunction();
	}

	@Test
	public void testCalculate_SameUnitTypeDifferentUnits() {
		Operand feetOperand = new Operand(5.0, "ft");
		Operand inchesOperand = new Operand(60.0, "in");
		Operator operator = Operator.ADD;
		assertNotNull(Conjunction.calculate(feetOperand, inchesOperand, operator, 20));
	}

	@Test
	public void testCalculate_DivideByZero() {
		Operand firstOperand = new Operand(10.0, "ft");
		Operand secondOperand = new Operand(0.0, "m");
		Operator operator = Operator.DIVIDE;
		assertNull(Conjunction.calculate(firstOperand, secondOperand, operator, 20));
	}

	@Test
	void testBasicMultiplication() {
		Operand feet2 = new Operand(6, "");
		Operand feet = new Operand(2, "");

		Operand finalCalculation = Conjunction.calculate(feet2, feet, Operator.MULTIPLY, 20);

		assertEquals("", finalCalculation.getUnit());
		assertEquals(12, finalCalculation.getValue());

	}

	@Test
	void testSubtractTwoDifferingUnitTypes() {
		Operand feet2 = new Operand(6, "ft");
		Operand tons = new Operand(2, "ton");

		Operand finalCalculation = Conjunction.calculate(feet2, tons, Operator.SUBTRACT, 20);

		assertEquals(null, finalCalculation);

	}

	@Test
	void testSubtractTwoSameUnitTypes() {
		Operand feet2 = new Operand(40, "in");
		Operand tons = new Operand(1, "ft");

		Operand finalCalculation = Conjunction.calculate(feet2, tons, Operator.SUBTRACT, 20);

		assertEquals(28, finalCalculation.getValue());
		assertEquals("in", finalCalculation.getUnit());

	}

	@Test
	void testDividewoDifferentUnitTypes() {
		Operand feet2 = new Operand(24, "in");
		Operand tons = new Operand(0, "ft");

		Operand finalCalculation = Conjunction.calculate(feet2, tons, Operator.DIVIDE, 20);

		assertEquals(null, finalCalculation);

	}

	@Test
	void testDividewoSameUnitsByZero() {
		Operand feet = new Operand(4, "ft");
		Operand feet2 = new Operand(0, "ft");

		Operand finalCalculation = Conjunction.calculate(feet, feet2, Operator.DIVIDE, 20);

		assertEquals(null, finalCalculation);

	}

	@Test
	void testSameUnitDivide() {
		Operand feet2 = new Operand(6, "ft");
		Operand feet = new Operand(0, "ft");

		Operand finalCalculation = Conjunction.calculate(feet2, feet, Operator.DIVIDE, 20);

		assertEquals(null, finalCalculation);
	}

	@Test
	void testAddDifferentTypesWillReturnNull() {
		Operand feet = new Operand(2, "ft");
		Operand second = new Operand(2, "sec");

		Operand finalCalc = Conjunction.calculate(feet, second, Operator.ADD, 20);

		assertEquals(null, finalCalc);

	}

	@Test
	void testAdditionInches() {
		Operand inch = new Operand(1, "in");
		Operand inch2 = new Operand(2, "in");

		Operand finalCalculation = null;

		finalCalculation = Conjunction.calculate(inch, inch2, Operator.ADD, 20);

		assertEquals(3.0, finalCalculation.getValue());
		assertEquals("in", finalCalculation.getUnit());

		inch = new Operand(8.0, "in");
		inch2 = new Operand(5.0, "in");

		finalCalculation = null;

		finalCalculation = Conjunction.calculate(inch, inch2, Operator.ADD, 20);

		assertEquals(13.0, finalCalculation.getValue());
		assertEquals("in", finalCalculation.getUnit());
	}

	@Test
	void testSubtractionInches() {
		Operand inch = new Operand(1.2, "in");
		Operand inch2 = new Operand(3.1, "in");

		Operand finalCalculation = null;

		finalCalculation = Conjunction.calculate(inch, inch2, Operator.SUBTRACT, 20);

		assertEquals(-1.9, finalCalculation.getValue(), 0.000001);
		assertEquals("in", finalCalculation.getUnit());
	}

	@Test
	void testDivisionInches() {
		Operand inch = new Operand(4, "in");
		Operand inch2 = new Operand(2, "in");

		Operand runningCalculation = null;

		runningCalculation = Conjunction.calculate(inch, inch2, Operator.DIVIDE, 20);

		assertEquals(2, runningCalculation.getValue());
		assertEquals("", runningCalculation.getUnit());

		Operand unitless = new Operand(4, "");

		runningCalculation = Conjunction.calculate(runningCalculation, unitless, Operator.DIVIDE, 20);

		assertEquals(0.5, runningCalculation.getValue());
		assertEquals("", runningCalculation.getUnit());

		Operand inch4 = new Operand(4, "in");

		runningCalculation = Conjunction.calculate(runningCalculation, inch4, Operator.DIVIDE, 20);

		assertEquals(0.125, runningCalculation.getValue());

	}

	@Test
	void testMultiplicationInches() {
		Operand inch = new Operand(3, "in");
		Operand inch2 = new Operand(9, "in");

		Operand finalCalculation = null;

		finalCalculation = Conjunction.calculate(inch, inch2, Operator.MULTIPLY, 20);

		assertEquals(27, finalCalculation.getValue());
		assertEquals("<html>in<sup>2</sup><html>", finalCalculation.getUnit());
	}

	@Test
	void testMultiplySlashConjunctionRemovesUnit() {
		Operand miles = new Operand(6, "mi");
		Operand hour = new Operand(2, "hr");

		Operand calc1 = Conjunction.calculate(miles, hour, Operator.DIVIDE, 20);

		assertEquals(3, calc1.getValue());
		assertEquals("mi/hr", calc1.getUnit());

		Operand hour2 = new Operand(5, "hr");

		Operand calc2 = Conjunction.calculate(calc1, hour2, Operator.MULTIPLY, 20);

		assertEquals(15, calc2.getValue());
		assertEquals("mi", calc2.getUnit());
	}

	// CONVERSION FACTORS

	@Test
	void testAdditionInchConversionFactors() {

		Operand inch = new Operand(2, "in");

		Operand feet = new Operand(2, "ft");

		Operand finalCalculation = null;

		finalCalculation = Conjunction.calculate(inch, feet, Operator.ADD, 20);

		assertEquals(26, finalCalculation.getValue());
		assertEquals("in", finalCalculation.getUnit());

		Operand yard = new Operand(2, "yd");

		finalCalculation = Conjunction.calculate(inch, yard, Operator.ADD, 20);

		assertEquals(74, finalCalculation.getValue());
		assertEquals("in", finalCalculation.getUnit());

		Operand mile = new Operand(2, "mi");

		finalCalculation = Conjunction.calculate(inch, mile, Operator.ADD, 20);

		assertEquals(126722, finalCalculation.getValue());
		assertEquals("in", finalCalculation.getUnit());

		Operand mm = new Operand(2, "mm");

		finalCalculation = Conjunction.calculate(inch, mm, Operator.ADD, 20);

		assertEquals(2.078740157480315, finalCalculation.getValue());
		assertEquals("in", finalCalculation.getUnit());

	}

	@Test
	public void testSecondtoAdd() {
		Operand sec = new Operand(6, "sec");
		Operand hour = new Operand(1, "hr");
		Operand month = new Operand(1, "mo");
		Operand year = new Operand(1, "yr");

		Operand runningCalc = Conjunction.calculate(sec, hour, Operator.ADD, 20);

		assertEquals(3606, runningCalc.getValue());
		assertEquals("sec", runningCalc.getUnit());

		Operand runningCalc2 = Conjunction.calculate(runningCalc, month, Operator.ADD, 20);

		assertEquals(2595606, runningCalc2.getValue());
		assertEquals("sec", runningCalc2.getUnit());

		Operand runningCalc3 = Conjunction.calculate(runningCalc2, year, Operator.ADD, 20);

		assertEquals(34131606, runningCalc3.getValue());
		assertEquals("sec", runningCalc3.getUnit());

	}

	@Test
	public void testMilesPerHourMultipliedByHour() {
		Operand miles = new Operand(6, "mi");
		Operand hour = new Operand(2, "hr");

		Operand milesPerHour = Conjunction.calculate(miles, hour, Operator.DIVIDE, 20);

		Operand hour5 = new Operand(5, "hr");

		Operand finalCalc = Conjunction.calculate(milesPerHour, hour5, Operator.MULTIPLY, 20);

		assertEquals(15.0, finalCalc.getValue());
		assertEquals("mi", finalCalc.getUnit());

		Operand miles2 = new Operand(10, "mi");

		Operand finalCalc2 = Conjunction.calculate(finalCalc, miles2, Operator.MULTIPLY, 20);

		assertEquals(150.0, finalCalc2.getValue());
		assertEquals("<html>mi<sup>2</sup><html>", finalCalc2.getUnit());

	}

	@Test
	public void testMilesPerHourMultipliedByTons() {
		Operand miles = new Operand(6, "mi");
		Operand hour = new Operand(2, "hr");

		Operand milesPerHour = Conjunction.calculate(miles, hour, Operator.DIVIDE, 20);

		Operand hour5 = new Operand(5, "tons");

		Operand finalCalc = Conjunction.calculate(milesPerHour, hour5, Operator.MULTIPLY, 20);

		assertEquals(15.0, finalCalc.getValue());
		assertEquals("mi/hr-tons", finalCalc.getUnit());

	}

	// TESTING CONVERSION FACTORS:

	// INCHES:

	// Test inch to centimeter conversion
	@Test
	public void testInchToCentimeterConversion() {
		Operand inch = new Operand(0, "cm"); // 1 inch = 2.54 centimeters
		Operand cm = new Operand(1, "in");

		Operand result = Conjunction.calculate(inch, cm, Operator.ADD, 20);

		assertEquals(2.54, result.getValue(), 0.001);
	}

	// Test inch to millimeter conversion
	@Test
	public void testInchToMillimeterConversion() {
		Operand inch = new Operand(0, "mm"); // 1 inch = 25.4 millimeters
		Operand mm = new Operand(1, "in");

		Operand result = Conjunction.calculate(inch, mm, Operator.ADD, 20);

		assertEquals(25.4, result.getValue(), 0.001);
		assertEquals("mm", result.getUnit());
	}

	// Test inch to meter conversion
	@Test
	public void testInchToMeterConversion() {
		Operand inch = new Operand(0, "m"); // 1 inch = 0.0254 meters
		Operand meter = new Operand(1, "in");

		Operand result = Conjunction.calculate(inch, meter, Operator.ADD, 20);

		assertEquals(0.0254, result.getValue(), 0.001);
		assertEquals("m", result.getUnit());
	}

	// Test inch to foot conversion
	@Test
	public void testInchToFootConversion() {
		Operand inch = new Operand(0, "ft"); // 1 inch = 0.0833333 feet
		Operand foot = new Operand(1, "in");

		Operand result = Conjunction.calculate(inch, foot, Operator.ADD, 20);

		assertEquals(0.0833333, result.getValue(), 0.001);
		assertEquals("ft", result.getUnit());
	}

	// Test inch to yard conversion
	@Test
	public void testInchToYardConversion() {
		Operand inch = new Operand(0, "yd"); // 1 inch = 0.0277778 yards
		Operand yard = new Operand(1, "in");

		Operand result = Conjunction.calculate(inch, yard, Operator.ADD, 20);

		assertEquals(0.0277778, result.getValue(), 0.001);
		assertEquals("yd", result.getUnit());
	}

	// Test inch to mile conversion
	@Test
	public void testInchToMileConversion() {
		Operand inch = new Operand(0, "mi"); // 1 inch
		Operand mile = new Operand(1, "in"); // 0 miles

		Operand result = Conjunction.calculate(inch, mile, Operator.ADD, 20); // Use MULTIPLY for conversion

		// Expected result is approximately 0.0000157828 miles
		assertEquals(0.0000157828, result.getValue(), 0.0000001);
		assertEquals("mi", result.getUnit());
	}

	// FEET:

	// Test feet to yard conversion
	@Test
	public void testFeetToYardConversion() {
		Operand feet = new Operand(0, "yd"); // 1 foot = 1/3 yards
		Operand yard = new Operand(1, "ft");

		Operand result = Conjunction.calculate(feet, yard, Operator.ADD, 20);

		assertEquals(1.0 / 3, result.getValue(), 0.001);
		assertEquals("yd", result.getUnit());
	}

	// Test feet to mile conversion
	@Test
	public void testFeetToMileConversion() {
		Operand feet = new Operand(0, "mi"); // 1 foot = 1/5280 miles
		Operand mile = new Operand(1, "ft");

		Operand result = Conjunction.calculate(feet, mile, Operator.ADD, 20);

		assertEquals(1.0 / 5280, result.getValue(), 0.0000001);
		assertEquals("mi", result.getUnit());
	}

	// Test feet to millimeter conversion
	@Test
	public void testFeetToMillimeterConversion() {
		Operand feet = new Operand(0, "mm"); // 1 foot = 304.8 millimeters
		Operand mm = new Operand(1, "ft");

		Operand result = Conjunction.calculate(feet, mm, Operator.ADD, 20);

		assertEquals(304.8, result.getValue(), 0.001);
		assertEquals("mm", result.getUnit());
	}

	// Test feet to centimeter conversion
	@Test
	public void testFeetToCentimeterConversion() {
		Operand feet = new Operand(0, "cm"); // 1 foot = 30.48 centimeters
		Operand cm = new Operand(1, "ft");

		Operand result = Conjunction.calculate(feet, cm, Operator.ADD, 20);

		assertEquals(30.48, result.getValue(), 0.001);
		assertEquals("cm", result.getUnit());
	}

	// Test feet to meter conversion
	@Test
	public void testFeetToMeterConversion() {
		Operand feet = new Operand(0, "m"); // 1 foot = 0.3048 meters
		Operand meter = new Operand(1, "ft");

		Operand result = Conjunction.calculate(feet, meter, Operator.ADD, 20);

		assertEquals(0.3048, result.getValue(), 0.001);
		assertEquals("m", result.getUnit());
	}

	// Test feet to kilometer conversion
	@Test
	public void testFeetToKilometerConversion() {
		Operand feet = new Operand(0, "km"); // 1 foot = 0.0003048 kilometers
		Operand km = new Operand(1, "ft");

		Operand result = Conjunction.calculate(feet, km, Operator.ADD, 20);

		assertEquals(0.0003048, result.getValue(), 0.0000001);
		assertEquals("km", result.getUnit());
	}

	// YARD

	// Test yard to mile conversion
	@Test
	public void testYardToMileConversion() {
		Operand yard = new Operand(0, "mi"); // 1 yard = 1/1760 miles
		Operand mile = new Operand(1, "yd");

		Operand result = Conjunction.calculate(yard, mile, Operator.ADD, 20);

		assertEquals(1.0 / 1760, result.getValue(), 0.0000001);
		assertEquals("mi", result.getUnit());
	}

	// Test yard to millimeter conversion
	@Test
	public void testYardToMillimeterConversion() {
		Operand yard = new Operand(0, "mm"); // 1 yard = 914.4 millimeters
		Operand mm = new Operand(1, "yd");

		Operand result = Conjunction.calculate(yard, mm, Operator.ADD, 20);

		assertEquals(914.4, result.getValue(), 0.001);
		assertEquals("mm", result.getUnit());
	}

	// Test yard to centimeter conversion
	@Test
	public void testYardToCentimeterConversion() {
		Operand yard = new Operand(0, "cm"); // 1 yard = 91.44 centimeters
		Operand cm = new Operand(1, "yd");

		Operand result = Conjunction.calculate(yard, cm, Operator.ADD, 20);

		assertEquals(91.44, result.getValue(), 0.001);
		assertEquals("cm", result.getUnit());
	}

	// Test yard to meter conversion
	@Test
	public void testYardToMeterConversion() {
		Operand yard = new Operand(0, "m"); // 1 yard = 0.9144 meters
		Operand meter = new Operand(1, "yd");

		Operand result = Conjunction.calculate(yard, meter, Operator.ADD, 20);

		assertEquals(0.9144, result.getValue(), 0.001);
		assertEquals("m", result.getUnit());
	}

	// Test yard to kilometer conversion
	@Test
	public void testYardToKilometerConversion() {
		Operand yard = new Operand(0, "km"); // 1 yard = 0.0009144 kilometers
		Operand km = new Operand(1, "yd");

		Operand result = Conjunction.calculate(yard, km, Operator.ADD, 20);

		assertEquals(0.0009144, result.getValue(), 0.0000001);
		assertEquals("km", result.getUnit());
	}

	// MILE:

	// Test mile to millimeter conversion
	@Test
	public void testMileToMillimeterConversion() {
		Operand mile = new Operand(0, "mm"); // 1 mile = 1609344 millimeters
		Operand mm = new Operand(1, "mi");

		Operand result = Conjunction.calculate(mile, mm, Operator.ADD, 20);

		assertEquals(1609344.0, result.getValue(), 0.001);
		assertEquals("mm", result.getUnit());
	}

	// Test mile to centimeter conversion
	@Test
	public void testMileToCentimeterConversion() {
		Operand mile = new Operand(0, "cm"); // 1 mile = 160934.4 centimeters
		Operand cm = new Operand(1, "mi");

		Operand result = Conjunction.calculate(mile, cm, Operator.ADD, 20);

		assertEquals(160934.4, result.getValue(), 0.001);
		assertEquals("cm", result.getUnit());
	}

	// Test mile to meter conversion
	@Test
	public void testMileToMeterConversion() {
		Operand mile = new Operand(0, "m"); // 1 mile = 1609.344 meters
		Operand meter = new Operand(1, "mi");

		Operand result = Conjunction.calculate(mile, meter, Operator.ADD, 20);

		assertEquals(1609.344, result.getValue(), 0.001);
		assertEquals("m", result.getUnit());
	}

	// Test mile to kilometer conversion
	@Test
	public void testMileToKilometerConversion() {
		Operand mile = new Operand(0, "km"); // 1 mile = 1.609344 kilometers
		Operand km = new Operand(1, "mi");

		Operand result = Conjunction.calculate(mile, km, Operator.ADD, 20);

		assertEquals(1.609344, result.getValue(), 0.0000001);
		assertEquals("km", result.getUnit());
	}

	// MILIMETER:

	// Test millimeter to centimeter conversion
	@Test
	public void testMillimeterToCentimeterConversion() {
		Operand mm = new Operand(0, "cm"); // 1 millimeter = 0.1 centimeters
		Operand cm = new Operand(1, "mm");

		Operand result = Conjunction.calculate(mm, cm, Operator.ADD, 20);

		assertEquals(0.1, result.getValue(), 0.001);
		assertEquals("cm", result.getUnit());
	}

	// Test millimeter to meter conversion
	@Test
	public void testMillimeterToMeterConversion() {
		Operand mm = new Operand(0, "m"); // 1 millimeter = 0.001 meters
		Operand meter = new Operand(1, "mm");

		Operand result = Conjunction.calculate(mm, meter, Operator.ADD, 20);

		assertEquals(0.001, result.getValue(), 0.001);
		assertEquals("m", result.getUnit());
	}

	// Test millimeter to kilometer conversion
	@Test
	public void testMillimeterToKilometerConversion() {
		Operand mm = new Operand(0, "km"); // 1 millimeter = 1.0E-6 kilometers
		Operand km = new Operand(1, "mm");

		Operand result = Conjunction.calculate(mm, km, Operator.ADD, 20);

		assertEquals(1.0E-6, result.getValue(), 0.0000001);
		assertEquals("km", result.getUnit());
	}

	// CENTIMETER:

	// Test centimeter to meter conversion
	@Test
	public void testCentimeterToMeterConversion() {
		Operand cm = new Operand(0, "m"); // 1 centimeter = 0.01 meters
		Operand meter = new Operand(1, "cm");

		Operand result = Conjunction.calculate(cm, meter, Operator.ADD, 20);

		assertEquals(0.01, result.getValue(), 0.001);
		assertEquals("m", result.getUnit());
	}

	// Test centimeter to kilometer conversion
	@Test
	public void testCentimeterToKilometerConversion() {
		Operand cm = new Operand(0, "km"); // 1 centimeter = 1.0E-5 kilometers
		Operand km = new Operand(1, "cm");

		Operand result = Conjunction.calculate(cm, km, Operator.ADD, 20);

		assertEquals(1.0E-5, result.getValue(), 0.0000001);
		assertEquals("km", result.getUnit());
	}

	// SECOND:

	// Test second to minute conversion
	@Test
	public void testSecondToMinuteConversion() {
		Operand sec = new Operand(0, "min"); // 1 second = 1/60 minutes
		Operand min = new Operand(1, "sec");

		Operand result = Conjunction.calculate(sec, min, Operator.ADD, 20);

		assertEquals(0.01666666666, result.getValue(), 0.0000001);
		assertEquals("min", result.getUnit());
	}

	// Test second to hour conversion
	@Test
	public void testSecondToHourConversion() {
		Operand sec = new Operand(0, "hr"); // 1 second = 1/3600 hours
		Operand hour = new Operand(1, "sec");

		Operand result = Conjunction.calculate(sec, hour, Operator.ADD, 20);

		assertEquals(0.00027777777, result.getValue(), 0.0000001);
		assertEquals("hr", result.getUnit());
	}

	// Test second to day conversion
	@Test
	public void testSecondToDayConversion() {
		Operand sec = new Operand(0, "day"); // 1 second = 1/86400 days
		Operand day = new Operand(1, "sec");

		Operand result = Conjunction.calculate(sec, day, Operator.ADD, 20);

		assertEquals(0.00001157407, result.getValue(), 0.0000001);
		assertEquals("day", result.getUnit());
	}

	// Test second to month conversion
	@Test
	public void testSecondToMonthConversion() {
		Operand sec = new Operand(0, "mo"); // 1 second = 1/(60 * 60 * 24 * 30) months
		Operand month = new Operand(1, "sec");

		Operand result = Conjunction.calculate(sec, month, Operator.ADD, 20);

		assertEquals(1 / (60.0 * 60.0 * 24.0 * 30.0), result.getValue(), 0.0000001);
		assertEquals("mo", result.getUnit());
	}

	// Test second to year conversion
	@Test
	public void testSecondToYearConversion() {
		Operand sec = new Operand(0, "yr"); // 1 second = 1/(365 * 24 * 60 * 60) years
		Operand year = new Operand(1, "sec");

		Operand result = Conjunction.calculate(sec, year, Operator.ADD, 20);

		assertEquals(1 / (365.0 * 24.0 * 60.0 * 60.0), result.getValue(), 0.0000001);
		assertEquals("yr", result.getUnit());
	}

	// MINUTE:

	// Test minute to hour conversion
	@Test
	public void testMinuteToHourConversion() {
		Operand min = new Operand(0, "hr"); // 1 minute = 1/60 hours
		Operand hour = new Operand(1, "min");

		Operand result = Conjunction.calculate(min, hour, Operator.ADD, 20);

		assertEquals(1.0 / 60, result.getValue(), 0.0000001);
		assertEquals("hr", result.getUnit());
	}

	// Test minute to day conversion
	@Test
	public void testMinuteToDayConversion() {
		Operand min = new Operand(0, "day"); // 1 minute = 1/(60 * 24) days
		Operand day = new Operand(1, "min");

		Operand result = Conjunction.calculate(min, day, Operator.ADD, 20);

		assertEquals(1.0 / (60 * 24), result.getValue(), 0.0000001);
		assertEquals("day", result.getUnit());
	}

	// Test minute to month conversion
	@Test
	public void testMinuteToMonthConversion() {
		Operand min = new Operand(0, "mo"); // 1 minute = 1/(60 * 24 * 30) months
		Operand month = new Operand(1, "min");

		Operand result = Conjunction.calculate(min, month, Operator.ADD, 20);

		assertEquals(1.0 / (60 * 24 * 30), result.getValue(), 0.0000001);
		assertEquals("mo", result.getUnit());
	}

	// Test minute to year conversion
	@Test
	public void testMinuteToYearConversion() {
		Operand min = new Operand(0, "yr"); // 1 minute = 1/(60 * 24 * 365) years
		Operand year = new Operand(1, "min");

		Operand result = Conjunction.calculate(min, year, Operator.ADD, 20);

		assertEquals(1.0 / (60 * 24 * 365), result.getValue(), 0.0000001);
		assertEquals("yr", result.getUnit());
	}

	// HOUR:

	// Test hour to minute conversion
	@Test
	public void testHourToMinuteConversion() {
		Operand hour = new Operand(0, "min"); // 1 hour = 60 minutes
		Operand minute = new Operand(1, "hr");

		Operand result = Conjunction.calculate(hour, minute, Operator.ADD, 20);

		assertEquals(60.0, result.getValue(), 0.0001);
		assertEquals("min", result.getUnit());
	}

	// Test hour to day conversion
	@Test
	public void testHourToDayConversion() {
		Operand hour = new Operand(0, "day"); // 1 hour = 1/24 days
		Operand day = new Operand(1, "hr");

		Operand result = Conjunction.calculate(hour, day, Operator.ADD, 20);

		assertEquals(1.0 / 24.0, result.getValue(), 0.0001);
		assertEquals("day", result.getUnit());
	}

	// Test hour to month conversion
	@Test
	public void testHourToMonthConversion() {
		Operand hour = new Operand(0, "mo"); // 1 hour = 1/(24 * 30) months
		Operand month = new Operand(1, "hr");

		Operand result = Conjunction.calculate(hour, month, Operator.ADD, 20);

		assertEquals("mo", result.getUnit());
	}

	// DAY:

	// Test day to month conversion
	@Test
	public void testDayToMonthConversion() {
		Operand day = new Operand(0, "mo"); // 1 day = 1/30 months
		Operand month = new Operand(1, "day");

		Operand result = Conjunction.calculate(day, month, Operator.ADD, 20);

		assertEquals(1.0 / 30.0, result.getValue(), 0.0001);
		assertEquals("mo", result.getUnit());
	}

	// Test day to year conversion
	@Test
	public void testDayToYearConversion() {
		Operand day = new Operand(0, "yr"); // 1 day = 1/365 years
		Operand year = new Operand(1, "day");

		Operand result = Conjunction.calculate(day, year, Operator.ADD, 20);

		assertEquals(1.0 / 365.0, result.getValue(), 0.0001);
		assertEquals("yr", result.getUnit());
	}

	// POWER CONVERSION FACTORS:

	// Test watt to kilowatt conversion
	@Test
	public void testWattToKilowattConversion() {
		Operand watt = new Operand(0, "kw"); // 1 watt = 0.001 kilowatt
		Operand kilowatt = new Operand(1, "w");

		Operand result = Conjunction.calculate(watt, kilowatt, Operator.ADD, 20);

		assertEquals(0.001, result.getValue(), 0.0001);
		assertEquals("kw", result.getUnit());
	}

	// Test kilowatt to watt conversion
	@Test
	public void testKilowattToWattConversion() {
		Operand kilowatt = new Operand(0, "w"); // 1 kilowatt = 1000 watts
		Operand watt = new Operand(1, "kw");

		Operand result = Conjunction.calculate(kilowatt, watt, Operator.ADD, 20);

		assertEquals(1000, result.getValue(), 0.0001);
		assertEquals("w", result.getUnit());
	}

	// VOLUMES:

	// CUBIC_CENTIMETER

	// Test cubic centimeter to pint conversion
	@Test
	public void testCubicCentimeterToPintConversion() {
		Operand cc = new Operand(0, "pt"); // 1 cubic centimeter = 0.00211338 pint
		Operand pint = new Operand(1, "cc");

		Operand result = Conjunction.calculate(cc, pint, Operator.ADD, 20);

		assertEquals(0.00211338, result.getValue(), 0.0001);
		assertEquals("pt", result.getUnit());
	}

	// Test cubic centimeter to quart conversion
	@Test
	public void testCubicCentimeterToQuartConversion() {
		Operand cc = new Operand(0, "qt"); // 1 cubic centimeter = 0.00105669 quart
		Operand quart = new Operand(1, "cc");

		Operand result = Conjunction.calculate(cc, quart, Operator.ADD, 20);

		assertEquals(0.00105669, result.getValue(), 0.0001);
		assertEquals("qt", result.getUnit());
	}

	// Test cubic centimeter to liter conversion
	@Test
	public void testCubicCentimeterToLiterConversion() {
		Operand cc = new Operand(0, "l"); // 1 cubic centimeter = 0.001 liter
		Operand liter = new Operand(1, "cc");

		Operand result = Conjunction.calculate(cc, liter, Operator.ADD, 20);

		assertEquals(0.001, result.getValue(), 0.0001);
		assertEquals("l", result.getUnit());
	}

	// Test cubic centimeter to gallon conversion
	@Test
	public void testCubicCentimeterToGallonConversion() {
		Operand cc = new Operand(0, "gal"); // 1 cubic centimeter = 0.000264172 gallon
		Operand gallon = new Operand(1, "cc");

		Operand result = Conjunction.calculate(cc, gallon, Operator.ADD, 20);

		assertEquals(0.000264172, result.getValue(), 0.0001);
		assertEquals("gal", result.getUnit());
	}

	// PINT

	// Test pint to quart conversion
	@Test
	public void testPintToQuartConversion() {
		Operand pint = new Operand(0, "qt"); // 1 pint = 0.5 quart
		Operand quart = new Operand(1, "pt");

		Operand result = Conjunction.calculate(pint, quart, Operator.ADD, 20);

		assertEquals(0.5, result.getValue(), 0.0001);
		assertEquals("qt", result.getUnit());
	}

	// Test pint to liter conversion
	@Test
	public void testPintToLiterConversion() {
		Operand pint = new Operand(0, "l"); // 1 pint = 0.473176 liter
		Operand liter = new Operand(1, "pt");

		Operand result = Conjunction.calculate(pint, liter, Operator.ADD, 20);

		assertEquals(0.473176, result.getValue(), 0.0001);
		assertEquals("l", result.getUnit());
	}

	// Test pint to gallon conversion
	@Test
	public void testPintToGallonConversion() {
		Operand pint = new Operand(0, "gal"); // 1 pint = 0.125 gallon
		Operand gallon = new Operand(1, "pt");

		Operand result = Conjunction.calculate(pint, gallon, Operator.ADD, 20);

		assertEquals(0.125, result.getValue(), 0.0001);
		assertEquals("gal", result.getUnit());
	}

	// QUART

	// Test quart to gallon conversion
	@Test
	public void testQuartToGallonConversion() {
		Operand quart = new Operand(0, "gal"); // 1 quart = 0.25 gallon
		Operand gallon = new Operand(1, "qt");

		Operand result = Conjunction.calculate(quart, gallon, Operator.ADD, 20);

		assertEquals(0.25, result.getValue(), 0.0001);
		assertEquals("gal", result.getUnit());
	}

	// Test quart to liter conversion
	@Test
	public void testQuartToLiterConversion() {
		Operand quart = new Operand(0, "l"); // 1 quart = 0.946353 liter
		Operand liter = new Operand(1, "qt");

		Operand result = Conjunction.calculate(quart, liter, Operator.ADD, 20);

		assertEquals(0.946353, result.getValue(), 0.0001);
		assertEquals("l", result.getUnit());
	}

	// LITER

	// Test liter to gallon conversion
	@Test
	public void testLiterToGallonConversion() {
		Operand liter = new Operand(0, "gal"); // 1 liter = 0.264172 gallon
		Operand gallon = new Operand(1, "l");

		Operand result = Conjunction.calculate(liter, gallon, Operator.ADD, 20);

		assertEquals(0.264172, result.getValue(), 0.0001);
		assertEquals("gal", result.getUnit());
	}

	// GALLON

	// Test gallon to cubic centimeter conversion
	@Test
	public void testGallonToCubicCentimeterConversion() {
		Operand gallon = new Operand(0, "cc"); // 1 gallon = 3785.41 cubic centimeters
		Operand cubicCentimeter = new Operand(1, "gal");

		Operand result = Conjunction.calculate(gallon, cubicCentimeter, Operator.ADD, 20);

		assertEquals(3785.41, result.getValue(), 0.01);
		assertEquals("cc", result.getUnit());
	}

	// Test gallon to pint conversion
	@Test
	public void testGallonToPintConversion() {
		Operand gallon = new Operand(0, "pt"); // 1 gallon = 8 pints
		Operand pint = new Operand(1, "gal");

		Operand result = Conjunction.calculate(gallon, pint, Operator.ADD, 20);

		assertEquals(8.0, result.getValue(), 0.0001);
		assertEquals("pt", result.getUnit());
	}

	// Test gallon to quart conversion
	@Test
	public void testGallonToQuartConversion() {
		Operand gallon = new Operand(0, "qt"); // 1 gallon = 4 quarts
		Operand quart = new Operand(1, "gal");

		Operand result = Conjunction.calculate(gallon, quart, Operator.ADD, 20);

		assertEquals(4.0, result.getValue(), 0.0001);
		assertEquals("qt", result.getUnit());
	}

	// Test gallon to liter conversion
	@Test
	public void testGallonToLiterConversion() {
		Operand gallon = new Operand(0, "l"); // 1 gallon = 3.78541 liters
		Operand liter = new Operand(1, "gal");

		Operand result = Conjunction.calculate(gallon, liter, Operator.ADD, 20);

		assertEquals(3.78541, result.getValue(), 0.0001);
		assertEquals("l", result.getUnit());
	}

	// WEIGHT CONVERSION FACTORS

	// KILOGRAM

	// Test kilogram to gram conversion
	@Test
	public void testKilogramToGramConversion() {
		Operand kilogram = new Operand(0, "g"); // 1 kilogram = 1000 grams
		Operand gram = new Operand(1, "kg");

		Operand result = Conjunction.calculate(kilogram, gram, Operator.ADD, 20);

		assertEquals(1000.0, result.getValue(), 0.0001);
		assertEquals("g", result.getUnit());
	}

	// Test kilogram to ounce conversion
	@Test
	public void testKilogramToOunceConversion() {
		Operand kilogram = new Operand(0, "oz"); // 1 kilogram = 35.27396 ounces
		Operand ounce = new Operand(1, "kg");

		Operand result = Conjunction.calculate(kilogram, ounce, Operator.ADD, 20);

		assertEquals(35.27396, result.getValue(), 0.0001);
		assertEquals("oz", result.getUnit());
	}

	// Test kilogram to lb conversion
	@Test
	public void testKilogramToPoundConversion() {
		Operand kilogram = new Operand(0, "lb"); // 1 kilogram = 2.20462 pounds
		Operand pound = new Operand(1, "kg");

		Operand result = Conjunction.calculate(kilogram, pound, Operator.ADD, 20);

		assertEquals(2.20462, result.getValue(), 0.0001);
		assertEquals("lb", result.getUnit());
	}

	// Test kilogram to ton conversion
	@Test
	public void testKilogramToTonConversion() {
		Operand kilogram = new Operand(0, "ton"); // 1 kilogram = 0.001 tons
		Operand ton = new Operand(1, "kg");

		Operand result = Conjunction.calculate(kilogram, ton, Operator.ADD, 20);

		assertEquals(0.001, result.getValue(), 0.0001);
		assertEquals("ton", result.getUnit());
	}

	// GRAM

	// Test gram to ounce conversion
	@Test
	public void testGramToOunceConversion() {
		Operand gram = new Operand(0, "oz"); // 1 gram = 0.03527396 ounces
		Operand ounce = new Operand(1, "g");

		Operand result = Conjunction.calculate(gram, ounce, Operator.ADD, 20);

		assertEquals(0.03527396, result.getValue(), 0.0001);
		assertEquals("oz", result.getUnit());
	}

	// Test gram to pound conversion
	@Test
	public void testGramToPoundConversion() {
		Operand gram = new Operand(0, "lb"); // 1 gram = 0.00220462 pounds
		Operand pound = new Operand(1, "g");

		Operand result = Conjunction.calculate(gram, pound, Operator.ADD, 20);

		assertEquals(0.00220462, result.getValue(), 0.0001);
		assertEquals("lb", result.getUnit());
	}

	// Test gram to ton conversion
	@Test
	public void testGramToTonConversion() {
		Operand gram = new Operand(0, "ton"); // 1 gram = 0.000001 tons
		Operand ton = new Operand(1, "g");

		Operand result = Conjunction.calculate(gram, ton, Operator.ADD, 20);

		assertEquals(0.000001, result.getValue(), 0.0001);
		assertEquals("ton", result.getUnit());
	}

	// POUND

	// Test pound to ounce conversion
	@Test
	public void testPoundToOunceConversion() {
		Operand pound = new Operand(0, "oz"); // 1 pound = 16 ounces
		Operand ounce = new Operand(1, "lb");

		Operand result = Conjunction.calculate(pound, ounce, Operator.ADD, 20);

		assertEquals(16.0, result.getValue(), 0.0001);
		assertEquals("oz", result.getUnit());
	}

	// Test pound to ton conversion
	@Test
	public void testPoundToTonConversion() {
		Operand pound = new Operand(0, "ton"); // 1 pound = 0.0005 tons
		Operand ton = new Operand(1, "lb");

		Operand result = Conjunction.calculate(pound, ton, Operator.ADD, 20);

		assertEquals(0.0005, result.getValue(), 0.0001);
		assertEquals("ton", result.getUnit());
	}

	// Test pound to gram conversion
	@Test
	public void testPoundToGramConversion() {
		Operand pound = new Operand(0, "g"); // 1 pound = 453.592 grams
		Operand gram = new Operand(1, "lb");

		Operand result = Conjunction.calculate(pound, gram, Operator.ADD, 20);

		assertEquals(453.5929094356397, result.getValue(), 0.0001);
		assertEquals("g", result.getUnit());
	}

	// Test pound to kilogram conversion
	@Test
	public void testPoundToKilogramConversion() {
		Operand pound = new Operand(0, "kg"); // 1 pound = 0.453592 kilograms
		Operand kilogram = new Operand(1, "lb");

		Operand result = Conjunction.calculate(pound, kilogram, Operator.ADD, 20);

		assertEquals(0.453592, result.getValue(), 0.0001);
		assertEquals("kg", result.getUnit());
	}

	// CURRENCY
	// Test cent to dollar conversion
	@Test
	public void testCentToDollarConversion() {
		Operand cent = new Operand(0, "$"); // 1 cent = 0.01 USD
		Operand dollar = new Operand(1, "c");

		Operand result = Conjunction.calculate(cent, dollar, Operator.ADD, 20);

		assertEquals(0.01, result.getValue(), 0.0001);
		assertEquals("$", result.getUnit());
	}

	// Test dollar to cent conversion
	@Test
	public void testDollarToCentConversion() {
		Operand dollar = new Operand(0, "c"); // 1 USD = 100 cents
		Operand cent = new Operand(1, "$");

		Operand result = Conjunction.calculate(dollar, cent, Operator.ADD, 20);

		assertEquals(100.0, result.getValue(), 0.0001);
		assertEquals("c", result.getUnit());
	}

}
