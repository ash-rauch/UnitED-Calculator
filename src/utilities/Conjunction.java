package utilities;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Class to handle conjunction and binary operations.
 * 
 * @author Zach Williams
 * @version 2/28/2024
 */
public class Conjunction {

	private class formatHTML {

		static String unitIdentifierBeginning = "l>";
		static String unitIdentifierEnd = "<s";
		static String superStringIdentifierBeginning = "p>";
		static String superStringIdentifierEnd = "</";

		static String firstUnitBeforeExponent(String firstUnit) {

			int exponentIndexBeginning = firstUnit.indexOf(superStringIdentifierBeginning);
			int exponentIndexEnd = firstUnit.indexOf(superStringIdentifierEnd);
			int unitIndexBeginning = firstUnit.indexOf(unitIdentifierBeginning);
			int unitIndexEnd = firstUnit.indexOf(unitIdentifierEnd);

			String firstUnitBeforeExponent = firstUnit.substring(unitIndexBeginning + 2, unitIndexEnd);

			return firstUnitBeforeExponent;
		}

		static String firstUnitExponent(String firstUnit) {

			int exponentIndexBeginning = firstUnit.indexOf(superStringIdentifierBeginning);
			int exponentIndexEnd = firstUnit.indexOf(superStringIdentifierEnd);
			int unitIndexBeginning = firstUnit.indexOf(unitIdentifierBeginning);
			int unitIndexEnd = firstUnit.indexOf(unitIdentifierEnd);

			String firstUnitExponent = firstUnit.substring(exponentIndexBeginning + 2, exponentIndexEnd);

			return firstUnitExponent;
		}
	}

	/**
	 * Formats input value by setting maxDigits to the right of decimal.
	 * 
	 * @param resultValue to be formatted
	 * @param maxDigits   of decimal
	 * @return formatted double
	 */
	private static double maxDigitsFormatter(double resultValue, int maxDigits) {
		StringBuilder pattern = new StringBuilder("#0.");

		for (int i = 0; i < maxDigits; i++) {
			pattern.append("0");
		}

		DecimalFormat decimalFormat = new DecimalFormat(pattern.toString());

		String formattedNumber = decimalFormat.format(resultValue);

		return Double.parseDouble(formattedNumber);

	}

	/**
	 * 
	 * Performs/handles exponent calculations, binary operations, unitless and unit
	 * calculations, and "/" + "-" conjunctions.
	 * 
	 * @param firstOperand  the first operand of the calculation
	 * @param secondOperand the second operand of the calculation
	 * @param operator      the second operator
	 *
	 * @return the result of the calculation
	 */
	public static Operand calculate(Operand firstOperand, Operand secondOperand, Operator operator, int maxDigits) {
		String firstUnit = firstOperand.getUnit();
		String secondUnit = secondOperand.getUnit();
		String exponentString;
		String unitBeforeSuperScript;
		double newExponent;
		double resultValue = 0.0;
		Operand returnedOperand = null;

		// EXPONENT CALCULATION CHECK FIRST

		if (firstUnit.contains("<html>")) {

			String firstUnitBeforeExponent = formatHTML.firstUnitBeforeExponent(firstUnit);
			String firstUnitExponent = formatHTML.firstUnitExponent(firstUnit);

			if (operator == Operator.POWER) {

				secondOperand.setUnit("");

				resultValue = Math.pow(firstOperand.getValue(), secondOperand.getValue());

				return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstUnit);
			}

			if (firstUnitBeforeExponent.equals(secondUnit)) {
				switch (operator) {

				case ADD:
					resultValue = firstOperand.getValue() + secondOperand.getValue();

					return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstUnit);

				case SUBTRACT:
					resultValue = firstOperand.getValue() - secondOperand.getValue();

					return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstUnit);
				case MULTIPLY:

					resultValue = firstOperand.getValue() * secondOperand.getValue();

					newExponent = Integer.parseInt(firstUnitExponent) + 1;

					if (firstUnit.contains("/")) {
						int perIndex = firstUnit.indexOf("/");
						String firstFirstHalfUnit = firstUnit.substring(0, perIndex);
						String firstSecondHalfUnit = firstUnit.substring(perIndex + 1, firstUnit.length());

						return new Operand(maxDigitsFormatter(resultValue, maxDigits),
								"<html>" + firstUnitBeforeExponent + "<sup>" + Double.toString(newExponent)
										+ "</sup><html>" + "/" + firstSecondHalfUnit);
					}
					return new Operand(maxDigitsFormatter(resultValue, maxDigits), "<html>" + firstUnitBeforeExponent
							+ "<sup>" + Double.toString(newExponent) + "</sup><html>");

				case DIVIDE:

					if (secondOperand.getValue() == 0) {
						System.out.println("Error: Division by zero");
						return null;
					}

					resultValue = firstOperand.getValue() / secondOperand.getValue();

					newExponent = Double.parseDouble(firstUnitExponent) - 1;

					// case:
					// ft^2/ft
					// should equal = ft
					if (newExponent <= 1) {
						return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstUnitBeforeExponent);
					}

