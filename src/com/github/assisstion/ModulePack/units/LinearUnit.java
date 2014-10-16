package com.github.assisstion.ModulePack.units;

import java.util.Map;

import com.github.assisstion.ModulePack.BigFraction;

public class LinearUnit extends AbstractUnit{

	protected BigFraction multiplier = new BigFraction(1, 1);

	public LinearUnit(String name, String measurement){
		super(name, measurement);
	}

	public LinearUnit(String name, String measurement, BaseUnits base){
		super(name, measurement, base);
	}

	public LinearUnit(String name, String measurement, BaseUnits base, Map<Unit, Integer> mapping){
		super(name, measurement, base, mapping);
	}

	public LinearUnit(String name, String measurement, BigFraction multi){
		super(name, measurement);
		multiplier = multi;
	}

	public LinearUnit(String name, String measurement, BaseUnits base, BigFraction multi){
		super(name, measurement, base);
		multiplier = multi;
	}

	public LinearUnit(String name, String measurement, BaseUnits base,
			Map<Unit, Integer> mapping, BigFraction multi){
		super(name, measurement, base, mapping);
		multiplier = multi;
	}

	@Override
	public BigFraction toBaseMapping(BigFraction in){
		return in.multiply(multiplier);
	}

}
