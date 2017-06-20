package nhb.serializable.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nhb.serializable.PuArray;
import nhb.serializable.PuElement;

public class BasicPuArray implements PuArray {

	private final List<PuElement> source = new ArrayList<>();

	@Override
	public Iterator<PuElement> iterator() {
		return this.source.iterator();
	}

	@Override
	public void clear() {
		this.source.clear();
	}

	@Override
	public int size() {
		return this.source.size();
	}

	@Override
	public PuElement getElement(int index) {
		return this.source.get(index);
	}

	@Override
	public PuElement removeElement(int index) {
		return this.source.remove(index);
	}

	@Override
	public void addElement(int index, PuElement element) {
		this.source.add(index, element);
	}

}
