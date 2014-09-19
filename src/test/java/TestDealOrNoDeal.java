import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import game.*;

public class TestDealOrNoDeal {
	@Test
	public void testInit() {
		DealOrNoDeal game = new DealOrNoDeal();

		assertEquals(12, game.getBriefcases().size());
	}

	@Test
	public void testBriefcases() {
		DealOrNoDeal game = new DealOrNoDeal();

		Hashtable<Integer, Briefcase> bht = game.getBriefcases();
		for (Enumeration<Integer> i = bht.keys(); i.hasMoreElements(); ) {
			Briefcase b = bht.get(i.nextElement());
			assertTrue(b.getValue() > 0);
		}
	}

	@Test
	public void testRounds() {
		DealOrNoDeal game = new DealOrNoDeal();

		//create a stack of ids and push ids onto stack
		Stack<Integer> ids = new Stack<Integer>();
		for (Enumeration<Integer> keys = game.getBriefcases().keys(); keys.hasMoreElements();) {
			ids.push(keys.nextElement());
		}

		assertTrue(ids.size() == 12);

		assertTrue(game.open(ids.pop())); //1
		assertTrue(game.open(ids.pop())); //2
		assertTrue(game.open(ids.pop())); //3
		assertTrue(game.open(ids.pop())); //4

		assertTrue(game.getRoundNumber() == 5);
		assertTrue(game.isOfferRound()); //5
		game.acceptOffer(false);

		assertTrue(game.open(ids.pop())); //6
		assertTrue(game.open(ids.pop())); //7
		assertTrue(game.open(ids.pop())); //8

		assertTrue(game.isOfferRound()); //9
		game.acceptOffer(false);

		assertTrue(game.open(ids.pop())); //10
		assertTrue(game.open(ids.pop())); //11

		assertTrue(game.isOfferRound()); //12
		game.acceptOffer(false);

		assertTrue(game.open(ids.pop())); //13

		assertTrue(game.isOfferRound()); //14
		game.acceptOffer(false);

		assertTrue(game.open(ids.pop())); //15

		assertTrue(game.isOfferRound()); //16
		game.acceptOffer(false);

		assertTrue(game.open(ids.pop())); //17
	}

	@Test
	public void testOfferAmount() {
		DealOrNoDeal game = new DealOrNoDeal();

		//create a stack of ids and push ids onto stack
		Stack<Integer> ids = new Stack<Integer>();
		for (Enumeration<Integer> keys = game.getBriefcases().keys(); keys.hasMoreElements();) {
			ids.push(keys.nextElement());
		}

		assertTrue(game.open(ids.pop())); //1
		assertTrue(game.open(ids.pop())); //2
		assertTrue(game.open(ids.pop())); //3
		assertTrue(game.open(ids.pop())); //4

		assertTrue(game.getRoundNumber() == 5);
		assertTrue(game.isOfferRound());

		assertTrue(game.getOfferAmount() > 0);
	}
}