package com.github.assisstion.ModulePack.logging;

import java.util.Calendar;
import java.util.function.Consumer;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.Pair;
import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.Dependency;

@Dependency(Pair.class)
@CompileVersion(SourceVersion.RELEASE_8) // Consumer<T>
public class LogHandler extends Handler{

	protected Consumer<String> sc;

	public LogHandler(Consumer<String> consumer){
		sc = consumer;
	}

	@Override
	public void close() throws SecurityException{
		// Unimplemented

	}

	@Override
	public void flush(){
		// Unimplemented
	}

	@Override
	public void publish(LogRecord record){
		Calendar c = Calendar.getInstance();
		String timeStamp = String.format("%1$tY-%1$tm-%1td %1$tH:%1$tM:%1$tS", c);
		if(!record.getLevel().getName().equals("NOMESSAGE")){
			sc.accept(timeStamp + " - [" + record.getLevel().getName() + "] " + record.getMessage());
		}
		else{
			sc.accept(record.getMessage());
		}
	}
}
