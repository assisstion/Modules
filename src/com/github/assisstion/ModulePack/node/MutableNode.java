package com.github.assisstion.ModulePack.node;

public interface MutableNode<T extends Node, S>{
	void setNext(T next);
	void set(S object);
}
