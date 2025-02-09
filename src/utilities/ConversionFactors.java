package utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Utility class used to initialize and store conversion factors used in
 * calculations.
 * 
 * @version 2.0
 * @author Zachary Williams
 */
public class ConversionFactors {

	public static final Map<String, Map<String, Double>> conversionFactors;

	/**
	 * Initializes conversion factors.
	 */
	static {
		// initialize conversion factors for different unit types
		conversionFactors = new HashMap<>();
		initializeConversionFactors();
	}

	/**
	 * Initializes conversion factors.
	 */
	public static void initializeConversionFactors() {

		// length conversion factors
		addConversionFactor(Units.INCH.getValue(), Units.FEET.getValue(), 1.0 / 12.0);
		addConversionFactor(Units.INCH.getValue(), Units.YARD.getValue(), 1.0 / 36);
		addConversionFactor(Units.INCH.getValue(), Units.MILE.getValue(), 1.0 / 63360);
		addConversionFactor(Units.INCH.getValue(), Units.MILIMETER.getValue(), 25.4);
		addConversionFactor(Units.INCH.getValue(), Units.CENTIMETER.getValue(), 2.54);
		addConversionFactor(Units.INCH.getValue(), Units.METER.getValue(), 0.0254);
		addConversionFactor(Units.INCH.getValue(), Units.KILOMETER.getValue(), 0.0000254);

		addConversionFactor(Units.FEET.getValue(), Units.YARD.getValue(), 1.0 / 3);
		addConversionFactor(Units.FEET.getValue(), Units.MILE.getValue(), 1.0 / 5280);
		addConversionFactor(Units.FEET.getValue(), Units.MILIMETER.getValue(), 304.8);
		addConversionFactor(Units.FEET.getValue(), Units.CENTIMETER.getValue(), 30.48);
		addConversionFactor(Units.FEET.getValue(), Units.METER.getValue(), 0.3048);
		addConversionFactor(Units.FEET.getValue(), Units.KILOMETER.getValue(), 0.0003048);

		addConversionFactor(Units.YARD.getValue(), Units.MILE.getValue(), 1.0 / 1760);
		addConversionFactor(Units.YARD.getValue(), Units.MILIMETER.getValue(), 914.4);
		addConversionFactor(Units.YARD.getValue(), Units.CENTIMETER.getValue(), 91.44);
		addConversionFactor(Units.YARD.getValue(), Units.METER.getValue(), 0.9144);
		addConversionFactor(Units.YARD.getValue(), Units.KILOMETER.getValue(), 0.0009144);

		addConversionFactor(Units.MILE.getValue(), Units.MILIMETER.getValue(), 1609344.0);
		addConversionFactor(Units.MILE.getValue(), Units.CENTIMETER.getValue(), 160934.4);
		addConversionFactor(Units.MILE.getValue(), Units.METER.getValue(), 1609.344);
		addConversionFactor(Units.MILE.getValue(), Units.KILOMETER.getValue(), 1.609344);

		addConversionFactor(Units.MILIMETER.getValue(), Units.CENTIMETER.getValue(), 0.1);
		addConversionFactor(Units.MILIMETER.getValue(), Units.METER.getValue(), 0.001);
		addConversionFactor(Units.MILIMETER.getValue(), Units.KILOMETER.getValue(), 1.0E-6);

		addConversionFactor(Units.CENTIMETER.getValue(), Units.METER.getValue(), 0.01);
		addConversionFactor(Units.CENTIMETER.getValue(), Units.KILOMETER.getValue(), 1.0E-5);

		// time conversion factors
		addConversionFactor(Units.SECOND.getValue(), Units.MINUTE.getValue(), 1 / 60.0);
		addConversionFactor(Units.SECOND.getValue(), Units.HOUR.getValue(), 1 / 3600.0);
		addConversionFactor(Units.SECOND.getValue(), Units.DAY.getValue(), 1 / 86400.0);
		addConversionFactor(Units.SECOND.getValue(), Units.MONTH.getValue(), 1 / (60.0 * 60.0 * 24.0 * 30.0));
		addConversionFactor(Units.SECOND.getValue(), Units.YEAR.getValue(), 1 / (365.0 * 24.0 * 60.0 * 60.0));

		addConversionFactor(Units.MINUTE.getValue(), Units.HOUR.getValue(), 1.0 / 60);
		addConversionFactor(Units.MINUTE.getValue(), Units.DAY.getValue(), 1.0 / (60 * 24));
		addConversionFactor(Units.MINUTE.getValue(), Units.MONTH.getValue(), 1.0 / (60 * 24 * 30));
		addConversionFactor(Units.MINUTE.getValue(), Units.YEAR.getValue(), 1.0 / (60 * 24 * 365));

		addConversionFactor(Units.HOUR.getValue(), Units.MINUTE.getValue(), 60);
		addConversionFactor(Units.HOUR.getValue(), Units.DAY.getValue(), 0.04166666666);
		addConversionFactor(Units.HOUR.getValue(), Units.MONTH.getValue(), 1 / (24 * 30));

		addConversionFactor(Units.DAY.getValue(), Units.MONTH.getValue(), 1.0 / 30);
		addConversionFactor(Units.DAY.getValue(), Units.YEAR.getValue(), 1.0 / 365);

		addConversionFactor(Units.MONTH.getValue(), Units.MINUTE.getValue(), 60 * 24 * 30);

		// Power conversion factors: kilowatt + watt
		addConversionFactor(Units.WATT.getValue(), Units.KILOWATT.getValue(), 0.001);

		// Volumes: pt, qt, gal, cc, l;
		addConversionFactor(Units.CUBIC_CENTIMETER.getValue(), Units.PINT.getValue(), 0.00211338);
		addConversionFactor(Units.CUBIC_CENTIMETER.getValue(), Units.QUART.getValue(), 0.00105669);
		addConversionFactor(Units.CUBIC_CENTIMETER.getValue(), Units.LITER.getValue(), 0.001);
		addConversionFactor(Units.CUBIC_CENTIMETER.getValue(), Units.GALLON.getValue(), 0.000264172);

		addConversionFactor(Units.PINT.getValue(), Units.QUART.getValue(), 0.5);
		addConversionFactor(Units.PINT.getValue(), Units.LITER.getValue(), 0.473176);
		addConversionFactor(Units.PINT.getValue(), Units.GALLON.getValue(), 0.125);

		addConversionFactor(Units.QUART.getValue(), Units.GALLON.getValue(), 0.25);
		addConversionFactor(Units.QUART.getValue(), Units.LITER.getValue(), 0.946353);

		addConversionFactor(Units.LITER.getValue(), Units.GALLON.getValue(), 0.264172);

		// Weight conversion factors: oz, lb, ton, g, kg
		addConversionFactor(Units.KILOGRAM.getValue(), Units.GRAM.getValue(), 1000);
		addConversionFactor(Units.KILOGRAM.getValue(), Units.OUNCE.getValue(), 35.27396);
		addConversionFactor(Units.KILOGRAM.getValue(), Units.POUND.getValue(), 2.20462);
		addConversionFactor(Units.KILOGRAM.getValue(), Units.TON.getValue(), 0.001);

		addConversionFactor(Units.GRAM.getValue(), Units.OUNCE.getValue(), 0.03527396);
		addConversionFactor(Units.GRAM.getValue(), Units.POUND.getValue(), 0.00220462);
		addConversionFactor(Units.GRAM.getValue(), Units.TON.getValue(), 0.000001);

		addConversionFactor(Units.OUNCE.getValue(), Units.POUND.getValue(), 0.0625);
		addConversionFactor(Units.OUNCE.getValue(), Units.TON.getValue(), 0.00003125);

		addConversionFactor(Units.POUND.getValue(), Units.TON.getValue(), 0.0005);

		// Currency
		addConversionFactor(Units.CENT.getValue(), Units.DOLLAR.getValue(), 0.01);
	}

