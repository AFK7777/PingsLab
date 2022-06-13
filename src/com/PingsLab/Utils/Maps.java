package com.PingsLab.Utils;

import java.util.HashMap;
import java.util.Map;

public class Maps {
	@SuppressWarnings("unchecked")
	public static <K,V> Map<K,V> asMap(Object ... objs) {
		HashMap<K, V> map = new HashMap<K,V>();
		if(objs.length % 2 == 1) {
			throw new RuntimeException("Bro, ti sei scordato qualcosa!");
		}
		for(int i = 0; i < objs.length; i+=2) {
			map.put((K) objs[i], (V) objs[i+1]);
		}
		return map;
	}
}
