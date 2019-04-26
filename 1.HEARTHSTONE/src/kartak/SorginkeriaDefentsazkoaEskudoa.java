package kartak;

import a.projectPackage.Partida;
import a.projectPackage.Teklatua;

public class SorginkeriaDefentsazkoaEskudoa extends SorginkeriaDefentsazkoa 
{
	//atributoak 
	private int eskudoKop;
	
	//eraikitzailea
	public SorginkeriaDefentsazkoaEskudoa(int pIdKarta, String pIzena, String pDeskribapena,int pBalioa, int pEskudoKop) 
	{
		super (pIdKarta, pIzena, pDeskribapena,pBalioa);
		this.eskudoKop = pEskudoKop;
	}
	
	//gainontzeko metodoak
	public void jokatuKarta()
	{
		if (Partida.getNirePartida().getUnekoJokalaria().getNireZelaikoKartak().getLuzeera() != 0)
		{
			Karta nireKarta = Teklatua.getNireTeklatua().irakurriAukera("	-> Zure zelaiko karten artean, zeini eman nahi diziozu "+this.eskudoKop+" eskudo punto?", 
																		Partida.getNirePartida().getUnekoJokalaria().getNireZelaikoKartak());
			this.emanDefentsa(nireKarta);
			
			Partida.getNirePartida().getUnekoJokalaria().setGemak(Partida.getNirePartida().getUnekoJokalaria().getGemak() - super.balioa); 
		}
	}
	public void emanDefentsa (Karta pKarta)
	{
		if (pKarta != null)
		{		
			((Morroia)pKarta).kartaHoniErasotu(eskudoKop*(-1));
			System.out.print("	");pKarta.imprimatu();
		}
		else
		{
			System.out.println("	Ez diozu inori emango defentsa");
		}
		
	}
	public void imprimatu ()
	{
		System.out.println("Sorginkeria: "+super.getIzena()+", "+this.eskudoKop+" eskudo punto ematen ditu ("+this.balioa+" gema)");
	}
}
