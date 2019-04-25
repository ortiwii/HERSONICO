package a.projectPackage;

import kartak.*;
import trebetasunak.*;

public class Partida 
{
	//atributoak
	private static Partida nirePartida = null;
	private Jokalaria[] listaJokalariak;
	private static int unekoTxanda;
	public static ListaKartak kartaGuztiak=null;
	
	//eraikitzailea
	private Partida() 
	{
		this.listaJokalariak = new Jokalaria[2]; // Se crea un array de 2 posiciones, una para cada jugador.
		Partida.unekoTxanda = 0; // El atributo unekoTxanda se inicializa en 0.
	}
	
	//gainontzeko metodoak
	public static synchronized Partida getNirePartida()
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
				String [] egungoInfo = morroiak[aux].split("-");
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
				String [] egungoErasozkoSorginkeria = erasozkoSorginkeriak[aux].split("-");
				SorginkeriaErasokoa egungoKarta = new SorginkeriaErasokoa(Integer.parseInt(egungoErasozkoSorginkeria[0]), egungoErasozkoSorginkeria[1], egungoErasozkoSorginkeria[2], 
						Integer.parseInt(egungoErasozkoSorginkeria[3]), Integer.parseInt(egungoErasozkoSorginkeria[4]));
				Partida.kartaGuztiak.gehituKarta(egungoKarta);
			}
			
			//Hirugarren blokea sorginkeriak dira, hain zuzen ere Defentsazkoak, Bizitzazkoak
			String [] sendatuSorginkeriak = blokeekaBanatuta[2].split(";");
				//int pIdKarta, String pIzena, String pDeskribapena,int pBalioa, int pSendatuKop
			for (int aux = 0; aux < sendatuSorginkeriak.length; aux ++)
			{
				String [] egungoSendatuSorginkeria = sendatuSorginkeriak[aux].split("-");
				SorginkeriaDefentsazkoaSendatu egungoKarta = new SorginkeriaDefentsazkoaSendatu (Integer.parseInt(egungoSendatuSorginkeria[0]),egungoSendatuSorginkeria[1], 
												egungoSendatuSorginkeria[2], Integer.parseInt(egungoSendatuSorginkeria[3]), Integer.parseInt(egungoSendatuSorginkeria[4]));
				Partida.kartaGuztiak.gehituKarta(egungoKarta);
			}
			
			//Laugarren blokea sorginkeriak dira, hain zuzen ere Defentsazkoak, Eskudokoak
			String [] eskudoSorginkeriak = blokeekaBanatuta[3].split(";");
				//int pIdKarta, String pIzena, String pDeskribapena,int pBalioa, int pEskudoKop
			for (int aux = 0; aux < eskudoSorginkeriak.length; aux ++)
			{
				String [] egungoEskudoSorginkeriak = eskudoSorginkeriak[aux].split("-");
				SorginkeriaDefentsazkoaEskudoa egungoKarta = new SorginkeriaDefentsazkoaEskudoa (Integer.parseInt(egungoEskudoSorginkeriak[0]), egungoEskudoSorginkeriak[1],
														egungoEskudoSorginkeriak[2], Integer.parseInt(egungoEskudoSorginkeriak[3]), Integer.parseInt(egungoEskudoSorginkeriak[4]));
				Partida.kartaGuztiak.gehituKarta(egungoKarta);
			}		
	}
	public void hasieratuPartida ()
	{
		System.out.println("*****************************************************************************************************************");
		System.out.println("------------------------------------------------   HEARTHSTONE   ------------------------------------------------");
		System.out.println("*****************************************************************************************************************");
		System.out.println("");
		System.out.println("PARTIDA HASIKO DA :)");
		System.out.println("__________________________________________________________________________________________________________________");
		this.kartaGuztiakKargatu();
		//Jokalariak hasieratu
			String izena1 = Teklatua.getNireTeklatua().Irakurri("Sartu 1. jokalariaren izena:");
			Jokalaria jok1 = new Jokalaria (izena1);
			this.listaJokalariak[0] = jok1;
			
			String izena2 = Teklatua.getNireTeklatua().Irakurri("Sartu 2. jokalariaren izena:");
			Jokalaria jok2 = new Jokalaria (izena2);
			this.listaJokalariak[1] = jok2;
			
			String irabazlea = this.jokatu();
			
			System.out.println("Partida dagoeneko amaitu da, eta irabazlea:");
			System.out.println(irabazlea);
	}
	public String jokatu() 
	{
		while (this.irabazia() == null)
		{
			Jokalaria egungoJokalaria = this.getUnekoJokalaria();
			egungoJokalaria.jokatuTxanda();
			this.inprimatuPartida();
			this.hurrengoTxanda();
		}
		
		return this.irabazia().getIzena();
	}
	public void hurrengoTxanda() 
	{
		Teklatua.getNireTeklatua().itxaronEnterArte();
		//Suma 1 a uneko txanda
		Partida.unekoTxanda = Partida.unekoTxanda + 1;
 
	}
	public Jokalaria irabazia()
	{
		if (this.listaJokalariak[0].irabazia()) 		
		{
			return listaJokalariak[0];
		}
		else if (this.listaJokalariak[1].irabazia())
		{
			return listaJokalariak[1];
		}
		else
		{
			return null;
		}
	}
	
	public void inprimatuPartida() 
	{
		System.out.println(this.listaJokalariak[0].getIzena()+"-ren Zelaiko Kartak:");
		this.listaJokalariak[0].getNireZelaikoKartak().inprimatuLista();
		System.out.println(this.listaJokalariak[1].getIzena()+"-ren Zelaiko Kartak:");
		this.listaJokalariak[1].getNireZelaikoKartak().inprimatuLista();
	}
	public void kartenEgoeraEguneratu ()
	{
		this.listaJokalariak[0].kartenEgoeraEguneratu();
		this.listaJokalariak[0].konprobatuEaKartarikHildaDagoen();
		
		this.listaJokalariak[1].kartenEgoeraEguneratu();
		this.listaJokalariak[1].konprobatuEaKartarikHildaDagoen();
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
