package kartak;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ListaKartak 
{
	//atributoak
	private ArrayList<Karta> lista;
	private boolean ikusgarria;
	
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
		Iterator <Karta> itr = this.getIteradorea();
		Karta egungoKarta;
		
		while (itr.hasNext())
		{
			egungoKarta = itr.next();
			if (egungoKarta instanceof Morroia)
			{
				if ( ((Morroia)egungoKarta).getBizitza() <= 0 )
				{
					this.kenduKarta(egungoKarta);
				}
			}
		}
	}
	public ArrayList<Karta> getLista()
	{
		return this.lista;
	}
	public void gehituKarta(Karta pKarta) 
	{
		if (!this.lista.contains(pKarta))
		{
			this.lista.add(pKarta);
		}
	}
	public void kenduKarta(Karta pKarta) 
	{
		if (this.lista.contains(pKarta))
		{
			this.lista.remove(pKarta);
		}
	}
	public Karta bilatuObjetiboa(Karta pKarta) 
	{
		//TODO
		Karta karta=null;
		return karta;
	}
	public void inprimatuLista() 
	{
		if (this.ikusgarria)
		{
			Iterator <Karta> itr = this.getIteradorea();
			Karta egungoKarta;
			while (itr.hasNext())
			{
				egungoKarta = itr.next();
				egungoKarta.imprimatu();
			}
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
}