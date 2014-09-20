package com.github.assisstion.ModulePack.node;

public class NodeException extends RuntimeException{

	private static final long serialVersionUID = 5363864497133814065L;

	public NodeException(){
		
	}
	
	public NodeException(String message){
		super(message);
	}
	
	public NodeException(Throwable cause){
		super(cause);
	}
	
	public NodeException(String message, Throwable cause){
		super(message, cause);
	}
}
