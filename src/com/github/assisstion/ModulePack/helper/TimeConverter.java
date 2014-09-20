package com.github.assisstion.ModulePack.helper;

public final class TimeConverter {
	private TimeConverter(){}

	public static Time expand(long seconds){
		long secondsLeft = seconds % 60;
		if(seconds-secondsLeft == 0){
			return new Time(0, 0, 0, 0, secondsLeft);
		}
		long totalMinutes = (seconds - secondsLeft) / 60;
		long minutesLeft = totalMinutes % 60;
		if(totalMinutes-minutesLeft == 0){
			return new Time(0, 0, 0, minutesLeft, secondsLeft);
		}
		long totalHours = (totalMinutes - minutesLeft) / 60;
		long hoursLeft = totalHours % 24;
		if(totalHours-hoursLeft == 0){
			return new Time(0, 0, hoursLeft, minutesLeft, secondsLeft);
		}
		long totalDays = (totalHours - hoursLeft) / 24;
		long daysLeft = totalDays % 365;
		if(totalDays-daysLeft == 0){
			return new Time(0, daysLeft, hoursLeft, minutesLeft, secondsLeft);
		}
		long yearsLeft = (totalDays - daysLeft) / 365;
		return new Time(yearsLeft, daysLeft, hoursLeft, minutesLeft, secondsLeft);
	}

	public static Time expandMillis(long milliseconds){
		long millisLeft = milliseconds % 1000;
		if(milliseconds-millisLeft == 0){
			return new Time(0, 0, 0, 0, 0, millisLeft);
		}
		long totalSeconds = (milliseconds - millisLeft) / 1000;
		long secondsLeft = totalSeconds % 60;
		if(totalSeconds-secondsLeft == 0){
			return new Time(0, 0, 0, 0, secondsLeft, millisLeft);
		}
		long totalMinutes = (totalSeconds - secondsLeft) / 60;
		long minutesLeft = totalMinutes % 60;
		if(totalMinutes-minutesLeft == 0){
			return new Time(0, 0, 0, minutesLeft, secondsLeft, millisLeft);
		}
		long totalHours = (totalMinutes - minutesLeft) / 60;
		long hoursLeft = totalHours % 24;
		if(totalHours-hoursLeft == 0){
			return new Time(0, 0, hoursLeft, minutesLeft, secondsLeft, millisLeft);
		}
		long totalDays = (totalHours - hoursLeft) / 24;
		long daysLeft = totalDays % 365;
		if(totalDays-daysLeft == 0){
			return new Time(0, daysLeft, hoursLeft, minutesLeft, secondsLeft, millisLeft);
		}
		long yearsLeft = (totalDays - daysLeft) / 365;
		return new Time(yearsLeft, daysLeft, hoursLeft, minutesLeft, secondsLeft, millisLeft);
	}

	public static Time expandWithoutYears(long seconds){
		long secondsLeft = seconds % 60;
		if(seconds-secondsLeft == 0){
			return new Time(0, 0, 0, 0, secondsLeft);
		}
		long totalMinutes = (seconds - secondsLeft) / 60;
		long minutesLeft = totalMinutes % 60;
		if(totalMinutes-minutesLeft == 0){
			return new Time(0, 0, 0, minutesLeft, secondsLeft);
		}
		long totalHours = (totalMinutes - minutesLeft) / 60;
		long hoursLeft = totalHours % 24;
		if(totalHours-hoursLeft == 0){
			return new Time(0, 0, hoursLeft, minutesLeft, secondsLeft);
		}
		long daysLeft = (totalHours - hoursLeft) / 24;
		return new Time(0, daysLeft, hoursLeft, minutesLeft, secondsLeft);
	}

	public static long contract(long years, long days, long hours, long minutes, long seconds){
		return years * 31536000 + days * 86400 + hours * 3600 + minutes * 60 + seconds;
	}

	public static double contract(double years, double days, double hours, double minutes, double seconds){
		return years * 31536000 + days * 86400 + hours * 3600 + minutes * 60 + seconds;
	}

	public static long contract(Time time){
		return time.years * 31536000 + time.days * 86400 + time.hours * 3600 + time.minutes * 60 + time.seconds;
	}

	public static class Time{
		public long years;
		public byte days;
		public byte hours;
		public byte minutes;
		public byte seconds;
		public int milliseconds;

		public Time(long years, long days, long hours, long minutes, long seconds){
			this(years, (byte) days, (byte) hours, (byte) minutes, (byte) seconds, 0);
		}

		public Time(long years, long days, long hours, long minutes, long seconds, long milliseconds){
			this(years, (byte) days, (byte) hours, (byte) minutes, (byte) seconds, (int) milliseconds);
		}

		public Time(long years, byte days, byte hours, byte minutes, byte seconds){
			this(years, days, hours, minutes, seconds, 0);
		}

		public Time(long years, byte days, byte hours, byte minutes, byte seconds, int milliseconds){
			this.years = years;
			this.days = days;
			this.hours = hours;
			this.minutes = minutes;
			this.seconds = seconds;
			this.milliseconds = milliseconds;
		}

		@Override
		public String toString(){
			return years + " years, " + days + " days, " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds, " + milliseconds + " milliseconds";
		}
	}
}
