package kartak;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import trebetasunak.Trebetasuna;
import trebetasunak.TrebetasunaDiana;
import trebetasunak.TrebetasunaErasoJarraia;

public class MorroiaProbak {
	Morroia mo1, mo2, mo3;
	TrebetasunaErasoJarraia t1;
	TrebetasunaDiana t2;
	@Before
	public void setUp() throws Exception {
		t1 = new TrebetasunaErasoJarraia();
		t2 = new TrebetasunaDiana();
		mo1 = new Morroia (0001, "Julen", "Lagun ona", 5, 4, 2, t1);
		mo2 = new Morroia (0002, "Janire", "Kafea gustatzen zaio", 2, 1, 1, null);
		mo3 = new Morroia (0003, "Camilo", "Presentazioak oso ondo egiten ditu", 3, 1, 4, t2);
	}

	@After
	public void tearDown() throws Exception {
		t1 = null;
		t2 = null;
		mo1 = null;
		mo2 = null;
		mo3 = null;
	}

	@Test
	public void testJokatuKarta() {
		fail("Not yet implemented");
	}

	@Test
	public void testImprimatu() {
		fail("Not yet implemented");
	}

	@Test
	public void testEgoeraEguneratu() {
		fail("Not yet implemented");
	}

	@Test
	public void testKopiaBatEgin() {
		fail("Not yet implemented");
	}

	@Test
	public void testMorroia() {
		assertNotNull(mo1);
		assertNotNull(mo2);
		assertNotNull(mo3);
		assertNotNull(t1);
		assertNotNull(t2);


	}

	@Test
	public void testKartaHoniErasotu() {
		fail("Not yet implemented");
	}

	@Test
	public void testErabiliTrebetasuna() {
		fail("Not yet implemented");
	}

	@Test
	public void testEgikarituTrebetasuna() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDiana() {
		assertFalse(mo2.getNiriSoilikErasoAhal());
		// Hemen ez du eraso jarraia oraindik...
		mo2.setDiana();
	}

	@Test
	public void testGetErasoa() {
		assertEquals(mo1.getErasoa(), 4);
		assertEquals(mo2.getErasoa(), 1);
		assertEquals(mo3.getErasoa(), 1);
		//int pIdKarta, String pIzena, String pDeskribapena, int pBalioa, int pErasoa, int pBizitza, Trebetasuna pTrebetasuna
	}

	@Test
	public void testGetBizitza() {
		assertEquals(mo1.getBizitza(),2);
		assertEquals(mo2.getBizitza(),1);
		assertEquals(mo3.getBizitza(),4);

	}

	@Test
	public void testGetNiriSoilikErasoAhal() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTrebetasunaErabilita() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetErasoa() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTrebetasunaErabilita() {
		fail("Not yet implemented");
	}

}
