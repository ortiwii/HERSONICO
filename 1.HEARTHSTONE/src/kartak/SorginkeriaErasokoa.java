package kartak;

import a.projectPackage.Partida;
import a.projectPackage.Teklatua;

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
					System.out.print("	");etsaiarenKarta.imprimatu();
				}
			}
		}
		else
		{
			Partida.getNirePartida().getHeroiEtsaia().puntoBatKendu();
			System.out.print("	 --> "+Partida.getNirePartida().getJokalariEtsaia().getIzena()+"-ren Heroiak, orain "+Partida.getNirePartida().getHeroiEtsaia().getBizitza()+" bizitza punto ditu");
		}
		Partida.getNirePartida().getUnekoJokalaria().setGemak(Partida.getNirePartida().getUnekoJokalaria().getGemak() - super.balioa);
	}
	public void imprimatu ()
	{
		System.out.println("Sorginkeria: "+super.getIzena()+", "+this.mina+" bizitza punto kentzen dizkio karta etsai bati ("+this.balioa+" gema)");
	}
}
