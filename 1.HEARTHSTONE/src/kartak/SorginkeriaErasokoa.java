package kartak;

import a.projectPackage.*;

public class SorginkeriaErasokoa extends Sorginkeria 
{
	//atributoak
	private int mina;

	//eraikitzailea 
	public SorginkeriaErasokoa(int pIdKarta, String pIzena, String pDeskribapena,int pBalioa, int pMina) 
	{
		super (pIdKarta, pIzena, pDeskribapena,pBalioa);
		this.mina = pMina;
	}

	//gainontzeko metodoak
	public void jokatuKarta()
	{
		String mezua = "	-> "+this.getIzena()+"-k, "+Partida.getNirePartida().getJokalariEtsaia().getIzena()+"-en zelaiko karten artean nori "+this.mina+" puntorekin erasotuko dion esan:";
		Karta etsaiarenKarta = Teklatua.getNireTeklatua().irakurriEtsaiarenKarta(mezua);
		if (etsaiarenKarta != null)
		{
			if (etsaiarenKarta instanceof Morroia)
			{
				((Morroia)etsaiarenKarta).kartaHoniErasotu(mina);
				if (((Morroia) etsaiarenKarta).getBizitza() <= 0)
				{
					System.out.println("	--> "+ ((Morroia)etsaiarenKarta).getIzena()+" HILDA !!!");
					Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak().kenduKarta(etsaiarenKarta);
				}
				else
				{
					System.out.println("		- "+Partida.getNirePartida().getJokalariEtsaia().getIzena()+"-ren "+etsaiarenKarta.getIzena()+" "+((Morroia)etsaiarenKarta).getBizitza()+" bizitza puntu ditu");
				}
			}
			Partida.getNirePartida().getUnekoJokalaria().setGemak(Partida.getNirePartida().getUnekoJokalaria().getGemak() - super.balioa);
		}
		else
		{
			if (Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak().getDianaDutenKartenLista() != null)
			{
				System.out.println("		- Ez diozu inori erasotuko");
			}
			else
			{
				Partida.getNirePartida().getHeroiEtsaia().honiErasoEgin(mina);
				if (Partida.getNirePartida().getHeroiEtsaia().getBizitza() <= 0)
				{
					System.out.println("		- HEROIA-ri erasotu diozu, eta HIL EGIN DA !!");
				}
				else
				{
					System.out.print("		- "+Partida.getNirePartida().getJokalariEtsaia().getIzena()+"-ren Heroiak, orain "+Partida.getNirePartida().getHeroiEtsaia().getBizitza()+" bizitza punto ditu");
				}
				Partida.getNirePartida().getUnekoJokalaria().setGemak(Partida.getNirePartida().getUnekoJokalaria().getGemak() - super.balioa);
			}
		}
		System.out.println("");
	}
	public void imprimatu ()
	{
		System.out.println("Sorginkeria: "+super.getIzena()+", "+this.mina+" bizitza punto kentzen dizkio karta etsai bati ("+this.balioa+" gema)");
	}
	public Karta kopiaBatEgin ()
	{
		SorginkeriaErasokoa emaitza = new SorginkeriaErasokoa (this.getId(), this.izena, this.getDeskribapena(), this.balioa, this.mina);
		return emaitza;
	}
	
	public int getMina()
	{
		return this.mina;
	}
}