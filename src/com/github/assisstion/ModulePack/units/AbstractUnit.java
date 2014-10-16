package com.github.assisstion.ModulePack.units;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.github.assisstion.ModulePack.BigFraction;
import com.github.assisstion.ModulePack.annotation.LimitedImmutable;

@LimitedImmutable({"base", "mapping"})
public abstract class AbstractUnit implements Unit{
	protected BaseUnits base;
	protected Map<Unit, Integer> mapping;
	protected String name;
	protected String measurement;

	protected AbstractUnit(){

	}

	public AbstractUnit(String name, String measurement){
		this.name = name;
		this.measurement = measurement;
		mapping = new HashMap<Unit, Integer>();
	}

	public AbstractUnit(String name, String measurement, BaseUnits base){
		this(name, measurement);
		this.base = base;
	}

	public AbstractUnit(String name, String measurement, BaseUnits base, Map<Unit, Integer> mapping){
		this(name, measurement, base);
		this.mapping.putAll(mapping);
	}

	@Override
	public BaseUnits getBaseUnits(){
		return base;
	}

	@Override
	public Map<Unit, Integer> getMapping(){
		return Collections.unmodifiableMap(mapping);
	}

	@Override
	public String getName(){
		return name;
	}

	@Override
	public String getMeasurement(){
		return measurement;
	}

	@Override
	public abstract BigFraction toBaseMapping(BigFraction in);
}
