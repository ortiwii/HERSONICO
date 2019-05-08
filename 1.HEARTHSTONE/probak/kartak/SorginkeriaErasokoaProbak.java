package kartak;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import trebetasunak.TrebetasunaDiana;
import trebetasunak.TrebetasunaErasoJarraia;

public class SorginkeriaErasokoaProbak {
	SorginkeriaErasokoa sor1, sor2;
	Morroia mo1, mo2, mo3;
	TrebetasunaErasoJarraia t1;
	TrebetasunaDiana t2;
	
	@Before
	public void setUp() throws Exception {
		//int pIdKarta, String pIzena, String pDeskribapena,int pBalioa, int pMina
		sor1 = new SorginkeriaErasokoa (0007, "Disparo", "Hace un punto de daño", 2, 1);
		sor2 = new SorginkeriaErasokoa (0004, "Un plaka", "Te mete un plaka en to el petxofrio ese que me llevas, pelau", 1, 1 );
		t1 = new TrebetasunaErasoJarraia();
		t2 = new TrebetasunaDiana();
		mo1 = new Morroia (0001, "Julen", "Lagun ona", 5, 4, 2, t1);
		mo2 = new Morroia (0002, "Janire", "Kafea gustatzen zaio", 2, 1, 1, null);
		mo3 = new Morroia (0003, "Camilo", "Presentazioak oso ondo egiten ditu", 3, 1, 4, t2);
	}

	@After
	public void tearDown() throws Exception {
		sor1 = null;
		sor2 = null;
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
		SorginkeriaErasokoa sorr1 = (SorginkeriaErasokoa) sor1.kopiaBatEgin();
		// Id berdina dutela konprobatzen dugu
		assertEquals(sorr1.getId(), sor1.getId());
		// Izen berdina dutela konprobatzen dugu
		assertEquals(sorr1.getIzena(), sor1.getIzena());
		// Deskribapen berdina dutela konprobatzen dugu
		assertEquals(sorr1.getDeskribapena(), sor1.getDeskribapena());
		// balio berdina dutela konprobatzen dugu
		assertEquals(sorr1.getBalioa(), sor1.getBalioa());
		// min berdina egiten dutela konprobatzen dugu
		assertEquals(sorr1.getMina(), sor1.getMina());
		
		
		
	}

	@Test
	public void testSorginkeriaErasokoa() {
		assertNotNull(sor1);
		assertNotNull(sor2);
	}

}
