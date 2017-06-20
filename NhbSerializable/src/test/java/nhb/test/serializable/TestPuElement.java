package nhb.test.serializable;

import java.math.BigDecimal;

import nhb.serializable.PuArray;
import nhb.serializable.PuMap;

public class TestPuElement {

	public static void main(String[] args) {
		PuMap map = PuMap.newInstance();

		map.put("name", "Bach Hoang Nguyen")//
				.put("float", 1.2f) //
				.put("boolean", true) //
				.put("big-decimal", new BigDecimal("2314123412344131.21234")) //
				.put("map", PuMap.newInstance("age", 28, "language", "Vietnamese"))//
				.put("array", PuArray.newInstance(1, 2, 3, new byte[] { 4, 5, 6 },
						PuMap.newInstance("bach", "den", "com", new String[] { "Terra", "Puppet" })));

		System.out.println("Map: " + map.toString());
	}
}
