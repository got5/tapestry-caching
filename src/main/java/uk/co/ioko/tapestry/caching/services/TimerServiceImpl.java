package uk.co.ioko.tapestry.caching.services;

import java.util.ArrayList;

import org.slf4j.Logger;

/**
 * TimerService implementation.
 */
public class TimerServiceImpl implements TimerService {
	
	private static final String START_STATE_LABEL = "Timer Start"; 
	
	private ArrayList<State> states;
	
	private long startTime;
	
	private final Logger log;
	
	public TimerServiceImpl(Logger log) {
		this.log = log;
	}
	
	/**
	 * Starts a new timer.
	 */
	public void start() {
		start(START_STATE_LABEL);
	}
	
	/**
	 * Starts a new timer.
	 */
	public void start(String label) {
		if (!isStarted()) {
			states = new ArrayList<TimerServiceImpl.State>();
			startTime = System.currentTimeMillis();
			states.add(new State(label, 0));
		}
		else if (label != null) {
			saveState(label);
		}
	}
	
	/**
	 * Puts a flag in the timer.
	 */
	public void saveState(String state) {
		if (!isStarted()) {
			start(state);
		}
		else
		{
			states.add(new State(state, System.currentTimeMillis() - startTime));
		}
	}
	
	/**
	 * Stop the timer.
	 */
	public void stop() {
		info();
		states = null;
	}
	
	/**
	 * Returns true if a timer has been launched, false otherwise.
	 */
	public Boolean isStarted() {
		return states != null;
	}
	
	/**
	 * Lists all the flags a started timer contains.
	 */
	@Override
	public String toString() {
		String result = "";
		
		if (isStarted()) {
			for (State state : states) {
				result += "[TIMER] " + state.time + " ms => state '" + state.label + "' called by : " + state.stack + "\n";
			}
		}
		
		return result;
	}
	
	/**
	 * Returns the time interval since the timer has started.
	 */
	public long getCurrentTime() {
		return System.currentTimeMillis() - startTime;
	}
	
	/**
	 * Displays all timer flags.
	 */
	public void info() {
		log.info(" Timer states list : \n" + toString());
	}
	
	private class State {
		
		public final String label;
		
		public final long time;
		
		public final String stack;
		
		public State(String label, long time) {
			this.label = label;
			this.time = time;
			this.stack = getStackTrace();
		}
		
		private String getStackTrace() {
			final StackTraceElement[] threads = Thread.currentThread().getStackTrace();
			String fileName;
			for (StackTraceElement thread : threads) {
				fileName = thread.getFileName();
				if (fileName != null && fileName.indexOf("Thread") == -1 && fileName.indexOf("TimerService") == -1) {
					return thread.getClassName() + " " + thread.getMethodName() + " : ln" + thread.getLineNumber();
				}
			}
			return null;
		}
	}
}
