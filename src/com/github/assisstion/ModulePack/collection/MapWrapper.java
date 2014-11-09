package com.github.assisstion.ModulePack.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Wrapper
public class MapWrapper<T, S> extends AbstractWrapper<Map<T, S>>
implements Map<T, S>{
	protected Map<T, S> wrapped;

	public MapWrapper(Map<T, S> map){
		wrapped = map;
	}

	public static <T, S> MapWrapper<T, S> wrap(Map<T, S> map){
		return new MapWrapper<T, S>(map);
	}

	@Override
	public int size(){
		return get().size();
	}

	@Override
	public boolean isEmpty(){
		return get().isEmpty();
	}

	@Override
	public boolean containsKey(Object key){
		return get().containsKey(key);
	}

	@Override
	public boolean containsValue(Object value){
		return get().containsValue(value);
	}

	@Override
	public S get(Object key){
		return get().get(key);
	}

	@Override
	public S put(T key, S value){
		return get().put(key, value);
	}

	@Override
	public S remove(Object key){
		return get().remove(key);
	}

	@Override
	public void putAll(Map<? extends T, ? extends S> m){
		get().putAll(m);
	}

	@Override
	public void clear(){
		get().clear();
	}

	@Override
	public Set<T> keySet(){
		return get().keySet();
	}

	@Override
	public Collection<S> values(){
		return get().values();
	}

	@Override
	public Set<Map.Entry<T, S>> entrySet(){
		return get().entrySet();
	}

	@Override
	public Map<T, S> get(){
		return wrapped;
	}
}
