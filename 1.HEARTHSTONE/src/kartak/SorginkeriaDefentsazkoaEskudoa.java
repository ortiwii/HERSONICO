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
		Karta nireKarta = Teklatua.getNireTeklatua().irakurriAukera("Zure zelaiko karten artean, zeini eman nahi diziozu "+this.eskudoKop+" eskudo punto?", 
																		Partida.getNirePartida().getUnekoJokalaria().getNireZelaikoKartak());
		this.emanDefentsa(nireKarta);
	}
	public void emanDefentsa (Karta pKarta)
	{
		if (pKarta != null)
		{		
			((Morroia)pKarta).kartaHoniErasotu(eskudoKop*(-1));
			pKarta.imprimatu();
		}
		else
		{
			System.out.println("Ez diozu inori emango defentsa");
		}
		
	}
}
