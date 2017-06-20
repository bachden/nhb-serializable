package nhb.serializable;

import nhb.serializable.basic.BasicPuValue;
import nhb.serializable.utils.PrimitiveTypeUtils;

public interface PuValue extends PuElement {

	static PuValue newInstance() {
		return new BasicPuValue();
	}

	static PuValue from(Object value) {
		return new BasicPuValue(value);
	}

	<T> T get();

	PuValue set(Object value);

	default <T> T get(T defaultValue) {
		if (this.getType() == PuDataType.NULL) {
			return null;
		}
		T result = this.get();
		if (result != null) {
			return result;
		}
		return defaultValue;
	}

	default boolean isNull() {
		return this.getType() == PuDataType.NULL;
	}

	default boolean asBoolean() {
		return PrimitiveTypeUtils.getBooleanValueFrom(get());
	}

	default boolean asBoolean(byte defaultValue) {
		Object value = this.get(defaultValue);
		if (value == null) {
			throw new NullPointerException();
		}
		return PrimitiveTypeUtils.getBooleanValueFrom(value);
	}

	default byte asByte() {
		return PrimitiveTypeUtils.getByteValueFrom(get());
	}

	default byte asByte(byte defaultValue) {
		Object value = this.get(defaultValue);
		if (value == null) {
			throw new NullPointerException();
		}
		return PrimitiveTypeUtils.getByteValueFrom(value);
	}

	default short asShort() {
		return PrimitiveTypeUtils.getShortValueFrom(get());
	}

	default short asShort(short defaultValue) {
		Object value = this.get(defaultValue);
		if (value == null) {
			throw new NullPointerException();
		}
		return PrimitiveTypeUtils.getShortValueFrom(value);
	}

	default int asInteger() {
		return PrimitiveTypeUtils.getIntegerValueFrom(this.get());
	}

	default int asInteger(int defaultValue) {
		Object value = this.get(defaultValue);
		if (value == null) {
			throw new NullPointerException();
		}
		return PrimitiveTypeUtils.getIntegerValueFrom(value);
	}

	default long asLong() {
		return PrimitiveTypeUtils.getLongValueFrom(this.get());
	}

	default long asLong(long defaultValue) {
		Object value = this.get(defaultValue);
		if (value == null) {
			throw new NullPointerException();
		}
		return PrimitiveTypeUtils.getLongValueFrom(value);
	}

	default float asFloat() {
		return PrimitiveTypeUtils.getFloatValueFrom(this.get());
	}

	default float asFloat(float defaultValue) {
		Object value = this.get(defaultValue);
		if (value == null) {
			throw new NullPointerException();
		}
		return PrimitiveTypeUtils.getFloatValueFrom(value);
	}

	default double asDouble() {
		return PrimitiveTypeUtils.getDoubleValueFrom(this.get());
	}

	default double asDouble(double defaultValue) {
		Object value = this.get(defaultValue);
		if (value == null) {
			throw new NullPointerException();
		}
		return PrimitiveTypeUtils.getDoubleValueFrom(value);
	}

	default char asChar() {
		return PrimitiveTypeUtils.getCharValueFrom(this.get());
	}

	default char asChar(char defaultValue) {
		Object value = this.get(defaultValue);
		if (value == null) {
			throw new NullPointerException();
		}
		return PrimitiveTypeUtils.getCharValueFrom(value);
	}

	default String asString() {
		return PrimitiveTypeUtils.getStringValueFrom(this.get());
	}

	default String asString(String defaultValue) {
		Object value = this.get(defaultValue);
		if (value == null) {
			throw new NullPointerException();
		}
		return PrimitiveTypeUtils.getStringValueFrom(value);
	}

	default byte[] asBinary() {
		if (this.getType() == PuDataType.STRING) {
			return ((String) this.get()).getBytes();
		}
		return (byte[]) this.get();
	}

	default byte[] asBinary(byte[] defaultValue) {
		Object value = this.get(defaultValue);
		if (value == null) {
			throw new NullPointerException();
		} else if (this.getType() == PuDataType.STRING) {
			return ((String) this.get()).getBytes();
		}
		return (byte[]) value;
	}
}
