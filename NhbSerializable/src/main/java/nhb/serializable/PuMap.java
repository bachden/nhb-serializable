package nhb.serializable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import nhb.serializable.basic.BasicPuMap;
import nhb.serializable.exceptions.UnexpectedDataException;
import nhb.serializable.utils.StringUtils;

public interface PuMap extends PuElement, PuIterable<Entry<String, PuElement>>, PuBeautyString {

	static PuMap newInstance() {
		return new BasicPuMap();
	}

	static PuMap newInstance(Object... keyValuePair) {
		PuMap map = new BasicPuMap();
		if (keyValuePair != null && (keyValuePair.length % 2 == 0)) {
			for (int i = 0; i < keyValuePair.length; i += 2) {
				map.put(keyValuePair[i].toString(), keyValuePair[i + 1]);
			}
		}
		return map;
	}

	static PuMap from(Map<String, ?> value) {
		return newInstance().addAll(value);
	}

	void clear();

	PuDataType typeOf(String key);

	PuElement valueOf(String key);

	void putElement(String key, PuElement element);

	boolean constantsKey(String key);

	int size();

	@Override
	default PuDataType getType() {
		return PuDataType.MAP;
	}

	default PuMap addAll(Map<String, ?> map) {
		for (Entry<String, ?> entry : map.entrySet()) {
			this.put(entry.getKey(), PuElement.from(entry.getValue()));
		}
		return this;
	}

	default PuMap addAll(PuMap map) {
		for (Entry<String, ?> entry : map) {
			this.put(entry.getKey(), PuElement.from(entry.getValue()));
		}
		return this;
	}

	default PuMap put(String key, Object value) {
		if (value instanceof PuElement) {
			this.putElement(key, (PuElement) value);
		} else {
			this.putElement(key, PuElement.from(value));
		}
		return this;
	}

