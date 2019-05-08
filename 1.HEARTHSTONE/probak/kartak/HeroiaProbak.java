package kartak;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HeroiaProbak 
{
	Heroia h1, h2;
	
	@Before
	public void setUp() throws Exception 
	{
		h1 = new Heroia ();
		h2 = new Heroia ();
	}

	@After
	public void tearDown() throws Exception 
	{
		h1 = null;
		h2 = null;
	}
	@Test
	public void testHeroia() 
	{
		assertNotNull (h1);
		assertNotNull (h2);
	}
	@Test
	public void testGetBizitza() 
	{
		assertSame (h1.getBizitza(), 30);
		h1.setBizitza(10);
		assertSame (h1.getBizitza(), 10);
	}
	@Test
	public void testHoniErasoEgin() 
	{
		assertSame (h1.getBizitza(), 30);
		h1.honiErasoEgin(10);
		assertSame (h1.getBizitza(), 20);
		h1.honiErasoEgin(10);
		assertSame (h1.getBizitza(), 10);
	}
	@Test
	public void testBizirikDago() 
	{
		//bizirik
		assertSame (h1.getBizitza(), 30);
		assertTrue (h1.bizirikDago());
		//hilda
		h1.setBizitza(0);
		assertFalse (h1.bizirikDago());
	}
}
