package nhb.serializable.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import nhb.serializable.PuDataType;
import nhb.serializable.PuElement;
import nhb.serializable.PuMap;

public class BasicPuMap implements PuMap {

	private final Map<String, PuElement> source = new HashMap<>();

	@Override
	public void clear() {
		this.source.clear();
	}

	@Override
	public Iterator<Entry<String, PuElement>> iterator() {
		return this.source.entrySet().iterator();
	}

	@Override
	public PuDataType typeOf(String key) {
		return this.getElement(key).getType();
	}

	@Override
	public PuElement getElement(String key) {
		return this.source.get(key);
	}

	@Override
	public void putElement(String key, PuElement element) {
		this.source.put(key, element);
	}

	@Override
	public boolean constantsKey(String key) {
		return this.source.containsKey(key);
	}

	@Override
	public int size() {
		return this.source.size();
	}

	@Override
	public String toString() {
		return toString(0);
	}
}
