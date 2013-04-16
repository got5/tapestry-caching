package uk.co.ioko.tapestry.caching.services;

/**
 * Service used to create timers. It is used to register states at different locations in your code
 * to get code execution duration.
 */
public interface TimerService {
	
	/**
	 * Starts a new timer.
	 */
	public void start();
	
	/**
	 * Starts a new timer with custom label.
	 */
	public void start(String label);
	
	/**
	 * Registers a new state.
	 */
	public void saveState(String state);
	
	/**
	 * Stop current timer.
	 */
	public void stop();
	
	/**
	 * Returns true if a timer has been launched, false otherwise.
	 */
	public Boolean isStarted();
	
	/**
	 * Lists all the flags a started timer contains.
	 */
	public String toString();
	
	/**
	 * Displays all timer flags.
	 */
	public void info();
}
