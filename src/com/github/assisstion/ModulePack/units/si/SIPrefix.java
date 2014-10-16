package com.github.assisstion.ModulePack.units.si;

import com.github.assisstion.ModulePack.BigFraction;
import com.github.assisstion.ModulePack.units.Prefix;

public final class SIPrefix{

	private SIPrefix(){
		//Do nothing
	}

	public static final Prefix KILO =
			Prefix.make(new BigFraction(1000, 1));
}
