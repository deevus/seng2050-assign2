/*

	Simon Hartcher
	C3185790

	SENG2050 Assignment 2

 */

package game;
import java.text.DecimalFormat;
import java.util.*;

public class DealOrNoDeal {
	private static final int numBriefcases = 12;
	private final double[] briefcaseValues = new double[] { 0.5, 1, 10, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000 };
	private Dictionary<Integer, Briefcase> briefcases = new Hashtable<Integer, Briefcase>(numBriefcases);
	private int roundNum = 1;
	private final int totalRounds = 19;
	private int numRevealed = 0;
	private boolean[] offerRounds = new boolean[totalRounds];
	private final int firstOffer = 5;
	private final int secondOffer = 9;
	private final int thirdOffer = 12;
	private final int finalOffer = 14;
	private boolean gameOver = false;
	private double winAmount;

	/**
	 * Create a new Deal Or No Deal game
	 */
	public DealOrNoDeal() { 
		initBriefcases();

		offerRounds[firstOffer] = true;
		offerRounds[secondOffer] = true;
		offerRounds[thirdOffer] = true;
		offerRounds[finalOffer] = true;
	}

	/**
	 * Randomises the briefcases
	 */
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

	/**
	 * Returns the list of briefcases cases
	 */
	public Dictionary<Integer, Briefcase> getBriefcases() {
		return briefcases;
	}

	/**
	 * Returns the round number
	 */
	public int getRoundNumber() {
		return roundNum;
	}

	/**
	 * Returns whether round is an offer round
	 */
	public boolean isOfferRound() {
		return offerRounds[roundNum];
	}

	/**
	 * Returns whether offer round is the final offer
	 */
	public boolean isFinalOffer() {
		return roundNum == finalOffer;
	}

	/**
	 * Returns the bank offer amount
	 */
	public double getOfferAmount() {
		//get total amount remaining
		double total = 0;

		//add up all the remaining unopened cases
		for (Enumeration<Integer> keys = briefcases.keys(); keys.hasMoreElements();) {
			Briefcase b = briefcases.get(keys.nextElement());
			if (!b.isOpen()) total += b.getValue();
		}

		//calculate the value
		double offerAmount = total / (numBriefcases - numRevealed);
		return offerAmount;
	}

	public boolean open(int id) {
		//cant open a case on an offer round 
		//or reopen a case
		//or open a case when the game is over
		if (this.isOfferRound() || briefcases.get(id).isOpen() || gameOver) return false;

		//open case and increment counts
		briefcases.get(id).setOpen();
		roundNum++;
		numRevealed++;

		//revealed all but one
		if (numRevealed == numBriefcases - 1) {
			//game is over
			gameOver = true;

			//open last case
			for (Enumeration<Integer> keys = briefcases.keys(); keys.hasMoreElements(); ) {
				//find the case that isn't open
				Briefcase b = briefcases.get(keys.nextElement());
				if (!b.isOpen()) {
					//open the case
					b.setOpen();

					//get the winning amount
					winAmount = b.getValue();

					//increment counts
					roundNum++;
					numRevealed++;
					break;
				}
			}
		}

		return true;
	}

	/**
	 * Accept or deny a bank offer
	 */
	public void acceptOffer(boolean deal) {
		//no deal
		if (!deal) {
			roundNum++;
		}
		//deal
		else {
			//set the win amount
			winAmount = this.getOfferAmount();

			//game is now over
			gameOver = true;
		}
	}

	/**
	 * Returns whether the game is finished
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * Returns a double value of the won amount
	 */
	public double getWinnings() {
		return winAmount;
	}
}