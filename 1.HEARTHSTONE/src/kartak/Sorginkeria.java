package kartak;

public abstract class Sorginkeria extends Karta
{
	//atributoak
	
	//eraikitzailea
	public Sorginkeria(int pIdKarta, String pIzena, String pDeskribapena, int pBalioa) 
	{
		super(pIdKarta, pIzena, pDeskribapena, pBalioa, true);
	}
	
	//gainontzeko metodoak
	public boolean edonoriErasoDiezaioke (ListaKartak pLista)
	{
		boolean emaitza = false; 
		return emaitza;
	}
	public abstract void imprimatu ();
	public void egoeraEguneratu ()
	{
		super.setErasoDezakeen(true);
	}
}
