package game;
import java.util.*;

public class DealOrNoDeal {
	private static final int numBriefcases = 12;
	private final double[] briefcaseValues = new double[] { 0.5, 1, 10, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000 };
	private LinkedHashSet briefcaseSet;
	private Briefcase[] briefcases = new Briefcase[numBriefcases];

	public DealOrNoDeal() { 
		briefcaseSet = new LinkedHashSet();
		initBriefcases();
	}

	private void initBriefcases() {
		//init random generator
		Random r = new Random(new Date().getTime());

		//random range value
		int max = numBriefcases - 1;

		/*
			Loop until we have inserted
			12 unique values into a set
		*/
		do {
			int index = r.nextInt(max + 1);
			briefcaseSet.add(briefcaseValues[index]);
		} while (briefcaseSet.size() < numBriefcases);

		/*
			Then iterate that set and insert 
			into an array
		*/
		Iterator iterator = briefcaseSet.iterator();
		for (int i = 0; i < briefcaseSet.size(); i++) {
			Briefcase b = new Briefcase();
			b.setValue((double)iterator.next());

			briefcases[i] = b;
		}
	}

	public Briefcase[] getBriefcases() {
		return briefcases;
	}
}