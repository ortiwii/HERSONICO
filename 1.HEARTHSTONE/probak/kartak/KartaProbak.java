package kartak;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import trebetasunak.Trebetasuna;

public class KartaProbak {
	Morroia mo1, mo2, mo3, mo4;
	Trebetasuna  erasoJarraia, diana;

	@Before
	public void setUp() throws Exception {
		// int pIdKarta, String pIzena, String pDeskribapena, int pBalioa, boolean pErasoAhalDu
		mo1= new Morroia (0001, "Otso Iluna", "Trebetasunik gabeko morroia, baina oso eraginkorra", 2, 2, 3, null);
		mo2 = new Morroia (0002, "Txerri Humoretsua", "Eraso jarraia duen morroia haserrea, jarraieran eraso dezake", 3, 3, 1, null );
//		mo3 = new Morroia ();
		// 0002-Txerri Humoretsua-Eraso Jarraia duen morroi haserrea, jarraieran eraso dezake-3-3-1-Eraso Jarraia;
		//int pIdKarta, String pIzena, String pDeskribapena, int pBalioa, int pErasoa, int pBizitza, Trebetasuna pTrebetasuna
		
	}

	@After
	public void tearDown() throws Exception {
		mo1 = null;
		mo2 = null;
	}

	@Test
	public void testKarta() {
		assertNotNull(mo1);
		assertNotNull(mo2);
//		assertNotNull(mo3);
//		assertNotNull(mo4);
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
	public void testIdHauDu() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetZelairaAteratakoTxanda() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmanDeskribapena() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetErasoDezakeen() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBalioa() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetZelairaAteratakoTxanda() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetErasoDezakeen() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIzena() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDeskribapena() {
		fail("Not yet implemented");
	}

}
