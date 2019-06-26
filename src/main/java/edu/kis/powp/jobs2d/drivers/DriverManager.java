package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;
import edu.kis.powp.observer.Publisher;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Driver manager provides means to setup the driver. It also enables other
 * components and features of the application to react on configuration changes.
 */
public class DriverManager extends Publisher {
	private Job2dDriver currentDriver = new LoggerDriver();

	/**
	 * @param driver Set the driver as current.
	 */
	public synchronized void setCurrentDriver(Job2dDriver driver) {
		currentDriver = driver;
		notifyObservers();
	}

	/**
	 * @return Current driver.
	 */
	public synchronized Job2dDriver getCurrentDriver() {
		return currentDriver;
	}
}
