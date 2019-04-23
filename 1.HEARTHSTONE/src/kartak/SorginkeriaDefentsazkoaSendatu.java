package kartak;

public class SorginkeriaDefentsazkoaSendatu extends SorginkeriaDefentsazkoa 
{
	//atributoak
	private int sendatuKop;

	//eraikitzailea
	public SorginkeriaDefentsazkoaSendatu(int pIdKarta, String pIzena, String pDeskribapena,int pBalioa, int pSendatuKop) 
	{
		super (pIdKarta, pIzena, pDeskribapena,pBalioa);
		this.sendatuKop = pSendatuKop;
	}
	
	//gainontzeko metodoak
	public void jokatuKarta ()
	{
		//TODO
	}
	public void egikarituSorginkeria() 
	{
		//TODO
	}
	public void emanDefentsa (Karta pKarta)
	{
		//TODO
	}
	public void imprimatu()
	{
		//TODO
	}
}
