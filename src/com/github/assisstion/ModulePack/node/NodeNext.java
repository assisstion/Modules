package com.github.assisstion.ModulePack.node;

public interface NodeNext<T extends Node>{
	boolean hasNext();
	T next();
}
