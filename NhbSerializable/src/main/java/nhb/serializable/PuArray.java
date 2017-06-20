package nhb.serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nhb.serializable.basic.BasicPuArray;
import nhb.serializable.exceptions.UnexpectedDataException;
import nhb.serializable.utils.ArrayUtils;
import nhb.serializable.utils.ArrayUtils.ForeachCallback;

public interface PuArray extends PuElement, PuIterable<PuElement>, PuBeautyString {

	static PuArray newInstance() {
		return new BasicPuArray();
	}

	static PuArray newInstance(Object... values) {
		PuArray arr = newInstance();
		if (values != null) {
			for (Object value : values) {
				arr.add(value);
			}
		}
		return arr;
	}

	static PuArray from(Object value) {
		if (value == null || !ArrayUtils.isArrayOrCollection(value.getClass())) {
			throw new UnexpectedDataException("Expected for array or collection");
		}
		PuArray arr = new BasicPuArray();
		ArrayUtils.foreach(value, new ForeachCallback<Object>() {

			@Override
			public void apply(Object element) {
				arr.addElement(PuElement.from(element));
			}
		});
		return arr;
	}

	@Override
	default PuDataType getType() {
		return PuDataType.ARRAY;
	}

	int size();

	void clear();

	PuElement getElement(int index);

	PuElement removeElement(int index);

	void addElement(int index, PuElement element);

	default void addElement(PuElement element) {
		this.addElement(this.size(), element);
	}

	default void addAll(Collection<?> collection) {
		for (Object element : collection) {
			if (element instanceof PuElement) {
				this.addElement((PuElement) element);
			} else if (element instanceof Map<?, ?>) {

			}
		}
	}

	default void add(int index, Object value) {
		this.addElement(index, PuElement.from(value));
	}

	default void add(Object value) {
		this.add(this.size(), value);
	}

	@SuppressWarnings("unchecked")
	default <T> T get(int index) {
		PuElement element = this.getElement(index);
		if (element.getType() == PuDataType.MAP) {
			return (T) ((PuMap) element).asMap();
		} else if (element.getType() == PuDataType.ARRAY) {
			return (T) ((PuArray) element).asList();
		} else if (element.getType() == PuDataType.NULL) {
			return null;
		}
		return ((PuValue) element).get();
	}

	@SuppressWarnings("unchecked")
	default <T> T remove(int index) {
		PuElement element = this.removeElement(index);
		if (element.getType() == PuDataType.MAP) {
			return (T) ((PuMap) element).asMap();
		} else if (element.getType() == PuDataType.ARRAY) {
			return (T) ((PuArray) element).asList();
		} else if (element.getType() == PuDataType.NULL) {
			return null;
		}
		return ((PuValue) element).get();
	}

	default List<?> asList() {
		List<Object> list = new ArrayList<>();

		for (Iterator<PuElement> it = this.iterator(); it.hasNext();) {
			PuElement element = it.next();
			if (element.getType() != PuDataType.NULL) {
				switch (element.getType()) {
				case MAP:
					list.add(((PuMap) element).asMap());
					break;
				case ARRAY:
					list.add(((PuArray) element).asList());
					break;
				default:
					list.add(((PuValue) element).get());
					break;
				}
			}
		}
		return list;
	}

	@Override
	default String toString(int numTabs) {
		StringBuilder sb = new StringBuilder();
		sb.append("(" + PuDataType.ARRAY.name().toLowerCase() + ") [");
		for (Iterator<PuElement> it = this.iterator(); it.hasNext();) {
			PuElement entry = it.next();
			if (entry instanceof PuArray || entry instanceof PuMap) {
				sb.append(((PuBeautyString) entry).toString(numTabs + 1));
			} else {
				sb.append(entry.toString());
			}
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
