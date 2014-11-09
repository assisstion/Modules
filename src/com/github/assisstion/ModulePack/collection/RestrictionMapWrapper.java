package com.github.assisstion.ModulePack.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RestrictionMapWrapper<T, S> extends MapWrapper<T, S> implements
BiChecker<T, S>{

	protected BiFunction<T, S, Boolean> checker = getDefaultChecker();

	@Override
	public BiFunction<T, S, Boolean> getChecker(){
		return checker;
	}

	public RestrictionMapWrapper(Map<T, S> map){
		super(map);
		purge();
	}

	public RestrictionMapWrapper(Map<T, S> map, BiFunction<T, S, Boolean> checker){
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
		newMap.forEach(new MapCheckerWrapper(newNewMap));
		super.putAll(newNewMap);
	}

	@Override
	public Set<Map.Entry<T, S>> entrySet(){
		return new RestrictionSetWrapper<Map.Entry<T, S>>(
				super.entrySet(), this.new EntrySetChecker());
	}

	@Override
	public Set<T> keySet(){
		return new RestrictionSetWrapper<T>(super.keySet(), this.new KeySetChecker());
	}

	@Override
	public Collection<S> values(){
		return Collections.unmodifiableCollection(super.values());
	}

	protected static <T, S> BiFunction<T, S, Boolean> getDefaultChecker(){
		return (t, s) -> true;
	}

	protected class MapCheckerWrapper implements BiConsumer<T, S>{

		protected Map<T, S> putter;

		public MapCheckerWrapper(Map<T, S> newNewMap){
			putter = newNewMap;
		}

		@Override
		public void accept(T t, S s){
			if(check(t, s)){
				putter.put(t, s);
			}
		}

	}

	protected class EntrySetChecker implements Function<Map.Entry<T, S>, Boolean>{

		@Override
		public Boolean apply(Map.Entry<T, S> entry){
			return check(entry.getKey(), entry.getValue());
		}

	}

	protected class KeySetChecker implements Function<T, Boolean>{

		@Override
		public Boolean apply(T t){
			return check(t, get().get(t));
		}

	}
}
