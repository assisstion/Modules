package com.github.assisstion.ModulePack.node;

public class NodePart<T extends TypedNode<?, ?>, S> implements TypedNode<T, S>{

	private T next;
	private S object;
	private boolean hasNext;
	private boolean hasObject;
	
	public NodePart(){
		
	}
	
	public NodePart(T next, S object){
		hasNext = true;
		hasObject = true;
		this.next = next;
		this.object = object;
	}
	
	public NodePart(T next){
		hasNext = true;
		this.next = next;
	}
	
	public NodePart(S object){
		hasObject = true;
		this.object = object;
	}
	
	@Override
	public T next(){
		if(hasNext){
			return next;
		}
		else{
			throw new NodeException("No next node object");
		}
	}

	@Override
	public S get(){
		if(hasObject){
			return object;
		}
		else{
			throw new NodeException("No object in node");
		}
	}

	@Override
	public boolean hasNext(){
		return hasNext;
	}

	@Override
	public boolean hasObject(){
		return hasObject;
	}

	@Override
	public void setNext(T next){
		hasNext = true;
		this.next = next;
	}

	@Override
	public void set(S object){
		hasObject = true;
		this.object = object;
	}

}
