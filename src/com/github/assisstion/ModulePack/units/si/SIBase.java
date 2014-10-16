package com.github.assisstion.ModulePack.units.si;

import com.github.assisstion.ModulePack.units.LinearUnit;

public class SIBase{

	public static class Meter extends LinearUnit{

		protected static Meter instance = new Meter();

		protected Meter(){
			super("meter", "length", SIMetric.get());
			mapping.put(this, 1);
		}

		public static Meter get(){
			return instance;
		}
	}

	public static class Gram extends LinearUnit{

		protected static Gram instance = new Gram();

		protected Gram(){
			super("gram", "mass", SIMetric.get());
			mapping.put(this, 1);
		}

		public static Gram get(){
			return instance;
		}
	}

	public static class Second extends LinearUnit{

		protected static Second instance = new Second();

		protected Second(){
			super("second", "time", SIMetric.get());
			mapping.put(this, 1);
		}

		public static Second get(){
			return instance;
		}
	}

	public static class Kelvin extends LinearUnit{

		protected static Kelvin instance = new Kelvin();

		protected Kelvin(){
			super("kelvin", "temperature", SIMetric.get());
			mapping.put(this, 1);
		}

		public static Kelvin get(){
			return instance;
		}
	}

	public static class Ampere extends LinearUnit{

		protected static Ampere instance = new Ampere();

		protected Ampere(){
			super("ampere", "electric current", SIMetric.get());
			mapping.put(this, 1);
		}

		public static Ampere get(){
			return instance;
		}
	}

	public static class Mole extends LinearUnit{

		protected static Mole instance = new Mole();

		protected Mole(){
			super("mole", "amount of substance", SIMetric.get());
			mapping.put(this, 1);
		}

		public static Mole get(){
			return instance;
		}
	}

	public static class Candela extends LinearUnit{

		protected static Candela instance = new Candela();

		protected Candela(){
			super("candela", "luminous intensity", SIMetric.get());
			mapping.put(this, 1);
		}

		public static Candela get(){
			return instance;
		}
	}
}
