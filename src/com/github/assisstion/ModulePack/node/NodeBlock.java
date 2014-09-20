package com.github.assisstion.ModulePack.node;

public class NodeBlock implements TypedNode<Node, Object>{

	private boolean hasNext;
	private Node next;
	
	public NodeBlock(){
		hasNext = false;
	}
	
	public NodeBlock(Node next){
		hasNext = true;
		this.next = next;
	}
	
	public void setNext(Node next){
		hasNext = true;
		this.next = next;
	}
	
	public boolean hasNext(){
		return hasNext;
	}
	
	@Override
	public Node next(){
		if(hasNext){
			return next;
		}
		else{
			throw new NodeException("No next node object");
		}
	}

	@Override
	public Object get(){
		throw new NodeException("No object in node");
	}

	@Override
	public boolean hasObject(){
		return false;
	}

	@Override
	public void set(Object object){
		throw new NodeException("Unable to set object");
	}

}
