package com.github.assisstion.ModulePack.collection.wrapper;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListWrapper<T> extends AbstractWrapper<List<T>> implements List<T>{

	protected List<T> wrapped;

	public ListWrapper(List<T> list){
		wrapped = list;
	}

	public static <T> ListWrapper<T> wrap(List<T> list){
		return new ListWrapper<T>(list);
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
	public boolean addAll(int index, Collection<? extends T> c){
		return get().addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c){
		return get().removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c){
		return get().retainAll(c);
	}

	@Override
	public void clear(){
		get().clear();
	}

	@Override
	public T get(int index){
		return get().get(index);
	}

	@Override
	public T set(int index, T element){
		return get().set(index, element);
	}

	@Override
	public void add(int index, T element){
		get().add(index, element);
	}

	@Override
	public T remove(int index){
		return get().remove(index);
	}

	@Override
	public int indexOf(Object o){
		return get().indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o){
		return get().lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator(){
		return get().listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index){
		return get().listIterator(index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex){
		return get().subList(fromIndex, toIndex);
	}

	@Override
	public List<T> get(){
		return wrapped;
	}

}
