package com.github.assisstion.ModulePack.collection;

import java.util.Collection;
import java.util.Map;

public interface MultiSet<T> extends Collection<T>{
	boolean addAll(Map<T, Integer> map);
	boolean removeAll(Map<T, Integer> map);
	boolean add(T element, int amount);
	boolean remove(Object o, int amount);
	Map<T, Integer> mapView();
	int count(Object o);
}
