package a.projectPackage;

import kartak.Heroia;
import kartak.Karta;
import kartak.ListaKartak;
import kartak.Morroia;
import kartak.SorginkeriaErasokoa;
import trebetasunak.Trebetasuna;
import trebetasunak.TrebetasunaDiana;
import trebetasunak.TrebetasunaErasoJarraia;
import trebetasunak.TrebetasunaVendetta;

public class Partida 
{
	//atributoak
	private static Partida nirePartida = null;
	private Jokalaria[] listaJokalariak;
	private static int unekoTxanda;
	private static ListaKartak kartaGuztiak=null;
	
	//eraikitzailea
	private Partida() 
	{
		this.listaJokalariak = new Jokalaria[2];
		Partida.unekoTxanda=0;
	}
	
	//gainontzeko metodoak
	public static Partida getNirePartida()
	{
		if (nirePartida == null)
		{
			nirePartida = new Partida();
		}
		return nirePartida;
	}
	private void kartaGuztiakKargatu ()
	{
		String textoGuztia = Fitxategiak.getFitxategiak().irakurriTxt("bin\\kartak.txt");
		Partida.kartaGuztiak = new ListaKartak ();
		
		//Lehenik eta behin 4 blokeetan banatuko ditugu kartak (Morroiak, Erasoko, Bizitzazko eta Eskudozko Sorginkeriak)
		String [] blokeekaBanatuta = textoGuztia.split("_");
			
			//Lehen blokea morroiak dira
			String[] morroiak = blokeekaBanatuta[0].split(";");
			for (int aux = 0; aux < morroiak.length; aux ++)
			{
				String egungoString = morroiak [aux];
				String [] egungoInfo = egungoString.split("-");
				//int pIdKarta, String pIzena, String pDeskribapena, int pBalioa, int pErasoa, int pBizitza, Trebetasuna pTrebetasuna
				Morroia egungoKarta;
				if (egungoInfo[6] == "Eraso Jarraia")
				{
					egungoKarta = new Morroia (Integer.parseInt(egungoInfo[0]),egungoInfo[1],egungoInfo[2], Integer.parseInt(egungoInfo[3]), 
														Integer.parseInt(egungoInfo[4]), Integer.parseInt(egungoInfo[5]), new TrebetasunaErasoJarraia());
				}
				else if (egungoInfo[6] == "Diana")
				{
					egungoKarta = new Morroia (Integer.parseInt(egungoInfo[0]),egungoInfo[1],egungoInfo[2], Integer.parseInt(egungoInfo[3]), 
							Integer.parseInt(egungoInfo[4]), Integer.parseInt(egungoInfo[5]), new TrebetasunaDiana());
				}
				else if (egungoInfo[6] == "Vendetta")
				{
					egungoKarta = new Morroia (Integer.parseInt(egungoInfo[0]),egungoInfo[1],egungoInfo[2], Integer.parseInt(egungoInfo[3]), 
							Integer.parseInt(egungoInfo[4]), Integer.parseInt(egungoInfo[5]), new TrebetasunaVendetta());
				}
				else
				{
					egungoKarta = new Morroia (Integer.parseInt(egungoInfo[0]),egungoInfo[1],egungoInfo[2], Integer.parseInt(egungoInfo[3]), 
							Integer.parseInt(egungoInfo[4]), Integer.parseInt(egungoInfo[5]), null);
				}
				Partida.kartaGuztiak.gehituKarta(egungoKarta);
			}
			
			//Bigarren blokea sorginkeriak dira, hain zuzen erasozkoak
			String [] erasozkoSorginkeriak = blokeekaBanatuta[1].split(";");
				//int pIdKarta, String pIzena, String pDeskribapena,int pBalioa, int pMina
			for (int aux = 0; aux < erasozkoSorginkeriak.length; aux ++)
			{
				SorginkeriaErasokoa egungoKarta = new SorginkeriaErasokoa(Integer.parseInt(erasozkoSorginkeriak[0]), erasozkoSorginkeriak[1], erasozkoSorginkeriak[2], 
						Integer.parseInt(erasozkoSorginkeriak[3]), Integer.parseInt(erasozkoSorginkeriak[4]));
				Partida.kartaGuztiak.gehituKarta(egungoKarta);
			}
			
			//Hirugarren blokea sorginkeriak dira, hain zuzen ere Defentsazkoak, Bizitzazkoak
			//TODO
			//Laugarren blokea sorginkeriak dira, hain zuzen ere Defentsazkoak, Eskudokoak
			//TODO
	}
	public void hasieratuPartida ()
	{
		//TODO
		//Jokalariak hasieratu
		for (int i = 1; i<=2; i++)
		{
			String pIzena = Teklatua.getNireTeklatua().Irakurri("Sartu "+i+". jokalariaren izena");
			Jokalaria jok = new Jokalaria (pIzena);
			jok.hasieratuJokalaria();
			this.listaJokalariak[i] = jok;
		}
	}
	public String jokatu() 
	{
		//TODO
		
		return "Jokalari irabazlearen izena";
	}
	public void hurrengoTxanda() 
	{
		//Suma 1 a uneko txanda
		Partida.unekoTxanda = Partida.unekoTxanda + 1;
		//TODO, porque no se si necesita algo mas 
	}
	public boolean irabazia()
	{
		boolean emaitza = false;
		if (this.listaJokalariak[0].irabazia() || this.listaJokalariak[1].irabazia())
		{
			emaitza = true;
		}
		return emaitza;
	}
	
	public void inprimatuPartida() 
	{
		//TODO
	}
		//getters
	public Jokalaria getUnekoJokalaria ()
	{
		if (Partida.unekoTxanda % 2 == 0)
		{
			return this.listaJokalariak[0];
		}
		else
		{
			return this.listaJokalariak[1];
		}
	}
	public Heroia getHeroiEtsaia ()
	{
		//como la unekotxanda si es bikoti (%2=0) es el jugador 1, y si no el jugador 2, 
		// si unekotxanda es bikoti devolvemos el heroe del jugador 2, y si no del 1, es decir,
		// hacemos lo contrario
		if (Partida.unekoTxanda % 2 != 0)
		{
			return this.listaJokalariak[0].getHeroia();
		}
		else
		{
			return this.listaJokalariak[1].getHeroia();
		}
	}
	public Jokalaria getJokalariEtsaia ()
	{
		if (Partida.unekoTxanda % 2 != 0)
		{
			return this.listaJokalariak[0];
		}
		else
		{
			return this.listaJokalariak[1];
		}
	}
	public int getUnekoTxanda ()
	{
		return Partida.unekoTxanda;
	}
}
