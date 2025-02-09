package testing;

import utilities.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OperandTest {

	@Test
	void gettersAndSettersTest() {
		Operand op1 = new Operand(2, "in");

		op1.setUnit("ft");

		op1.setValue(2);
		assertEquals(2, op1.getValue());
		assertEquals("ft", op1.getUnit());
	}

	@Test
	void signTest() {
		Operand op1 = new Operand(2, "in");
		op1.changeSign();

		assertEquals(-2, op1.getValue());
	}

}