	default Map<String, ?> asMap() {
		Map<String, Object> map = new HashMap<>();
		Iterator<Entry<String, PuElement>> it = this.iterator();
		while (it.hasNext()) {
			Entry<String, PuElement> ele = it.next();
			if (ele.getValue().getType() != PuDataType.NULL) {
				switch (ele.getValue().getType()) {
				case MAP:
					map.put(ele.getKey(), ((PuMap) ele.getValue()).asMap());
					break;
				case ARRAY:
					map.put(ele.getKey(), ((PuArray) ele.getValue()).asList());
					break;
				default:
					map.put(ele.getKey(), ((PuValue) ele.getValue()).get());
					break;
				}
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	default <T> T get(String key) {
		PuElement value = this.valueOf(key);
		if (value == null) {
			throw new UnexpectedDataException("Data doesn't exist for key '" + key + "'");
		} else if (value.getType() == PuDataType.NULL) {
			return null;
		} else if (value.getType() == PuDataType.MAP) {
			try {
				return (T) ((PuMap) value).asMap();
			} catch (Exception e) {
				throw new UnexpectedDataException(e);
			}
		} else if (value.getType() == PuDataType.ARRAY) {
			try {
				return (T) ((PuArray) value).asList();
			} catch (Exception e) {
				throw new UnexpectedDataException(e);
			}
		} else {
			return ((PuValue) value).get();
		}
	}

	default <T> T get(String key, T defaultValue) {
		if (typeOf(key) == PuDataType.NULL) {
			return null;
		}
		T result = this.get(key);
		if (result != null) {
			return result;
		}
		return defaultValue;
	}

	default boolean asBoolean(String key) {
		PuElement value = this.valueOf(key);
		if (value == null) {
			throw new UnexpectedDataException();
		} else if (value.getType() == PuDataType.NULL) {
			return false;
		} else if (value.getType() == PuDataType.ARRAY || value.getType() == PuDataType.MAP) {
			return true;
		}
		return ((PuValue) value).asBoolean();
	}

	default boolean asBoolean(String key, boolean defaultValue) {
		try {
			return this.asBoolean(key);
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	default byte asByte(String key) {
		PuElement value = this.valueOf(key);
		if (value == null) {
			throw new NullPointerException();
		} else if (value.getType() == PuDataType.ARRAY || value.getType() == PuDataType.MAP) {
			throw new UnexpectedDataException();
		} else if (value.getType() == PuDataType.NULL) {
			return 0;
		}
		return ((PuValue) value).asByte();
	}

	default byte asByte(String key, byte defaultValue) {
		try {
			return this.asByte(key);
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	default short asShort(String key) {
		PuElement value = this.valueOf(key);
		if (value == null) {
			throw new NullPointerException();
		} else if (value.getType() == PuDataType.ARRAY || value.getType() == PuDataType.MAP) {
			throw new UnexpectedDataException();
		} else if (value.getType() == PuDataType.NULL) {
			return 0;
		}
		return ((PuValue) value).asShort();
	}

	default short asShort(String key, short defaultValue) {
		try {
			return this.asShort(key);
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	default int asInteger(String key) {
		PuElement value = this.valueOf(key);
		if (value == null) {
			throw new NullPointerException();
		} else if (value.getType() == PuDataType.ARRAY || value.getType() == PuDataType.MAP) {
			throw new UnexpectedDataException();
		} else if (value.getType() == PuDataType.NULL) {
			return 0;
		}
		return ((PuValue) value).asInteger();
	}

	default int asInteger(String key, int defaultValue) {
		try {
			return this.asInteger(key);
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	default long asLong(String key) {
		PuElement value = this.valueOf(key);
		if (value == null) {
			throw new NullPointerException();
		} else if (value.getType() == PuDataType.ARRAY || value.getType() == PuDataType.MAP) {
			throw new UnexpectedDataException();
		} else if (value.getType() == PuDataType.NULL) {
			return 0;
		}
		return ((PuValue) value).asLong();
	}

	default long asLong(String key, long defaultValue) {
		try {
			return this.asLong(key);
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	default float asFloat(String key) {
		PuElement value = this.valueOf(key);
		if (value == null) {
			throw new NullPointerException();
		} else if (value.getType() == PuDataType.ARRAY || value.getType() == PuDataType.MAP) {
			throw new UnexpectedDataException();
		} else if (value.getType() == PuDataType.NULL) {
			return 0;
		}
		return ((PuValue) value).asFloat();
	}

	default float asFloat(String key, float defaultValue) {
		try {
			return this.asFloat(key);
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	default double asDouble(String key) {
		PuElement value = this.valueOf(key);
		if (value == null) {
			throw new NullPointerException();
		} else if (value.getType() == PuDataType.ARRAY || value.getType() == PuDataType.MAP) {
			throw new UnexpectedDataException();
		} else if (value.getType() == PuDataType.NULL) {
			return 0;
		}
		return ((PuValue) value).asDouble();
	}

	default double asDouble(String key, double defaultValue) {
		try {
			return this.asDouble(key);
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	default char asChar(String key) {
		PuElement value = this.valueOf(key);
		if (value == null) {
			throw new NullPointerException();
		} else if (value.getType() == PuDataType.ARRAY || value.getType() == PuDataType.MAP) {
			throw new UnexpectedDataException();
		} else if (value.getType() == PuDataType.NULL) {
			return 0;
		}
		return ((PuValue) value).asChar();
	}

	default char asChar(String key, char defaultValue) {
		try {
			return this.asChar(key);
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	default String asString(String key) {
		PuElement value = this.valueOf(key);
		if (value == null) {
			throw new NullPointerException();
		} else if (value.getType() == PuDataType.ARRAY || value.getType() == PuDataType.MAP) {
			throw new UnexpectedDataException();
		} else if (value.getType() == PuDataType.NULL) {
			return null;
		}
		return ((PuValue) value).asString();
	}

	default String asString(String key, String defaultValue) {
		try {
			return this.asString(key);
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	default byte[] asBinary(String key) {
		PuElement value = this.valueOf(key);
		if (value == null) {
			throw new NullPointerException();
		} else if (value.getType() == PuDataType.ARRAY || value.getType() == PuDataType.MAP) {
			throw new UnexpectedDataException();
		} else if (value.getType() == PuDataType.NULL) {
			return null;
		}
		return ((PuValue) value).asBinary();
	}

	default byte[] asBinary(String key, byte[] defaultValue) {
		try {
			return this.asBinary(key);
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	@Override
	default String toString(int numTabs) {
		String tabs = StringUtils.genTabs(numTabs);
		StringBuilder sb = new StringBuilder();
		sb.append("(" + PuDataType.MAP.name().toLowerCase() + ") {\n");
		for (Iterator<Entry<String, PuElement>> it = this.iterator(); it.hasNext();) {
			Entry<String, PuElement> entry = it.next();
			sb.append("\t" + tabs) //
					.append(entry.getKey()) //
					.append(" = ");
			if (entry.getValue() instanceof PuArray) {
				sb.append(((PuBeautyString) entry.getValue()).toString(numTabs));
			} else if (entry.getValue() instanceof PuMap) {
				sb.append(((PuBeautyString) entry.getValue()).toString(numTabs + 1));
			} else {
				sb.append(entry.getValue().toString());
			}
			if (it.hasNext()) {
				sb.append(",");
			}
			sb.append("\n");
		}
		sb.append(tabs + "}");
		return sb.toString();
	}
}
