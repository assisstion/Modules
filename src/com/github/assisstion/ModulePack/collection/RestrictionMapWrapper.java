package com.github.assisstion.ModulePack.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;

public class RestrictionMapWrapper<T, S> extends MapWrapper<T, S> implements
BiChecker<T, S>{

	protected BiPredicate<T, S> checker = getDefaultChecker();

	@Override
	public BiPredicate<T, S> getChecker(){
		return checker;
	}

	public RestrictionMapWrapper(Map<T, S> map){
		super(map);
		purge();
	}

	public RestrictionMapWrapper(Map<T, S> map, BiPredicate<T, S> checker){
		this(map);
		this.checker = checker;
	}

	//True if map is good
	//False if purge happened
	public boolean purge(){
		Set<T> keysToPurge = new HashSet<T>();
		for(Map.Entry<T, S> entry : get().entrySet()){
			T key = entry.getKey();
			if(!check(key, entry.getValue())){
				keysToPurge.add(key);
			}
		}
		for(T t : keysToPurge){
			get().remove(t);
		}
		return keysToPurge.size() == 0;
	}

	@Override
	public S put(T key, S value){
		if(check(key, value)){
			return get().put(key, value);
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends T, ? extends S> newMap){
		Map<T, S> newNewMap = new HashMap<T, S>();
		newMap.forEach((t, s) -> {
			if(check(t, s)){
				newNewMap.put(t, s);
			}
		});
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
				(t) -> check(t, get().get(t)));
	}

	@Override
	public Collection<S> values(){
		return Collections.unmodifiableCollection(super.values());
	}

	protected static <T, S> BiPredicate<T, S> getDefaultChecker(){
		return (t, s) -> true;
	}
}
