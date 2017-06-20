package nhb.serializable.basic;

import java.util.Arrays;

import nhb.serializable.PuDataType;
import nhb.serializable.PuValue;
import nhb.serializable.exceptions.UnexpectedDataException;
import nhb.serializable.utils.PrimitiveTypeUtils;

public class BasicPuValue implements PuValue {

	private PuDataType type = PuDataType.NULL;
	private Object data = null;

	public BasicPuValue() {
		// do nothing
	}

	public BasicPuValue(Object value) {
		this.set(value);
	}

	@Override
	public PuDataType getType() {
		return this.type;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get() {
		return (T) this.data;
	}

	@Override
	public PuValue set(Object value) {
		if (value == null) {
			this.type = PuDataType.NULL;
			this.data = null;
		} else if (!(value instanceof byte[]) && !PrimitiveTypeUtils.isPrimitiveOrWrapperType(value.getClass())) {
			throw new UnexpectedDataException("Expected for primitive type, got " + value.getClass());
		} else {
			this.type = PuDataType.fromClass(value.getClass());
			this.data = value;
		}
		return this;
	}

	@Override
	public String toString() {
		if (this.getType() == PuDataType.NULL) {
			return "NULL";
		} else if (this.getType() == PuDataType.BIN) {
			return "(" + PuDataType.BIN.name().toLowerCase() + ") " + Arrays.toString(this.asBinary());
		} else {
			return "(" + this.getType().name().toLowerCase() + ") " + this.get().toString();
		}
	}
}
