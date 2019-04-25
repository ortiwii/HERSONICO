package a.projectPackage;

import java.util.Iterator;
import java.util.Scanner;
import kartak.Karta;
import kartak.ListaKartak;

public class Teklatua 
{
	//atributoak
	private static Teklatua nireTeklatua = null;
	private Scanner sc = new Scanner(System.in);
	//eraikitzailea
	private Teklatua ()
	{	}
	
	public static Teklatua getNireTeklatua()
	{
		if (nireTeklatua == null)
		{
			nireTeklatua = new Teklatua ();
		}
		return Teklatua.nireTeklatua;
	}
	
	//gainontzeko metodoak
	public String Irakurri (String aurrekoMezua)
	{
		System.out.println(aurrekoMezua);
		String mezua = sc.next();
		return mezua;
	}
	public int OsoaIrakurri (String pAurrekoMezua)
	{
		System.out.println(pAurrekoMezua);
		int emaitza = sc.nextInt();
		return emaitza ;
    }
	public void itxaronEnterArte ()
	{
		boolean salataria = false;
		String aux = "";
		String textoa = "";
		System.out.println("Sakatu enter jarraitzeko:");
		while (!salataria)
		{
			textoa = sc.nextLine();
			if (aux != textoa)
			{
				salataria = true;
			}
		}
	}
	public Karta irakurriAukera (String pAurrekoMezua, ListaKartak pAukerak)
	{
		
		System.out.println(pAurrekoMezua);
		if (pAukerak != null )
		{
			Iterator <Karta> itr = pAukerak.getIteradorea();
			int kont = 0;
			Karta egungoKarta;
			while (itr.hasNext())
			{
				kont ++;
				egungoKarta = itr.next();
				System.out.print(kont+".aukera ) "); egungoKarta.imprimatu();
			}
			//7. aukera pasatu egiteko da
			System.out.println((kont+1)+".aukera ) Pasatu");
			int aukeratutakoZenb = sc.nextInt();
				//dagoeneko aukera zein den badakigunez, prozesua errepikatuko dugu
					//baldin pasa duen
			if (kont+1 == aukeratutakoZenb)
			{
				egungoKarta = null;
			}
					//baldin karta bat aukeratu duen
			else
			{
				egungoKarta = pAukerak.getPosizioHonetakoKarta(aukeratutakoZenb);
			}
			return egungoKarta;
		}
		else
		{
			System.out.println("Ez dago aukeratu dezakezun kartarik");
			this.itxaronEnterArte();
			return null;
		}
		
	}
	public Karta irakurriEtsaiarenKarta ()
	{
		Karta aukeratutakoKarta ;
		int kont = 0;
		if (Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak() != null || Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak().getLuzeera() != 0)
		{
			System.out.println("Aukeratu etsaiaren karten artean, zein kartari erosotuko diozun:");
			//Aqui vamos a comprobar si alguna de sus cartas tiene la habilidad diana
			if (Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak().getDianaDutenKartenLisa().getLuzeera() != 0)
			{	//Hemen sartzen bada badakigu kartaren batek diana duela
				Iterator <Karta> itr = Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak().getDianaDutenKartenLisa().getIteradorea();
				while (itr.hasNext())
				{
					aukeratutakoKarta = itr.next();
					kont ++;
					System.out.print(kont+". aukera) ");aukeratutakoKarta.imprimatu();
				}
				int zenb = sc.nextInt();
				//Orain badakigu zein den gure aukera
				aukeratutakoKarta = Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak().getDianaDutenKartenLisa().getPosizioHonetakoKarta(zenb);
			}
			else
			{	//Hemen sartzen bada badakigu ez dagoela diana duten kartarik, hau da, edonori eraso diezaike
				System.out.print("0. aukera) ");Partida.getNirePartida().getHeroiEtsaia().inprimatu();
				Iterator <Karta> itr = Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak().getIteradorea();
				while (itr.hasNext())
				{
					kont ++;
					aukeratutakoKarta = itr.next();
					System.out.print(kont+". aukera) ");aukeratutakoKarta.imprimatu();
				}
				int zenb = sc.nextInt();
				//Orain dagoeneko badakigu zein aukeratu duen
				if (zenb == 0)
				{
					aukeratutakoKarta = null;
				}
				else
				{
					aukeratutakoKarta = Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak().getPosizioHonetakoKarta(zenb);
				}
			}
			return aukeratutakoKarta;
		}
		else
		{
			System.out.println("Ez dago Kartarik zelaian, soilik heroiari eraso diezaiokezu");
			return null;
		}
	}
}
