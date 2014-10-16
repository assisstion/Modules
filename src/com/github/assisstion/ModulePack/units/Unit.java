package com.github.assisstion.ModulePack.units;

import java.util.Map;

import com.github.assisstion.ModulePack.BigFraction;
import com.github.assisstion.ModulePack.annotation.Dependency;

@Dependency(BigFraction.class)
public interface Unit{
	//Name of unit
	String getName();
	//What the unit is measuring
	String getMeasurement();
	//Terms of base unit (may be N/A)
	Map<Unit, Integer> getMapping();
	//Base units
	BaseUnits getBaseUnits();
	//Convert from special mapping to base mapping
	BigFraction toBaseMapping(BigFraction in);
}
