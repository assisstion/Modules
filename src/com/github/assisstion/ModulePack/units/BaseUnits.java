package com.github.assisstion.ModulePack.units;

import java.util.Map;

import com.github.assisstion.ModulePack.annotation.LimitedImmutable;

@LimitedImmutable({})
public interface BaseUnits{
	String getName();
	Map<Unit, Prefix> getUnits();
}
