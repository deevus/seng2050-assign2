package game;
import java.text.DecimalFormat;

public class Briefcase {
	private double value;
	private boolean open;

	public Briefcase() {
		value = 0;
		open = false;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double newValue) {
		value = newValue;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen() {
		open = true;
	}

	public String toString() {
		DecimalFormat df = new DecimalFormat("$0.00");
		return df.format(value);
	}
}