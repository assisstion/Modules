package com.github.assisstion.ModulePack.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@Wrapper
public class SetWrapper<T> extends AbstractWrapper<Set<T>>
implements Set<T>{

	protected Set<T> wrapped;

	public SetWrapper(Set<T> set){
		wrapped = set;
	}

	public static <T> SetWrapper<T> wrap(Set<T> set){
		return new SetWrapper<T>(set);
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
	public boolean contains(Object o){
		return get().contains(o);
	}

	@Override
	public Iterator<T> iterator(){
		return get().iterator();
	}

	@Override
	public Object[] toArray(){
		return get().toArray();
	}

	@Override
	public <S> S[] toArray(S[] a){
		return get().toArray(a);
	}

	@Override
	public boolean add(T e){
		return get().add(e);
	}

	@Override
	public boolean remove(Object o){
		return get().remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c){
		return get().containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c){
		return get().addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c){
		return get().retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c){
		return get().removeAll(c);
	}

	@Override
	public void clear(){
		get().clear();
	}

	@Override
	public Set<T> get(){
		return wrapped;
	}
}
