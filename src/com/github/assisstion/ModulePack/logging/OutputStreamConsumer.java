package com.github.assisstion.ModulePack.logging;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.function.Consumer;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.annotation.CompileVersion;

@CompileVersion(SourceVersion.RELEASE_8) // Consumer<T>
public class OutputStreamConsumer implements Consumer<String>, Closeable{

	protected BufferedWriter bos;

	protected OutputStreamConsumer(){

	}

	public OutputStreamConsumer(Writer out){
		bos = new BufferedWriter(out);
	}

	public OutputStreamConsumer(OutputStream out){
		bos = new BufferedWriter(new OutputStreamWriter(out));
	}

	@Override
	public void accept(String s){
		try{
			bos.write(s + "\n");
		}
		catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException{
		bos.close();
	}

}
