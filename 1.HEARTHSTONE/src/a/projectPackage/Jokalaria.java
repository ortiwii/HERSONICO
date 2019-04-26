	package a.projectPackage;

import java.util.Iterator;

import kartak.Heroia;
import kartak.Karta;
import kartak.ListaKartak;
import kartak.Morroia;
import kartak.Sorginkeria;

//viva er beti
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
	}
	private void gemenEguneraketa ()
	{
//		if (Partida.getNirePartida().getUnekoTxanda() < 9)
//		{
//			this.gemak = Partida.getNirePartida().getUnekoTxanda()+1; 
//		}
//		else
//		{
//			this.gemak = 10;
//		}
		// Esto estaba mal. Porque las txandas suben de 0 a 19 y si estás en la txanda 4 que es la segunda del jugador2, tienes 4
		// gemas acumuladas cuando deberías tener 2 si no has gastado.


		/* Como las txandak suben de uno en uno, empezando por el 0, se me ha ocurrido hacer lo siguiente para arreglar el fallo
		de que las gemas suban mal. Los turnos suben desde el turno 0 hasta el turno 20 (Porque cada jugador tiene 10 turnos y 
		empieza a contar desde 0)*/
		
		if(this.gemak<10)
		{
			this.gemak++;
		}
	}
	public void kartenEgoeraEguneratu ()
	{
		this.zelaikoKartak.kartenEgoeraEguneratu();
	}
	public void konprobatuEaKartarikHildaDagoen ()
	{
		this.zelaikoKartak.konprobatuEaKartarikHildaDagoen();
	}
	public void jokatuTxanda() 
	{
		this.zelaikoKartak.kartenEgoeraEguneratu();
		this.gemenEguneraketa();
		
		System.out.println("______________________________________________________________________________________________");
		System.out.println(this.izena+"-ren txanda:");
		System.out.println(this.heroia.getBizitza()+" Bizitza du Heroiak, eta "+this.gemak+" gema dituzu");
		//Lehenik eta behin karta bat lapurtuko dugu
		this.lapurtu();
		
		//Eskuko Kartak inprimatu
		System.out.println("Zure eskuko kartak");
		this.eskukoKartak.inprimatuLista();

		//Gemen arabera aukeratu daitekeen bitartean, kartak aukeratu
		boolean salataria = true;
		ListaKartak aukeratuDaitezkeenKartak = new ListaKartak ();
		while (this.aukeratuAhalDu() && salataria && Partida.getNirePartida().irabazia() == null)
		{
			System.out.println("");
			aukeratuDaitezkeenKartak = this.eskukoKartak.getAukeratzekoKartaPosibleak (this.gemak);
			Karta aukeratutakoKarta = Teklatua.getNireTeklatua().irakurriAukera("Zure eskuko karten artean, bat aukeratu ("+this.gemak+" gema) :", aukeratuDaitezkeenKartak);
			//Baldin pasa duen
			if (aukeratutakoKarta == null)
			{
				salataria = false;
			}
			else
			{
				aukeratutakoKarta.setZelairaAteratakoTxanda();
				this.zelaianJarri(aukeratutakoKarta);
				this.konprobatuEaKartarikHildaDagoen();
			}
		}
		
		//ondoren karten egoera eguneratu beharko dugu, batzuek eraso dezakeetelako, eta beste batzuk ez, eta baten bat hilda egon daitekeelako
		Partida.getNirePartida().kartenEgoeraEguneratu();
		
		//Amaitzeko, eraso dezakeen bitartean eraso prozesua jarraituko du
		salataria = true;
		ListaKartak zelairaAteraDaitezkeenKartak = new ListaKartak ();
		while (this.erasoDezake() && Partida.getNirePartida().irabazia() == null && salataria)
		{
			zelairaAteraDaitezkeenKartak = this.zelaikoKartak.getErasoDezakeetenKartak();
			Karta erasotzekoKarta = Teklatua.getNireTeklatua().irakurriAukera("Zure zelaiko karten artean, bat aukeratu:", zelairaAteraDaitezkeenKartak);
			if (erasotzekoKarta == null)
			{
				//Baldin pasa duen
				salataria = false;
			}
			else
			{
				erasotzekoKarta.jokatuKarta();
				erasotzekoKarta.setErasoDezakeen(false);
				//konprobatu ea kartaren bat hilda dagoen
				this.konprobatuEaKartarikHildaDagoen();
			}
		}
		Partida.getNirePartida().kartenEgoeraEguneratu();
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
			this.eskukoKartak.kenduKarta(pKarta);
			pKarta.jokatuKarta();
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
	public void imprimatu()
	{
		//TODO
	}
	private boolean erasoDezake()
	{
		boolean emaitza = true;
		//baldin partida irabazia ez dagoen
		if (Partida.getNirePartida().irabazia() != null)
		{
			emaitza = false;
		}
		//baldin kartak gelditzen zaizkion zelaian
		if (this.zelaikoKartak.getLuzeera() == 0)
		{
			emaitza = false;
		}
		//Baldin erasorik egin ezin duten
		if (!this.zelaikoKartak.erasoDezakete())
		{
			emaitza = false;
		}
		return emaitza;
	}
	public boolean irabazia ()
	{
		boolean emaitza = false;
		if (this.heroia.getBizitza() <= 0)
		{
			emaitza =  true;
		}
		return emaitza;
	}
	public boolean txandaBukatu()
	{
		//Baldin !aukeratuAhalDu()
		//Baldin Pasatu Duen
		//Baldin Heroia hil
		//TODO
		boolean emaitza = false;
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
