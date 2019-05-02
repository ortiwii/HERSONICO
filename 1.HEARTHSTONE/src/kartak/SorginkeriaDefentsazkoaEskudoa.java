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
			if (nireKarta != null)
			{
				this.emanDefentsa(nireKarta);
				
				if (((Morroia)nireKarta).getBizitza() > 0)
				{
					System.out.println("		- "+Partida.getNirePartida().getUnekoJokalaria().getIzena()+"-ren "+nireKarta.getIzena()+" "+((Morroia)nireKarta).getBizitza()+" bizitza puntu ditu");
				}
			}
			else
			{
				System.out.println("		- Ez diozu inori eskudoa emango");
			}
		}
	}
	public void emanDefentsa (Karta pKarta)
	{
		if (pKarta != null)
		{		
			((Morroia)pKarta).kartaHoniErasotu(eskudoKop*(-1));
			Partida.getNirePartida().getUnekoJokalaria().setGemak(Partida.getNirePartida().getUnekoJokalaria().getGemak() - super.balioa);
		}
		else
		{
			System.out.println("	Ez diozu inori emango defentsa");
		}
		
	}
	public Karta kopiaBatEgin ()
	{
		SorginkeriaDefentsazkoaEskudoa emaitza = new SorginkeriaDefentsazkoaEskudoa(this.getId(), this.izena, this.getDeskribapena(), this.balioa, this.eskudoKop);
		return emaitza;
	}
	public void imprimatu ()
	{
		System.out.println("Sorginkeria: "+super.getIzena()+", "+this.eskudoKop+" eskudo punto ematen ditu ("+this.balioa+" gema)");
	}
}
