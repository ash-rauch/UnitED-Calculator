package utilities;

/**
 * 
 * Utility class to process the user input and call on conjunction calculations
 * 
 * @author Colin Kirkwood and bresloct
 * @version 3/27/2024
 */
public class Calculations {
	private static Operand firstOperand = new Operand(0.0, "");
	private static Operand secondOperand = new Operand(0.0, "");
	private static Operator middleOperator = null;
	private static Operand result = new Operand(0.0, "");

	/**
	 * Gets first value of user input
	 * 
	 * @return first value
	 */
	public static double getFirstValue() {
		return firstOperand.getValue();
	}

	/**
	 * Getter first unit of user input
	 * 
	 * @return first unit
	 */
	public static String getFirstUnit() {
		return firstOperand.getUnit();
	}

	/**
	 * Setter for first Operand
	 * 
	 * @param value of first operand
	 * @param unit  of first operand
	 */
	public static void setFirstOperand(double value, String unit) {
		firstOperand.setValue(value);
		firstOperand.setUnit(unit);
	}

	/**
	 * Getter for second Value
	 * 
	 * @return second value
	 */
	public static double getSecondValue() {
		return secondOperand.getValue();
	}

	/**
	 * Getter for second unit
	 * 
	 * @return second unit
	 */
	public static String getSecondUnit() {
		return secondOperand.getUnit();
	}

	/**
	 * Setter for second Operand
	 * 
	 * @param value of second operand
	 * @param unit  of second operand
	 */
	public static void setSecondOperand(double value, String unit) {
		secondOperand.setValue(value);
		secondOperand.setUnit(unit);
	}

	/**
	 * Getter for middle Operator
	 * 
	 * @return middle operator
	 */
	public static Operator getMiddleOperator() {
		return middleOperator;
	}

	/**
	 * Setter for Middle Operator
	 * 
	 * @param op middle operator
	 */
	public static void setMiddleOperator(Operator op) {
		middleOperator = op;
	}

	/**
	 * Getter for result value
	 * 
	 * @return result value
	 */
	public static double getResultValue() {
		return result.getValue();
	}

	/**
	 * Getter for result unit
	 * 
	 * @return result unit
	 */
	public static String getResultUnit() {
		return result.getUnit();
	}

	/**
	 * Setter for result
	 * 
	 * @param value of result
	 * @param unit  of result
	 */
	public static void setResult(double value, String unit) {
		result.setValue(value);
		result.setUnit(unit);
	}

	/**
	 * Performs calculation
	 * 
	 * @param maxDigits to format calculation
	 * @return result of calculation
	 */
	public static Operand calculate(int maxDigits) {
		result = Conjunction.calculate(firstOperand, secondOperand, middleOperator, maxDigits);
		return result;
	}

}