					return new Operand(maxDigitsFormatter(resultValue, maxDigits), "<html>" + firstUnitBeforeExponent
							+ "<sup>" + Double.toString(newExponent) + "</sup><html>");
				case POWER:
					break;
				default:
					break;
				}

			}
		}

		// first case : not the same Unit
		// perform basic binary operation
		// return a new Operand

		else if (!firstUnit.equals(secondUnit)) {
			Units firstUnitEnum;
			Units secondUnitEnum;

			// Different unit types, apply conversion factor
			Double conversionFactor;

			switch (operator) {

			case POWER:

				secondOperand.setUnit("");

				resultValue = Math.pow(firstOperand.getValue(), secondOperand.getValue());

				return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstUnit);

			case ADD:

				firstUnitEnum = ConversionFactors.getUnitEnum(firstOperand.getUnit());
				secondUnitEnum = ConversionFactors.getUnitEnum(secondOperand.getUnit());

				// if the firstUnit's enum type does NOT equal the secondUnit's enum type
				// return null which will be handled to GUI to display "ERR"

				if (firstUnitEnum == null || secondUnitEnum == null) {
					return null;
				} else if (!firstUnitEnum.getType().equals(secondUnitEnum.getType())) {
					return null;
				}

				else {
					conversionFactor = ConversionFactors.conversionFactors.get(secondUnit).get(firstUnit);

					resultValue = firstOperand.getValue() + (secondOperand.getValue() * conversionFactor);

					return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstUnit);
				}

			case SUBTRACT:

				firstUnitEnum = ConversionFactors.getUnitEnum(firstOperand.getUnit());
				secondUnitEnum = ConversionFactors.getUnitEnum(secondOperand.getUnit());

				if (firstUnitEnum == null) {
					return null;
				}

				// if the firstUnit's enum type does NOT equal the secondUnit's enum type
				// return null which will be handled to GUI to display "ERR"
				if (!firstUnitEnum.getType().equals(secondUnitEnum.getType())) {
					return null;
				} else {
					conversionFactor = ConversionFactors.conversionFactors.get(secondUnit).get(firstUnit);

					resultValue = firstOperand.getValue() - (secondOperand.getValue() * conversionFactor);

					return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstUnit);
				}

			case MULTIPLY:

				resultValue = firstOperand.getValue() * secondOperand.getValue();

				// handle - ex: 10mi/hr * 5hr = 15.0mi
				if (firstOperand.getUnit().contains("/")) {
					int perIndex = firstUnit.indexOf("/");
					String firstFirstHalfUnit = firstUnit.substring(0, perIndex);
					String firstSecondHalfUnit = firstUnit.substring(perIndex + 1, firstUnit.length());

					if (firstSecondHalfUnit.equals(secondUnit)) {
						return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstFirstHalfUnit);
					}
					if (firstFirstHalfUnit.equals(secondUnit)) {
						return new Operand(maxDigitsFormatter(resultValue, maxDigits), "<html>" + firstFirstHalfUnit
								+ "<sup>" + 2 + "</sup><html>" + "/" + firstSecondHalfUnit);
					}
				}

				// handle - ex: 1.0 x 9ton = 9.0ton (instead of 9.0-ton)
				if (firstOperand.getUnit() == "") {
					return new Operand(maxDigitsFormatter(resultValue, maxDigits), secondUnit);
				}

				// special case where the value returned should be squared
				return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstUnit + "-" + secondUnit);

			case DIVIDE:
				if (secondOperand.getValue() == 0) {
					System.out.println("Error: Division by zero");
					return null;
				}

				resultValue = firstOperand.getValue() / secondOperand.getValue();

				// handle - ex: 9.0 / 3ton = 3.0ton (instead of 3.0/ton)
				if (firstOperand.getUnit() == "") {
					return new Operand(maxDigitsFormatter(resultValue, maxDigits), secondUnit);
				}

				if (secondOperand.getUnit() == "") {
					return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstUnit);
				}

				return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstUnit + "/" + secondUnit);
			}

		} else {
			// Same unit type, perform normal calculation for
			// if (firstUnit == secondUnit) {

			switch (operator) {

			case POWER:

				secondOperand.setUnit("");

				resultValue = Math.pow(firstOperand.getValue(), secondOperand.getValue());

				return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstUnit);

			case ADD:
				resultValue = firstOperand.getValue() + secondOperand.getValue();
				return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstUnit);
			case SUBTRACT:
				resultValue = firstOperand.getValue() - secondOperand.getValue();
				return new Operand(maxDigitsFormatter(resultValue, maxDigits), firstUnit);
			case MULTIPLY:

				resultValue = firstOperand.getValue() * secondOperand.getValue();

				// basic multiplication operations do NOT return ^2
				if (firstUnit == "") {
					return new Operand(maxDigitsFormatter(resultValue, maxDigits), secondOperand.getUnit());
				}

				resultValue = firstOperand.getValue() * secondOperand.getValue();

				return new Operand(maxDigitsFormatter(resultValue, maxDigits),
						"<html>" + secondOperand.getUnit() + "<sup>" + 2 + "</sup><html>");

			case DIVIDE:
				if (secondOperand.getValue() == 0) {
					System.out.println("Error: Division by zero");
					return null;
				}
				resultValue = firstOperand.getValue() / secondOperand.getValue();
				returnedOperand = new Operand(maxDigitsFormatter(resultValue, maxDigits), "");
			}

		}

		return returnedOperand;
	}
}
