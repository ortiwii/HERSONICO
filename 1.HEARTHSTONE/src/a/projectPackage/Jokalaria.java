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
		this.gemak=1;
		
		this.hasieratuJokalaria();
	}
	
	//gainontzeko metodoak
	public void hasieratuJokalaria ()
	{
		this.lapurtzekoKartak = Partida.kartaGuztiak.get40Karta();
	}
	public void jokatuTxanda(ListaKartak listaEtsaia) 
	{
		//Lehenik eta behin karta bat lapurtuko dugu
		this.lapurtu();

		//Gemen arabera aukeratu daitekeen bitartean, kartak aukeratu
		boolean salataria = true;
		ListaKartak aukeratuDaitezkeenKartak = new ListaKartak ();
		while (this.aukeratuAhalDu() && salataria && !Partida.getNirePartida().irabazia())
		{
			System.out.println("");
			aukeratuDaitezkeenKartak = this.eskukoKartak.getAukeratzekoKartaPosibleak (this.gemak);
			Karta aukeratutakoKarta = Teklatua.getNireTeklatua().irakurriAukera("Zure eskuko karten artean, bat aukeratu:", aukeratuDaitezkeenKartak);
			//Baldin pasa duen
			if (aukeratutakoKarta == null)
			{
				salataria = false;
			}
			else
			{
				this.zelaianJarri(aukeratutakoKarta);
			}
		}
		
		//ondoren karten egoera eguneratu beharko dugu, batzuek eraso dezakeetelako, eta beste batzuk ez
		this.zelaikoKartak.kartenEgoeraEguneratu();
		
		//Amaitzeko, eraso dezakeen bitartean eraso prozesua jarraituko du
		salataria = true;
		ListaKartak zelairaAteraDaitezkeenKartak = new ListaKartak ();
		while (this.erasoDezake() && !Partida.getNirePartida().irabazia() && salataria)
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
				//konprobatu ea kartaren bat hilda dagoen
				this.zelaikoKartak.konprobatuEaKartarikHildaDagoen();
			}
		}
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
		if (Partida.getNirePartida().irabazia())
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
		if (Partida.getNirePartida().irabazia())
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
}
