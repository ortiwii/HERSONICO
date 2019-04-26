package a.projectPackage;

import java.util.Iterator;

import kartak.Heroia;
import kartak.*;

public class Jokalaria 
{
	//atributoak
	private String izena;
		//ListaKartak
	private ListaKartak lapurtzekoKartak;
	private ListaKartak zelaikoKartak;
	private ListaKartak eskukoKartak;
		//Gainontzekoak
	private Heroia heroia;
	private int gemak;
	
	//eraikitzailea
	public Jokalaria (String pIzena) 
	{
		this.izena = pIzena;
		//ListaKartak
		this.zelaikoKartak=new ListaKartak();
		this.eskukoKartak=new ListaKartak();
		this.lapurtzekoKartak = new ListaKartak();
		//Gainontzekoak
		this.heroia=new Heroia();
		this.gemak=0;
		
		this.hasieratuJokalaria();
	}
	
	//gainontzeko metodoak
	public void hasieratuJokalaria ()
	{
		this.lapurtzekoKartak = Partida.kartaGuztiak.get40Karta();
		this.lapurtu();
		this.lapurtu();
	}
	private void gemenEguneraketa ()
	{
		if (Partida.getNirePartida().getUnekoTxanda()/2 < 9)

		
		if(this.gemak<10)
		{
			this.gemak = Partida.getNirePartida().getUnekoTxanda()/2+1;
		}
		else
		{
			this.gemak = 10;
		}
	}
	public void kartenEgoeraEguneratu ()
	{
		this.zelaikoKartak.kartenEgoeraEguneratu();
	}
	public void konprobatuEaKartarikHildaDagoen ()
	{
		this.zelaikoKartak.konprobatuEaKartarikHildaDagoen();
		Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak().konprobatuEaKartarikHildaDagoen();
	}
	public void jokatuTxanda() 
	{
		this.zelaikoKartak.kartenEgoeraEguneratu();
		this.gemenEguneraketa();
		
		//Lehen mezua agertzeko
		String lehenMezua = "|	 "+this.izena+"-ren TXANDA:     "+this.heroia.getBizitza()+" Bizitza du Heroiak, eta "+this.gemak+" gema dituzu  |";
		System.out.print("      ");Teklatua.getNireTeklatua().imprimatuX_(lehenMezua.lastIndexOf("|"));System.out.println("");
		System.out.print("     ");System.out.println(lehenMezua);
		System.out.print("     |");Teklatua.getNireTeklatua().imprimatuX_(lehenMezua.length()-1);System.out.println("|");
		System.out.println();

		
		//Lehenik eta behin karta bat lapurtuko dugu
		this.lapurtu();
		
		//Eskuko eta zelaiko Kartak inprimatu
		System.out.println("    Hauek dira zure eskuko kartak:");
		this.eskukoKartak.inprimatuLista();
		System.out.println("");
		System.out.println("    Eta hauek zure zelaiko kartak:");
		this.zelaikoKartak.inprimatuLista();

		System.out.println("    ------------------------------------------------------------------------------------------");
		
		//Gemen arabera aukeratu daitekeen bitartean, kartak aukeratu
		System.out.println("  + Hautatze Prozesua:\n");
		boolean salataria = true;
		ListaKartak aukeratuDaitezkeenKartak = new ListaKartak ();
		while (this.aukeratuAhalDu() && salataria && Partida.getNirePartida().irabazia() == null)
		{
			aukeratuDaitezkeenKartak = this.eskukoKartak.getAukeratzekoKartaPosibleak (this.gemak);
			Karta aukeratutakoKarta = Teklatua.getNireTeklatua().irakurriAukera("	-> Zure eskuko karten artean, zelaira aterako duzun bat aukeratu ("+this.gemak+" gema dituzu):", aukeratuDaitezkeenKartak);
			//Baldin pasa duen
			if (aukeratutakoKarta == null)
			{
				System.out.println("\n  + Hautatze prozesua amaituta");
				salataria = false;
			}
			else
			{
				System.out.println("	"+aukeratutakoKarta.getIzena()+" aukeratuta, eta zelaian jarrita");
				aukeratutakoKarta.setZelairaAteratakoTxanda();
				this.zelaianJarri(aukeratutakoKarta);
				this.konprobatuEaKartarikHildaDagoen();
			}
			Teklatua.getNireTeklatua().itxaronEnterArte();
		}
		
		//ondoren karten egoera eguneratu beharko dugu, batzuek eraso dezakeetelako, eta beste batzuk ez, eta baten bat hilda egon daitekeelako
		Partida.getNirePartida().kartenEgoeraEguneratu();
		Partida.getNirePartida().jokatuTrebetasunak();
		
		//Amaitzeko, eraso dezakeen bitartean eraso prozesua jarraituko du
		
		salataria = true;
		ListaKartak zelairaAteraDaitezkeenKartak = new ListaKartak ();
		System.out.println("    ------------------------------------------------------------------------------------------");
		System.out.println("  + Eraso Prozesua:\n");
		Partida.getNirePartida().kartenEgoeraEguneratu();
		while (Partida.getNirePartida().irabazia() == null && salataria)
		{
			zelairaAteraDaitezkeenKartak = this.zelaikoKartak.getErasoDezakeetenKartak();
			Karta erasotzekoKarta = Teklatua.getNireTeklatua().irakurriAukera("	-> Zure zelaiko karten artean, eraso dezakeen bat atera zelaira:", zelairaAteraDaitezkeenKartak);
			if (erasotzekoKarta == null)
			{
				//Baldin pasa duen
				salataria = false;
				System.out.println("\n  + Eraso prozesua amaituta");
			}
			else
			{
				erasotzekoKarta.jokatuKarta();
				erasotzekoKarta.setErasoDezakeen(false);
				//konprobatu ea kartaren bat hilda dagoen
				this.konprobatuEaKartarikHildaDagoen();
			}
			Teklatua.getNireTeklatua().itxaronEnterArte();
		}
		System.out.println("    ------------------------------------------------------------------------------------------");
		Partida.getNirePartida().kartenEgoeraEguneratu();
		Partida.getNirePartida().jokatuTrebetasunak();
	}
	private void lapurtu () 
	{
		Karta kartaLapurtua = this.lapurtzekoKartak.lapurtuKarta();
		this.lapurtzekoKartak.kenduKarta(kartaLapurtua);
		this.eskukoKartak.gehituKarta(kartaLapurtua);
	}
	private void zelaianJarri (Karta pKarta)
	{
		if (pKarta instanceof Morroia)
		{
			this.eskukoKartak.kenduKarta(pKarta);
			this.zelaikoKartak.gehituKarta(pKarta);
			this.gemak = this.gemak - pKarta.getBalioa();
		}
		else if (pKarta instanceof Sorginkeria)
		{
			if (pKarta instanceof SorginkeriaDefentsazkoa)
			{
				if (this.zelaikoKartak.getLuzeera() != 0)
				{
					this.eskukoKartak.kenduKarta(pKarta);
					pKarta.jokatuKarta();
				}
				else 
				{
					System.out.println("	-> Ez daude kartarik zelaian, beraz ezin diezaiokezu inori defentsa eman");
					pKarta.setErasoDezakeen(false);
				}
			}
			else
			{
				this.eskukoKartak.kenduKarta(pKarta);
				pKarta.jokatuKarta();
			}
		}
	}
	private boolean aukeratuAhalDu()
	{
		//gemekin aukeratu ahal duen adierazi
		boolean emaitza = false;
		Iterator <Karta> itr = this.eskukoKartak.getIteradorea();
		Karta egungoKarta;
		while (itr.hasNext() && !emaitza)
		{
			egungoKarta = itr.next();
			if (egungoKarta.getBalioa() <= this.gemak)
			{
				emaitza = true;
			}
		}
		//aukeratzeko kartak gelditzen zaizkion adierazi
		if (this.eskukoKartak.getLuzeera() != 0)
		{
			emaitza = true;
		}
		//partida irabazita dagoen adierazi		
		if (Partida.getNirePartida().irabazia() != null)
		{
			emaitza = false;
		}
		return emaitza;
	}
//	private boolean erasoDezake()
//	{
//		boolean emaitza = true;
//		//baldin partida irabazia ez dagoen
//		if (Partida.getNirePartida().irabazia() != null)
//		{
//			emaitza = false;
//		}
//		//baldin kartak gelditzen zaizkion zelaian
//		if (this.zelaikoKartak.getLuzeera() == 0)
//		{
//			emaitza = false;
//		}
//		//Baldin erasorik egin ezin duten
//		if (!this.zelaikoKartak.erasoDezakete())
//		{
//			emaitza = false;
//		}
//		return emaitza;
//	}
	public boolean irabazia ()
	{
		boolean emaitza = false;
		if (this.heroia.getBizitza() <= 0)
		{
			emaitza =  true;
		}
		return emaitza;
	}
		//setters
		public void setGemak (int pGemak)
		{
			this.gemak = pGemak;
		}
		public void setLapurtzekoKartak (ListaKartak pLista)
		{
			this.lapurtzekoKartak = pLista;
		}
		//getters
		public Heroia getHeroia ()
		{
			return this.heroia;
		}
		public ListaKartak getNireZelaikoKartak ()
		{
			return this.zelaikoKartak;
		}
		public int getGemak ()
		{
			return this.gemak;
		}
		public ListaKartak getLapurtzekoKartak ()
		{
			return this.lapurtzekoKartak;
		}
		public String getIzena ()
		{
			return this.izena;
		}
}
