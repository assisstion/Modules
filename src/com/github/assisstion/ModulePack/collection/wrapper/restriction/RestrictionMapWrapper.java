package com.github.assisstion.ModulePack.collection.wrapper.restriction;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import com.github.assisstion.ModulePack.collection.wrapper.MapWrapper;

public class RestrictionMapWrapper<T, S> extends MapWrapper<T, S> implements
BiChecker<T, S>{

	protected BiPredicate<T, S> checker = getDefaultChecker();

	@Override
	public BiPredicate<T, S> getChecker(){
		return checker;
	}

	public RestrictionMapWrapper(Map<T, S> map){
		super(map);
	}

	public RestrictionMapWrapper(Map<T, S> map, BiPredicate<T, S> checker){
		super(map);
		this.checker = checker;
		purge();
	}

	//True if map is good
	//False if purge happened
	public boolean purge(){
		Map<T, S> map = get();
		Set<T> keysToPurge = map.entrySet().stream()
				.filter((entry) -> !check(entry.getKey(), entry.getValue()))
				.map((entry) -> entry.getKey())
				.collect(Collectors.toSet());
		keysToPurge.forEach((t) -> map.remove(t));
		return keysToPurge.size() == 0;
	}

	@Override
	public S put(T key, S value){
		if(check(key, value)){
			return super.put(key, value);
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends T, ? extends S> newMap){
		Map<T, S> newNewMap = newMap.entrySet().stream()
				.filter(entry -> check(entry.getKey(), entry.getValue()))
				.collect(Collectors.toMap(entry -> entry.getKey(),
						entry -> entry.getValue()));
		super.putAll(newNewMap);
	}

	@Override
	public Set<Map.Entry<T, S>> entrySet(){
		return new RestrictionSetWrapper<Map.Entry<T, S>>(
				super.entrySet(), (entry) -> check(entry.getKey(), entry.getValue()));
	}

	@Override
	public Set<T> keySet(){
		return new RestrictionSetWrapper<T>(super.keySet(),
				t -> check(t, get().get(t)));
	}

	@Override
	public Collection<S> values(){
		return Collections.unmodifiableCollection(super.values());
	}

	protected static <T, S> BiPredicate<T, S> getDefaultChecker(){
		return (t, s) -> true;
	}
}