	/**
	 * Get the the Strings corresponding enum.
	 * 
	 * @param unitString unit to be converted to enum
	 * @return the Units enum corresponding to the unit string
	 */
	public static Units getUnitEnum(String unitString) {
		Units returnedUnit = null;
		for (Units unit : Units.values()) {
			if (unit.getValue().equalsIgnoreCase(unitString)) {
				returnedUnit = unit;
			}
		}
		return returnedUnit;
	}

	/**
	 * Adds a conversion factor between two units to the conversionFactors map.
	 *
	 * @param fromUnit   The source unit
	 * @param toUnit     The target unit
	 * @param conversion The conversion factor from the source unit to the target
	 *                   unit
	 */
	private static void addConversionFactor(String fromUnit, String toUnit, double conversion) {
		// check if the conversionFactors map contains the source unit
		if (!conversionFactors.containsKey(fromUnit)) {
			// create the nested map if it doesn't exist
			conversionFactors.put(fromUnit, new HashMap<>());
		}
		// add the conversion factor to the nested map for the source unit
		conversionFactors.get(fromUnit).put(toUnit, conversion);

		// add inverse conversion too
		if (!conversionFactors.containsKey(toUnit)) {
			// create the nested map if it doesn't exist
			conversionFactors.put(toUnit, new HashMap<>());
		}
		// add the inverse conversion factor to the nested map for the target unit
		conversionFactors.get(toUnit).put(fromUnit, 1 / conversion);
	}

}
