package com.github.assisstion.ModulePack.node;

public class NodeEnd<T> implements TypedNode<Node, T>{

	private T object;
	private boolean hasObject;
	
	public NodeEnd(){
		hasObject = false;
	}
	
	public NodeEnd(T object){
		hasObject = true;
		this.object = object;
	}
	
	@Override
	public Node next(){
		throw new NodeException("No next node object");
	}

	@Override
	public T get(){
		if(hasObject){
			return object;
		}
		else{
			throw new NodeException("No object in node");
		}
	}

	@Override
	public boolean hasNext(){
		return false;
	}

	@Override
	public boolean hasObject(){
		return hasObject;
	}

	@Override
	public void setNext(Node next){
		throw new NodeException("Unable to set next");
	}

	@Override
	public void set(T object){
		hasObject = true;
		this.object = object;
	}

}
