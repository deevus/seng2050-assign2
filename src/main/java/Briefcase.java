/*

	Simon Hartcher
	C3185790

	SENG2050 Assignment 2

 */

package game;
import java.text.DecimalFormat;

/**
 * Briefcase bean
 */
public class Briefcase {
	private double value;
	private boolean open;

	/**
	 * Initialise the Briefcase class
	 */
	public Briefcase() {
		value = 0;
		open = false;
	}

	/**
	 * Gets the value contained in the Briefcase
	 * @return The value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Sets the value in the Briefcase
	 * @param newValue The value to set
	 */
	public void setValue(double newValue) {
		value = newValue;
	}

	/**
	 * Returns a boolean value determining
	 * whether the briefcase has been opened
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * Opens the briefcase
	 */
	public void setOpen() {
		open = true;
	}

	/**
	 * String representation of the value inside
	 * the briefcase
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("$0.00");
		return df.format(value);
	}
}