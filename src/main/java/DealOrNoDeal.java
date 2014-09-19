package game;
import java.text.DecimalFormat;
import java.util.*;

public class DealOrNoDeal {
	private static final int numBriefcases = 12;
	private final double[] briefcaseValues = new double[] { 0.5, 1, 10, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000 };
	private Hashtable<Integer, Briefcase> briefcases = new Hashtable<Integer, Briefcase>(numBriefcases);
	private int roundNum = 1;
	private final int totalRounds = 18;
	private int numRevealed = 0;
	private boolean[] offerRounds = new boolean[totalRounds];

	public DealOrNoDeal() { 
		initBriefcases();

		offerRounds[5] = true;
		offerRounds[9] = true;
		offerRounds[12] = true;
		offerRounds[14] = true;
		offerRounds[16] = true;
	}

	private void initBriefcases() {

		//put values into linked list
		LinkedList<Double> values = new LinkedList<Double>();
		for (int i = 0; i < briefcaseValues.length; i++) {
			values.add(briefcaseValues[i]);
		}

		//init random generator
		Random r = new Random(new Date().getTime());
		while (values.size() > 0) {
			//get a random index value
			int index = r.nextInt(values.size());

			//get it from the list
			//then remove it
			double value = values.get(index);
			values.remove(index);

			//create our briefcase
			//then insert into hashtable
			Briefcase b = new Briefcase();
			b.setValue(value);

			briefcases.put(b.hashCode(), b);
		}
	}

	public Hashtable<Integer, Briefcase> getBriefcases() {
		return briefcases;
	}

	public int getRoundNumber() {
		return roundNum;
	}

	public boolean isOfferRound() {
		return offerRounds[roundNum];
	}

	public double getOfferAmount() {
		//get total amount remaining
		double total = 0;
		for (Enumeration<Integer> keys = briefcases.keys(); keys.hasMoreElements();) {
			Briefcase b = briefcases.get(keys.nextElement());
			if (!b.isOpen()) total += b.getValue();
		}

		double offerAmount = total / (numBriefcases - numRevealed);
		return offerAmount;
	}

	public boolean open(int id) {
		if (offerRounds[roundNum] || briefcases.get(id).isOpen()) return false;

		briefcases.get(id).setOpen();
		roundNum++;
		numRevealed++;

		return true;
	}

	public void acceptOffer(boolean deal) {
		if (!deal) {
			roundNum++;
		}
		else {
			//TODO: end game
		}
	}
}