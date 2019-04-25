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
		// TODO Auto-generated constructor stub
	}
	
	//gainontzeko metodoak
	public void jokatuKarta()
	{
		Karta nireKarta = Teklatua.getNireTeklatua().irakurriAukera("Zure zelaiko karten artean, zeini eman nahi diziozu "+this.eskudoKop+" eskudo punto?", 
																		Partida.getNirePartida().getUnekoJokalaria().getNireZelaikoKartak());
		
	}
	public void emanDefentsa (Karta pKarta)
	{
		//TODO
	}
}
