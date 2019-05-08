package kartak;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import a.projectPackage.Partida;

public class SorginkeriaDefentsazkoaSendatuProbak 
{
	Morroia mo1, mo2, mo3;
	SorginkeriaDefentsazkoa s1,s2;

	@Before
	public void setUp() throws Exception 
	{
		//morrroiak
		mo1 = new Morroia (0000, "Julen", "Lagun ona", 1, 4, 2, null);
		mo2 = new Morroia (0001, "Julen", "Lagun ona", 2, 4, 2, null);
		mo3 = new Morroia (0002, "Janire", "Kafea gustatzen zaio", 3, 1, 1, null);
		//sorginkeriak (int pIdKarta, String pIzena, String pDeskribapena,int pBalioa, int pSendatuKop)
		s1 = new SorginkeriaDefentsazkoaSendatu (1000, "cura 1", "cura 1", 1,1);
		s2 = new SorginkeriaDefentsazkoaSendatu (2000, "cura 2", "cura 2", 2,2);
	}

	@After
	public void tearDown() throws Exception 
	{
		mo1 = null;
		mo2 = null;
		mo3 = null;
		s1 = null;
		s2 = null;
	}
	@Test
	public void testSorginkeriaDefentsazkoaSendatu() 
	{
		assertNotNull (mo1);
		assertNotNull (mo2);
		assertNotNull (mo3);
		
		assertNotNull (s1);
		assertNotNull (s2);
	}
}
