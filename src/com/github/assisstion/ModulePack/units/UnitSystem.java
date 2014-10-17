package com.github.assisstion.ModulePack.units;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.github.assisstion.ModulePack.annotation.LimitedImmutable;

@LimitedImmutable("baseUnits")
public abstract class UnitSystem implements BaseUnits{

	protected Map<Unit, Prefix> baseUnits;

	public UnitSystem(){
		baseUnits = new HashMap<Unit, Prefix>();
	}

	public UnitSystem(HashMap<Unit, Prefix> map){
		baseUnits = new HashMap<Unit, Prefix>(map);
	}

	@Override
	public Map<Unit, Prefix> getUnits(){
		return Collections.unmodifiableMap(getMutableBaseUnits());
	}

	protected Map<Unit, Prefix> getMutableBaseUnits(){
		return baseUnits;
	}

	@Override
	public abstract String getName();

	@Override
	public String toString(){
		return getName();
	}
}
