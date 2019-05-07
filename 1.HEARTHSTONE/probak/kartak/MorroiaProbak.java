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
	public void testKopiaBatEgin() {
		
		Karta morroi1 = mo1.kopiaBatEgin();
		// sortzen dugu morroi bat kopia egiteko eta konprobatzen dugu atributuak berdinak direla.
		// Id berdina dutela konprobatzen dugu
		assertEquals(morroi1.getId(), mo1.getId());
		// Izen berdina badute konprobatzen dugu
		assertEquals(morroi1.getIzena(), mo1.getIzena());
		//  Deskribapen berbera dutela konprobatzen dugu
		assertEquals(morroi1.getDeskribapena(), mo1.getDeskribapena());
		// balio berdina dutela konprobatzen dugu.
		assertEquals(morroi1.getBalioa(), mo1.getBalioa());
		// erasoa berdina del konprobatzen dugu.
		
		
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
		assertEquals(mo1.getBizitza(), 2);
		mo1.kartaHoniErasotu(1);
		assertEquals(mo1.getBizitza(), 1);
	}

	@Test
	public void testErabiliTrebetasuna() {
		assertFalse(mo3.getNiriSoilikErasoAhal());
		mo3.setDiana();
		assertTrue(mo3.getNiriSoilikErasoAhal());
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
		assertEquals(mo1.getBizitza(), 2);
		assertEquals(mo2.getBizitza(), 1);
		assertEquals(mo3.getBizitza(), 4);
	}

	@Test
	public void testGetNiriSoilikErasoAhal() {
		// Este metodo es para saber si tienes diana o no.
		//t1 es eraso jarraia y t2 es diana
		assertFalse(mo3.getNiriSoilikErasoAhal());
		mo3.setDiana();
		assertTrue(mo3.getNiriSoilikErasoAhal());
	}

	@Test
	public void testGetTrebetasunaErabilita() {
		assertEquals(mo1.getTrebetasunaErabilita(), false);
		mo1.setTrebetasunaErabilita();
		assertEquals(mo1.getTrebetasunaErabilita(), true);
		// mo1 morroiak t1 trebetasuna du, hau da, eraso jarraia du
		
		assertEquals(mo3.getTrebetasunaErabilita(), false);
		mo3.setTrebetasunaErabilita();
		assertEquals(mo3.getTrebetasunaErabilita(), true);
		// mo3 morroiak t2 trebetasuna du, hau da, diana du
	}

	@Test
	public void testSetErasoa() {
		assertEquals(mo1.getErasoa(), 4);
		mo1.setErasoa(1);
		assertEquals(mo1.getErasoa(), 1);

		
		assertEquals(mo2.getErasoa(), 1);
		mo2.setErasoa(2);
		assertEquals(mo2.getErasoa(), 2);

		
		assertEquals(mo3.getErasoa(), 1);
		mo3.setErasoa(2);
		assertEquals(mo3.getErasoa(), 2);

	}

	@Test
	public void testSetTrebetasunaErabilita() {
		assertEquals(mo1.getTrebetasunaErabilita(), false);
		mo1.setTrebetasunaErabilita();
		assertEquals(mo1.getTrebetasunaErabilita(), true);
		// mo1 morroiak t1 trebetasuna du, hau da, eraso jarraia du
		
		assertEquals(mo3.getTrebetasunaErabilita(), false);
		mo3.setTrebetasunaErabilita();
		assertEquals(mo3.getTrebetasunaErabilita(), true);
		// mo3 morroiak t2 trebetasuna du, hau da, diana du
	}

}
