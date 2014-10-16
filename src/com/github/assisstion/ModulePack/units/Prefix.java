package com.github.assisstion.ModulePack.units;

import com.github.assisstion.ModulePack.BigFraction;
import com.github.assisstion.ModulePack.annotation.Dependency;

@Dependency(BigFraction.class)
public interface Prefix{

	Prefix NONE = new Prefix(){
		@Override
		public BigFraction convert(BigFraction in){
			return in;
		}
	};

	BigFraction convert(BigFraction in);

	default double convert(double in){
		return convert(new BigFraction(1, 1)).doubleValue() * in;
	}

	public static Prefix make(BigFraction bi){
		return new ProportionalConversion(bi);
	}

	public static class ProportionalConversion implements Prefix{

		protected BigFraction bi;

		public ProportionalConversion(BigFraction bi){
			this.bi = bi;
		}

		@Override
		public BigFraction convert(BigFraction in){
			return in.multiply(bi);
		}

	}
}
