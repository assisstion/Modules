package com.github.assisstion.ModulePack.units;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.github.assisstion.ModulePack.annotation.LimitedImmutable;

@LimitedImmutable("baseUnits")
public abstract class BaseUnitMap implements BaseUnits{

	protected Map<Unit, Prefix> baseUnits;

	public BaseUnitMap(){
		baseUnits = new HashMap<Unit, Prefix>();
	}

	public BaseUnitMap(HashMap<Unit, Prefix> map){
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
}
