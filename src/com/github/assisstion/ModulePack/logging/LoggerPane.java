package com.github.assisstion.ModulePack.logging;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javax.lang.model.SourceVersion;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.github.assisstion.ModulePack.Pair;
import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.Dependency;

@Dependency(Pair.class)
@CompileVersion(SourceVersion.RELEASE_8) // Consumer<T>
public class LoggerPane extends JPanel implements Consumer<String>{

	public Logger log;

	private static final long serialVersionUID = 1022490441168101112L;
	protected JTextPane textPane;
	private JScrollPane scrollPane;
	protected LogWorker worker;
	protected ProgressWorker progress;
	private JProgressBar progressBar;
	protected Style style;
	protected StyledDocument document;
	//protected String separator;
	protected Color color = Color.BLACK;

	protected String separator;
	protected boolean reverseOrder;

	public LoggerPane(Logger log, boolean showProgress, boolean reverseOrder){
		this(log, showProgress, "\n", reverseOrder);
	}

	public LoggerPane(Logger log, boolean showProgress, String separator){
		this(log, showProgress, separator, false);
	}

	public LoggerPane(Logger log, boolean showProgress){
		this(log, showProgress, "\n", false);
	}

	/**
	 * Create the frame.
	 */
	public LoggerPane(Logger log, boolean showProgress, String separator, boolean reverseOrder){
		//setTitle();

		setLayout(new BorderLayout(0, 0));

		this.log = log;
		this.reverseOrder = reverseOrder;
		LogHandler handler = new LogHandler(this);
		log.addHandler(handler);

		scrollPane = new JScrollPane();
		add(scrollPane);

		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);

		progressBar = new JProgressBar(0, 0);
		progressBar.setEnabled(false);
		progressBar.setStringPainted(false);
		if(showProgress){
			add(progressBar, BorderLayout.SOUTH);
		}

		if(!reverseOrder){
			DefaultCaret caret = (DefaultCaret) textPane.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		}

		document = textPane.getStyledDocument();
		style = document.addStyle("text-style", null);
		StyleConstants.setForeground(style, Color.BLACK);

		worker = new LogWorker();
		progress = new ProgressWorker();

		this.separator = separator;
	}

	/*
	public void setSeparator(String separator){
		this.separator = separator;
	}

	public String getSeparator(){
		return separator;
	}
	 */

	public void setProgress(Pair<Integer, Integer> values){
		progress.push(values);
	}

	public class LogWorker extends SwingWorker<Object, Pair<String, Color>>{

		protected void push(Pair<String, Color> message){
			publish(message);
		}

		@Override
		protected Object doInBackground() throws Exception{
			return null;
		}

		@Override
		protected void process(List<Pair<String, Color>> messages){
			for(Pair<String, Color> messageOne : messages){
				String tempSeparator = "";
				if(!textPane.getText().equals("")){
					tempSeparator = separator;
				}
				StyleConstants.setForeground(style, messageOne.getValueTwo());
				try{
					if(reverseOrder){
						document.insertString(0,
								messageOne.getValueOne() + tempSeparator, style);
					}
					else{
						document.insertString(document.getLength(),
								tempSeparator + messageOne.getValueOne(), style);
					}
				}
				catch(BadLocationException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public class ProgressWorker extends SwingWorker<Object, Pair<Integer, Integer>>{

		protected void push(Pair<Integer, Integer> progress){
			publish(progress);
		}

		@Override
		protected Object doInBackground() throws Exception{
			return null;
		}

		@Override
		protected void process(List<Pair<Integer, Integer>> integerPairs){
			for(Pair<Integer, Integer> pairOne : integerPairs){
				if(pairOne.getValueTwo().equals(0)){
					progressBar.setEnabled(false);
					progressBar.setStringPainted(false);
				}
				else{
					progressBar.setEnabled(true);
					progressBar.setStringPainted(true);
				}
				progressBar.setValue(pairOne.getValueOne());
				progressBar.setMaximum(pairOne.getValueTwo());
			}
		}
	}

	@Override
	public void accept(String t){
		worker.push(new Pair<String, Color>(t, color));
	}
}