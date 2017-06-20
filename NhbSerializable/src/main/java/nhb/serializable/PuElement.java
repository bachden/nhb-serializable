package nhb.serializable;

import java.util.Map;

public interface PuElement {

	static final PuElement NULL = PuValue.from(null);

	@SuppressWarnings("unchecked")
	public static PuElement from(Object value) {
		if (value instanceof PuElement) {
			return (PuElement) value;
		}
		PuDataType type = value == null ? PuDataType.NULL : PuDataType.fromClass(value.getClass());
		switch (type) {
		case NULL:
			return PuElement.NULL;
		case ARRAY:
			return PuArray.from(value);
		case MAP:
			return PuMap.from((Map<String, ?>) value);
		default:
			return PuValue.from(value);
		}
	}

	PuDataType getType();
}
