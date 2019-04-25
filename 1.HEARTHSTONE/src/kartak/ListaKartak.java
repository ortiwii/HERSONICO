package kartak;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class ListaKartak 
{
	//atributoak
	private ArrayList<Karta> lista;
	
	//eraikitzailea
	public ListaKartak () 
	{
		this.lista = new ArrayList<Karta>(); 
	}
	
	//gainontzeko metodoak	
	public Iterator<Karta> getIteradorea()
	{
		return lista.iterator();
	}
	public void barajatu()
	{
		Collections.shuffle(lista);	
	}
	public Karta lapurtuKarta ()
	{
		Iterator <Karta> itr = this.getIteradorea();
		Karta kartaLapurtua = itr.next();
		this.kenduKarta(kartaLapurtua);
		return kartaLapurtua;
	}
	public void kartenEgoeraEguneratu ()
	{
		this.konprobatuEaKartarikHildaDagoen();
		Iterator <Karta> itr = this.getIteradorea();
		Karta egungoKarta;
		while (itr.hasNext())
		{
			egungoKarta = itr.next();
			egungoKarta.egoeraEguneratu();
		}
	}
	public boolean erasoDezakete ()
	{
		Iterator <Karta> itr = this.getIteradorea();
		boolean emaitza = false;
		Karta egungoKarta;
		while (itr.hasNext() && !emaitza)
		{
			egungoKarta = itr.next();
			if (egungoKarta.getErasoDezakeen()) 
			{
				emaitza = true;
			}
			
		}
		return emaitza;
	}
	public ListaKartak getAukeratzekoKartaPosibleak (int pGemak)
	{
		Karta egungoKarta = null;
		ListaKartak aukerak = new ListaKartak ();
		Iterator <Karta> itr = this.getIteradorea();
		while (itr.hasNext())
		{
			egungoKarta = itr.next();
			if (egungoKarta.getBalioa() <= pGemak)
			{
				aukerak.gehituKarta(egungoKarta);
			}
		}
		if (aukerak.getLuzeera() == 0)
		{
			aukerak = null;
		}
		return aukerak;
	}
	public ListaKartak getErasoDezakeetenKartak ()
	{
		Karta egungoKarta = null;
		ListaKartak erasoDezakeetenKartak = new ListaKartak ();
		Iterator <Karta> itr = this.getIteradorea();
		while (itr.hasNext())
		{
			egungoKarta = itr.next();
			if (egungoKarta.getErasoDezakeen())
			{
				erasoDezakeetenKartak.gehituKarta(egungoKarta);
			}
		}
		if (erasoDezakeetenKartak.getLuzeera() == 0)
		{
			erasoDezakeetenKartak = null;
		}
		return erasoDezakeetenKartak;
	}
	public void konprobatuEaKartarikHildaDagoen ()
	{
		ArrayList <Karta> emaitza = new ArrayList <Karta> ();
		if (this != null)
		{
			Iterator <Karta> itr = this.getIteradorea();
			Karta egungoKarta;
			while (itr.hasNext())
			{
				egungoKarta = itr.next();
				if (egungoKarta instanceof Morroia)
				{
					if ( ((Morroia)egungoKarta).getBizitza() <= 0 )
					{
						System.out.println("--> "+ ((Morroia)egungoKarta).getIzena()+" HILDA !!!");
					}
					else
					{
						emaitza.add(egungoKarta);
					}
				}
			}
		}
		this.lista = emaitza;
	}
	public ArrayList<Karta> getLista()
	{
		return this.lista;
	}
	public void gehituKarta(Karta pKarta) 
	{
			this.lista.add(pKarta);
	}
	public void kenduKarta(Karta pKarta) 
	{
		if (this.lista.contains(pKarta))
		{
			this.lista.remove(pKarta);
		}
	}
	public void inprimatuLista() 
	{
		if (this.getLuzeera() != 0)
		{		
			Iterator <Karta> itr = this.getIteradorea();
			Karta egungoKarta;
			while (itr.hasNext())
			{
				egungoKarta = itr.next();
				System.out.print("    * ");
				egungoKarta.imprimatu();
			}
		}
		else
		{
			System.out.println("    - Ez duzu kartarik");
		}
	}
	public Karta getPosizioHonetakoKarta (int pos)
	{
		Iterator <Karta> itr = this.getIteradorea();
		int kont = 0;
		Karta aukeratutakoKarta = null;
		while (itr.hasNext() && kont < pos)
		{
			kont++;
			aukeratutakoKarta = itr.next();			
		}
		return aukeratutakoKarta;
	}
	public ListaKartak getDianaDutenKartenLisa ()
	{
		ListaKartak emaitza = new ListaKartak ();
		Iterator <Karta> itr = this.getIteradorea();
		Karta egungoKarta;
		while (itr.hasNext())
		{
			egungoKarta = itr.next();
			if (egungoKarta instanceof Morroia)
			{
				if (((Morroia)egungoKarta).getNiriSoilikErasoAhal())
				{
					emaitza.gehituKarta(egungoKarta);
				}
			}
		}
		if (emaitza.getLuzeera() == 0)
		{
			emaitza = null;
		}
		return emaitza;
	}
	public int getLuzeera ()
	{
		return this.lista.size();
	}
	public ListaKartak get40Karta ()
	{
		ListaKartak listaKartak = new ListaKartak();
		Random rand = new Random ();
		for (int i = 0; i < 40; i++)
		{
			listaKartak.gehituKarta(this.lista.get(rand.nextInt(this.lista.size()-1)));
		}
		return listaKartak;
	}
}