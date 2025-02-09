package testing;

/**
 * Tests for Calculations class.
 * 
 * @author Cade Breslow
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utilities.Calculations;
import utilities.Operand;
import utilities.Operator;

class CalculationsTest {

	@Test
	void getFirstValueTest() {
		Calculations.setFirstOperand(2.0, Calculations.getFirstUnit());
		assertEquals(2.0, Calculations.getFirstValue());
	}

	@Test
	void getSecondValueTest() {
		Calculations.setSecondOperand(2.0, Calculations.getSecondUnit());
		assertEquals(2.0, Calculations.getSecondValue());
	}

	@Test
	void getFirstUnitTest() {
		Calculations.setFirstOperand(Calculations.getFirstValue(), "ft");
		assertEquals("ft", Calculations.getFirstUnit());
	}

	@Test
	void getSecondUnitTest() {
		Calculations.setSecondOperand(Calculations.getSecondValue(), "ft");
		assertEquals("ft", Calculations.getSecondUnit());
	}

	@Test
	void getMiddleOperator() {
		Calculations.setMiddleOperator(Operator.ADD);
		assertEquals(Operator.ADD, Calculations.getMiddleOperator());
	}

	@Test
	void calculateTest() {
		Calculations.setFirstOperand(2.0, "ft");
		Calculations.setSecondOperand(3.0, "ft");
		Calculations.setMiddleOperator(Operator.ADD);
		Operand result = Calculations.calculate(20);
		assertEquals(5.0, result.getValue());
		assertEquals("ft", result.getUnit());
	}
}
