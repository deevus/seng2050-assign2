import org.junit.Test;
import static org.junit.Assert.*;
import game.*;

public class TestDealOrNoDeal {
	@Test
	public void TestInit() {
		DealOrNoDeal game = new DealOrNoDeal();

		assertEquals(12, game.getBriefcases().length);
	}

	public void TestBriefcases() {
		DealOrNoDeal game = new DealOrNoDeal();

		Briefcase[] bArr = game.getBriefcases();
		for (int i = 0; i < bArr.length; i++) {
			assertTrue(bArr[i].getValue() > 0);
		}
	}
}