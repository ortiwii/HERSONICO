package kartak;

public abstract class SorginkeriaDefentsazkoa extends Sorginkeria 
{
	//atributoak
	
	//eraikitzailea
	public SorginkeriaDefentsazkoa(int pIdKarta, String pIzena, String pDeskribapena, int pBalioa) 
	{
		super(pIdKarta, pIzena, pDeskribapena,pBalioa);
	}
	
	//gainontzeko metodoak
	public abstract void emanDefentsa (Karta pKarta);
}
