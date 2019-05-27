package kartak;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import a.projectPackage.Partida;
import trebetasunak.TrebetasunaDiana;
import trebetasunak.TrebetasunaErasoJarraia;
import trebetasunak.TrebetasunaVendetta;

public class ListaKartakProbak 
{
	Morroia mo1, mo2, mo3, moDiana;
	TrebetasunaErasoJarraia t1;
	TrebetasunaVendetta t2;
	TrebetasunaDiana tD;
	
	ListaKartak kartaGuztiak,l1,l2,l3,l4,l5;
	
	@Before
	public void setUp() throws Exception 
	{
		t1 = new TrebetasunaErasoJarraia();
		t2 = new TrebetasunaVendetta();
		tD = new TrebetasunaDiana();
		
		moDiana = new Morroia (0000, "Julen", "Lagun ona", 1, 4, 2, tD);
		mo1 = new Morroia (0001, "Julen", "Lagun ona", 2, 4, 2, t1);
		mo2 = new Morroia (0002, "Janire", "Kafea gustatzen zaio", 3, 1, 1, null);
		mo3 = new Morroia (0003, "Camilo", "Presentazioak oso ondo egiten ditu", 4, 1, 4, t2);		
		
		Partida.getNirePartida().kartaGuztiakKargatu();
		kartaGuztiak = Partida.kartaGuztiak;
		l1 = new ListaKartak ();
		l2 = new ListaKartak ();
	}

	@After
	public void tearDown() throws Exception 
	{
		t1 = null;
		t2 = null;
		tD = null;
		mo1 = null;
		mo2 = null;
		mo3 = null;
		
		kartaGuztiak = null;
		l1 = null;
		l2 = null;
	}
	@Test
	public void testListaKartak() 
	{
		assertNotNull(kartaGuztiak);
		assertNotNull(l1);
		assertNotNull(l2);
	}
	@Test
	public void testLapurtuKarta() 
	{
		l1.setLista(kartaGuztiak.get40Karta());
		int luzeera = l1.getLuzeera();
		Karta k1 = l1.getPosizioHonetakoKarta(1);
		Karta kartaLapurtua = l1.lapurtuKarta();
		
		assertEquals (k1, kartaLapurtua);
		assertNotSame(luzeera, l1.getLuzeera());
	}
	@Test
	public void testGetAukeratzekoKartaPosibleak() 
	{
		l1.gehituKarta(mo1);
		l1.gehituKarta(mo2);
		l1.gehituKarta(mo3);
		l1.gehituKarta(moDiana);
		
		assertSame (l1.getAukeratzekoKartaPosibleak(1).getLuzeera(), 1);
		assertSame (l1.getAukeratzekoKartaPosibleak(2).getLuzeera(), 2);
		assertSame (l1.getAukeratzekoKartaPosibleak(3).getLuzeera(), 3);
		assertSame (l1.getAukeratzekoKartaPosibleak(4).getLuzeera(), 4);
	}

	@Test
	public void testGetErasoDezakeetenKartak() 
	{
		l1.gehituKarta(mo2);
		l1.gehituKarta(mo3);
		l1.gehituKarta(moDiana);
		
		//ez dago eraso dezakeen kartarik
		assertSame (l1.getErasoDezakeetenKartak(), null);
		
		//mo1 eraso dezakee	
		l1.gehituKarta(mo1);
		assertSame (l1.getErasoDezakeetenKartak().getLuzeera(), 1);
	}
	@Test
	public void testKonprobatuEaKartarikHildaDagoen(){
		l1.gehituKarta(mo1);
		l1.gehituKarta(mo2);
		l1.gehituKarta(mo3);
		l1.gehituKarta(moDiana);
		
		//ez dago kartarik hilda
		int luzeera = l1.getLuzeera();
		l1.konprobatuEaKartarikHildaDagoen();
		assertEquals (luzeera, l1.getLuzeera());
		
		//kartarik hilda daude
		mo1.setBizitza(-10);
		l1.konprobatuEaKartarikHildaDagoen();
		assertNotEquals (luzeera, l1.getLuzeera());
	}
	@Test
	public void testGehituKarta(){
		int luz = l1.getLuzeera();
		l1.gehituKarta(mo1);
		assertNotSame (l1.getLuzeera(), luz);
		
		luz = l1.getLuzeera();
		l1.gehituKarta(mo2);
		assertNotSame (l1.getLuzeera(), luz);
		
	}
	@Test
	public void testKenduKarta(){
		l1.gehituKarta(mo1);
		l1.gehituKarta(mo2);
		l1.gehituKarta(mo3);
	
		int luzeera = l1.getLuzeera();
		l1.kenduKarta(mo1);
		assertNotSame (l1.getLuzeera(), luzeera);
		
		luzeera = l1.getLuzeera();
		l1.kenduKarta(mo2);
		assertNotSame (l1.getLuzeera(), luzeera);
		
		luzeera = l1.getLuzeera();
		l1.kenduKarta(mo3);
		assertNotSame (l1.getLuzeera(), luzeera);
	}
	@Test
	public void testGetPosizioHonetakoKarta() {
		l1.gehituKarta(mo3);
		l1.gehituKarta(mo2);
		l1.gehituKarta(mo1);	
		assertEquals (mo3, l1.getPosizioHonetakoKarta(1));
		assertEquals (mo2, l1.getPosizioHonetakoKarta(2));
		assertEquals (mo1, l1.getPosizioHonetakoKarta(3));
	}
	@Test
	public void testGetDianaDutenKartenLista() {
		l1.gehituKarta(mo1);
		l1.gehituKarta(mo2);
		l1.gehituKarta(mo3);
		//ez dago diana duen kartarik
		assertNull (l1.getDianaDutenKartenLista()); 	
		//badago
		l1.gehituKarta(moDiana);
		moDiana.setDiana();
		assertEquals (l1.getDianaDutenKartenLista().getLuzeera(), 1); 
	}
	@Test
	public void testGetLuzeera() {
		//ez dauka elementurik
		assertEquals (l1.getLuzeera(), 0);
		//baditu
		int luzeera = l1.getLuzeera();
		l1.gehituKarta(mo1);
		l1.gehituKarta(mo2);
		l1.gehituKarta(mo3);
		assertNotSame (luzeera, l1.getLuzeera());
	}
	@Test
	public void testGet40Karta() {
		assertSame (l1.getLuzeera(), 0);
		l1.setLista(kartaGuztiak.get40Karta());
		assertSame (l1.getLuzeera(), kartaGuztiak.getLuzeera());
		Iterator <Karta> itrGuztiak = l1.getIteradorea();
		Iterator <Karta> itrl1 = kartaGuztiak.getIteradorea();
		boolean salataria = true;
		while (itrl1.hasNext() && itrGuztiak.hasNext() && salataria)
		{
			Karta Karta1 = itrl1.next();
			Karta Karta2 = itrGuztiak.next();
			if (Karta1 != Karta2)	
			{
				salataria = false;
			}
		}
		assertFalse (salataria);
	}
}

