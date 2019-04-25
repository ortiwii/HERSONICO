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
	public void imprimatu ()
	{
		System.out.print("Sorginkerria:");
		System.out.println(super.izena+" ("+super.balioa+" gema)");
	}
	public void egoeraEguneratu ()
	{
		super.setErasoDezakeen(true);
	}
}
