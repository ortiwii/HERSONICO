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
	private void deskripzioaInprimatu ()
	{
		String textoGuztia = Fitxategiak.getFitxategiak().irakurriTxt("bin\\Deskripzioa.txt");
		String [] deskripzioa = textoGuztia.split(";");
		for (int aux = 0; aux < deskripzioa.length; aux++)
		{
			System.out.println(deskripzioa[aux]);
		}
		Teklatua.getNireTeklatua().itxaronEnterArte();
	}
	private void deskripzioaNahiDuenGaldetu ()
	{
		int aukera = Teklatua.getNireTeklatua().baiAlaEzAukera("    Jokoaren deskribapen labur bat nahi duzu ?");
		if (aukera == 1)
		{
			this.deskripzioaInprimatu();
			aukera = Teklatua.getNireTeklatua().baiAlaEzAukera("    Karta guztiak zeintzuk diren jakin nahi duzu ?");
			if (aukera == 1)
			{
				System.out.println("\n     Hauek dira karta guztiak : ");
				
				boolean salataria = false;
				while (!salataria)
				{
					Karta egungoKarta = Teklatua.getNireTeklatua().irakurriAukera("\n    Karta guzti hauetatik deskribapenen bat irakurri nahi duzu ?", Partida.kartaGuztiak);
					if (egungoKarta == null)
					{
						System.out.println("    Ondo.");
						salataria = true;
						
					}
					else
					{
						System.out.println("    -> "+egungoKarta.getIzena()+": "+egungoKarta.getDeskribapena());
						System.out.println(" ");Teklatua.getNireTeklatua().itxaronEnterArte();
					}
				}
			}
		}
		
	}
	public void kartaGuztiakKargatu ()
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
				if (egungoInfo[6].equalsIgnoreCase("Eraso Jarraia"))
				{
					egungoKarta = new Morroia (Integer.parseInt(egungoInfo[0]),egungoInfo[1],egungoInfo[2], Integer.parseInt(egungoInfo[3]), 
														Integer.parseInt(egungoInfo[4]), Integer.parseInt(egungoInfo[5]), new TrebetasunaErasoJarraia());
				}
				else if (egungoInfo[6].equalsIgnoreCase("Diana"))
				{
					egungoKarta = new Morroia (Integer.parseInt(egungoInfo[0]),egungoInfo[1],egungoInfo[2], Integer.parseInt(egungoInfo[3]), 
							Integer.parseInt(egungoInfo[4]), Integer.parseInt(egungoInfo[5]), new TrebetasunaDiana());
				}
				else if (egungoInfo[6].equalsIgnoreCase("Vendetta"))
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
		this.kartaGuztiakKargatu();
		System.out.println("\n\n       			░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
		System.out.println("       			░                                                                                                                       ░");
		System.out.println("       			░       ░      ░  ░░░░░░   ░░░░░░░  ░░░░░░░  ░░░░░░░░░  ░    ░   ░░░░░░░  ░░░░░░░░░  ░░░░░░░  ░░░       ░  ░░░░░░       ░");
		System.out.println("       			░       ░      ░  ░        ░     ░  ░     ░      ░      ░    ░   ░            ░      ░     ░  ░ ░░      ░  ░            ░");
		System.out.println("       			░       ░      ░  ░        ░     ░  ░     ░      ░      ░    ░   ░            ░      ░     ░  ░  ░░     ░  ░            ░");
		System.out.println("       			░       ░      ░  ░        ░     ░  ░     ░      ░      ░    ░   ░            ░      ░     ░  ░   ░░    ░  ░            ░");
		System.out.println("       			░       ░░░░░░░░  ░░░░     ░░░░░░░  ░░░░░░░      ░      ░░░░░░   ░░░░░░░      ░      ░     ░  ░    ░░   ░  ░░░░         ░");
		System.out.println("       			░       ░      ░  ░        ░     ░  ░░           ░      ░    ░         ░      ░      ░     ░  ░     ░░  ░  ░            ░");
		System.out.println("       			░       ░      ░  ░        ░     ░  ░  ░         ░      ░    ░         ░      ░      ░     ░  ░      ░░ ░  ░            ░");
		System.out.println("       			░       ░      ░  ░        ░     ░  ░    ░       ░      ░    ░         ░      ░      ░     ░  ░       ░░░  ░            ░");
		System.out.println("       			░       ░      ░  ░░░░░░   ░     ░  ░      ░     ░      ░    ░   ░░░░░░░      ░      ░░░░░░░  ░        ░░  ░░░░░░       ░");
		System.out.println("       			░                                                                                                                       ░");
		System.out.println("       			░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");	
		System.out.println("");
		//Deskripzioa nahi duen galdetu
		this.deskripzioaNahiDuenGaldetu();
		System.out.println("  __________________");
		System.out.println(" |  JOKOA HASIKO DA |");
		System.out.println(" |__________________|");
		System.out.println("");
		//Jokalariak hasieratu
			String izena1 = Teklatua.getNireTeklatua().Irakurri("	SARTU 1. JOKALARIAREN IZENA:");
			Jokalaria jok1 = new Jokalaria (izena1);
			this.listaJokalariak[0] = jok1;
			
			String izena2 = Teklatua.getNireTeklatua().Irakurri("	SARTU 2. JOKALARIAREN IZENA:");
			Jokalaria jok2 = new Jokalaria (izena2);
			this.listaJokalariak[1] = jok2;
			
			String irabazlea = this.jokatu();
			System.out.println("\n░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
			System.out.println("    	 								"+irabazlea+" da IRABAZLEA !! ☺☺                   ");
			System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
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
		if (this.listaJokalariak[0].galduDuzu()) 		
		{
			return listaJokalariak[1];
		}
		else if (this.listaJokalariak[1].galduDuzu())
		{
			return listaJokalariak[0];
		}
		else
		{
			return null;
		}
	}	
	public void inprimatuPartida() 
	{
		if (this.irabazia() == null)
		{
			System.out.println("    BERAZ, HONELA DOA PARTIDA:");
			for (int i = 0; i <= 1; i++)
			{
				System.out.println("");
				String Mezua = " "+this.listaJokalariak[i].getIzena()+"-ren ZELAIA ";
				System.out.print("	 ");Teklatua.getNireTeklatua().imprimatuX_(Mezua.length());System.out.println("");
				System.out.print("	|");System.out.print(Mezua);System.out.println("| Heroiak "+this.listaJokalariak[i].getHeroiarenBizitza()+" Bizitza");
				System.out.println("");
				this.listaJokalariak[i].getNireZelaikoKartak().inprimatuLista();
			}
			
			System.out.println("\n    ------------------------------------------------------------------------------------------");
			System.out.println("    ------------------------------------------------------------------------------------------");		}
}
	
	public void kartenEgoeraEguneratu ()
	{
		this.listaJokalariak[0].kartenEgoeraEguneratu();
		this.listaJokalariak[0].konprobatuEaKartarikHildaDagoen();
		
		this.listaJokalariak[1].kartenEgoeraEguneratu();
		this.listaJokalariak[1].konprobatuEaKartarikHildaDagoen();
	}
	public void jokatuTrebetasunak ()
	{
		this.listaJokalariak[0].getNireZelaikoKartak().trebetasunakEgikaritu();
		this.listaJokalariak[1].getNireZelaikoKartak().trebetasunakEgikaritu();
	}
		//getters
	//Uneko Jokalaria
	private Jokalaria getUnekoJokalaria ()
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
	public int getUnekoJokalariarenGemak ()
	{
		return this.getUnekoJokalaria().getGemak();
	}
	public String getUnekoJokalariarenIzena ()
	{
		return this.getUnekoJokalaria().getIzena();
	}
	public ListaKartak getUnekoJokalariarenZelaikoKartak ()
	{
		return this.getUnekoJokalaria().getNireZelaikoKartak();
	}
	//Jokalari Etsaia 
	public String getJokalariEtsaiarenIzena ()
	{
		return this.getJokalariEtsaia().getIzena();
	}
	public ListaKartak getJokalariEtsaiarenZelaikoKartak ()
	{
		return this.getJokalariEtsaia().getNireZelaikoKartak();
	}
	private Jokalaria getJokalariEtsaia ()
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
	public void imprimatuHeroiEtsaia ()
	{
		this.getJokalariEtsaia().imprimatuHeroia();
	}
	public void heroiEtsaiariErasotu (int pErasoa)
	{
		this.getJokalariEtsaia().heroiariErasotu(pErasoa);
	}
	public int getHeroiEtsaiarenBizitza ()
	{
		return this.getJokalariEtsaia().getHeroiarenBizitza();
	}
		//Txanda
	public int getUnekoTxanda ()
	{
		return Partida.unekoTxanda;
	}

		//setters
		public void setUnekoJokalariarenGemak (int pGemak)
		{
			this.getUnekoJokalaria().setGemak(pGemak);
		}
		public void setJok (Jokalaria jok, int pos)
		{
			this.listaJokalariak[pos] = jok;
		}
		public void setTxanda (int txanda)
		{
			Partida.unekoTxanda = txanda;
		}
	
		//JOKOA HASIERATZEKO BEHARREZKOA DEN MAIN
	public static void main(String[] args) 
	{
		Partida.getNirePartida().hasieratuPartida();
	}
}
