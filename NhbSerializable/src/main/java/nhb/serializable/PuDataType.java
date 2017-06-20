package nhb.serializable;

import java.math.BigDecimal;
import java.util.Map;

import nhb.serializable.utils.ArrayUtils;

public enum PuDataType {

	NULL, BOOL, BYTE, SHORT, INT, LONG, FLOAT, DOUBLE, CHAR, STRING, ARRAY, MAP, BIN;

	public static PuDataType fromClass(Class<?> clazz) {
		if (clazz == null) {
			throw new NullPointerException("Cannot perform check for null class");
		}

		if (clazz == byte[].class) {
			return PuDataType.BIN;
		} else if (clazz == Boolean.class || clazz == Boolean.TYPE) {
			return BOOL;
		} else if (clazz == Byte.class || clazz == Byte.TYPE) {
			return BYTE;
		} else if (clazz == Short.class || clazz == Short.TYPE) {
			return SHORT;
		} else if (clazz == Integer.class || clazz == Integer.TYPE) {
			return PuDataType.INT;
		} else if (clazz == Long.class || clazz == Long.TYPE) {
			return PuDataType.LONG;
		} else if (clazz == Float.class || clazz == Float.TYPE) {
			return PuDataType.FLOAT;
		} else if (clazz == Double.class || clazz == Double.TYPE || clazz == BigDecimal.class) {
			return PuDataType.DOUBLE;
		} else if (clazz == Character.class || clazz == Character.TYPE) {
			return PuDataType.CHAR;
		} else if (clazz == String.class) {
			return PuDataType.STRING;
		} else if (PuArray.class.isAssignableFrom(clazz) || ArrayUtils.isArrayOrCollection(clazz)) {
			return PuDataType.ARRAY;
		} else if (PuMap.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz)) {
			return PuDataType.MAP;
		}

		return null;
	}

}
