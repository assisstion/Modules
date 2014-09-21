package com.github.assisstion.ModuleMack.logging;

import com.github.assisstion.ModulePack.Pair;
import com.github.assisstion.ModulePack.annotation.Dependency;

@Dependency(Pair.class)
public class IntegerPair extends Pair<Integer, Integer>{

	private static final long serialVersionUID = 518332759298803660L;

	protected IntegerPair(){

	}

	public IntegerPair(Integer valueOne, Integer valueTwo){
		super(valueOne, valueTwo);
	}


}
