package com.github.assisstion.ModulePack.node;

public interface TypedNode<T extends Node, S> extends Node, NodeNext<T>, NodeObject<S>, MutableNode<T, S>{
	T next();
	S get();
}
