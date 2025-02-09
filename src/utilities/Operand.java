package utilities;

/**
 * Object containing information about operands.
 * 
 * @author Cade Breslow
 * @version 1.0
 */
public class Operand {
	private double value;
	private String unit;

	/**
	 * Constructor for making Operand objects.
	 *
	 * @param value the value of the operand
	 * @param unit  the units of the operand
	 */
	public Operand(double value, String unit) {
		this.value = value;
		this.unit = unit;
	}

	/**
	 * Gets the value attribute.
	 * 
	 * @return value
	 */
	public double getValue() {
		return this.value;
	}

	/**
	 * Sets value attribute.
	 * 
	 * @param value the value to change value to
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * Gets the unit attribute.
	 * 
	 * @return unit
	 */
	public String getUnit() {
		return this.unit;
	}

	/**
	 * Sets the unit attribute.
	 * 
	 * @param unit the unit to change unit to
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * Changes the sign of the Operand.
	 */
	public void changeSign() {
		this.value = this.value * -1;
	}
}
