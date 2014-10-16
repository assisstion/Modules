package com.github.assisstion.ModulePack.units.si;

import com.github.assisstion.ModulePack.units.BaseUnitMap;
import com.github.assisstion.ModulePack.units.BaseUnits;
import com.github.assisstion.ModulePack.units.Prefix;
import com.github.assisstion.ModulePack.units.si.SIBase.Ampere;
import com.github.assisstion.ModulePack.units.si.SIBase.Candela;
import com.github.assisstion.ModulePack.units.si.SIBase.Gram;
import com.github.assisstion.ModulePack.units.si.SIBase.Kelvin;
import com.github.assisstion.ModulePack.units.si.SIBase.Meter;
import com.github.assisstion.ModulePack.units.si.SIBase.Mole;
import com.github.assisstion.ModulePack.units.si.SIBase.Second;


public class SIMetric extends BaseUnitMap{

	public static void main(String[] args){
		System.out.println(get().getUnits());
	}

	protected static SIMetric instance = new SIMetric();

	protected SIMetric(){
		baseUnits.put(Meter.get(), Prefix.NONE);
		baseUnits.put(Gram.get(), SIPrefix.KILO);
		baseUnits.put(Second.get(), Prefix.NONE);
		baseUnits.put(Kelvin.get(), Prefix.NONE);
		baseUnits.put(Ampere.get(), Prefix.NONE);
		baseUnits.put(Candela.get(), Prefix.NONE);
		baseUnits.put(Mole.get(), Prefix.NONE);
	}

	@Override
	public String getName(){
		return "metric";
	}

	public static BaseUnits get(){
		return instance;
	}

}
