package a.projectPackage;

import java.util.Iterator;
import java.util.Scanner;
import kartak.Karta;
import kartak.ListaKartak;

public class Teklatua 
{
	//atributoak
	private static Teklatua nireTeklatua = null;
	private Scanner sc;
	//eraikitzailea
	private Teklatua ()
	{	
		sc = new Scanner(System.in);
	}
	
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
		System.out.print("	");
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

		System.out.print("\n	Sakatu enter jarraitzeko:");
		while (!salataria)
		{
			textoa = sc.nextLine();
			if (aux != textoa)
			{
				salataria = true;
				System.out.println("");
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
				System.out.print("	"+kont+") "); egungoKarta.imprimatu();
			}
			//7. aukera pasatu egiteko da
			System.out.println("	"+(kont+1)+") Pasatu");
			System.out.print("	");int aukeratutakoZenb = sc.nextInt();
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
			System.out.println("	Ez dago aukeratu dezakezun kartarik");
			return null;
		}
		
	}
	public Karta irakurriEtsaiarenKarta (String mezua)
	{
		Karta aukeratutakoKarta ;
		int kont = 0;
		Jokalaria jokEtsaia = Partida.getNirePartida().getJokalariEtsaia();
		if (jokEtsaia.getNireZelaikoKartak() != null || jokEtsaia.getNireZelaikoKartak().getLuzeera() != 0)
		{
			System.out.println(mezua);
			//Aqui vamos a comprobar si alguna de sus cartas tiene la habilidad diana
			if (jokEtsaia.getNireZelaikoKartak().getDianaDutenKartenLisa() != null )//|| jokEtsaia.getNireZelaikoKartak().getDianaDutenKartenLisa().getLuzeera() != 0)
			{	//Hemen sartzen bada badakigu kartaren batek diana duela
				Iterator <Karta> itr = Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak().getDianaDutenKartenLisa().getIteradorea();
				while (itr.hasNext())
				{
					aukeratutakoKarta = itr.next();
					kont ++;
					System.out.print("	"+kont+") ");aukeratutakoKarta.imprimatu();
				}
				System.out.print("	");int zenb = sc.nextInt();
				//Orain badakigu zein den gure aukera
				aukeratutakoKarta = Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak().getDianaDutenKartenLisa().getPosizioHonetakoKarta(zenb);
			}
			else  
			{	//Hemen sartzen bada badakigu ez dagoela diana duten kartarik, hau da, edonori eraso diezaike
				System.out.print("	0) ");Partida.getNirePartida().getHeroiEtsaia().inprimatu();
				Iterator <Karta> itr = Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak().getIteradorea();
				while (itr.hasNext())
				{
					kont ++;
					aukeratutakoKarta = itr.next();
					System.out.print("	"+kont+") ");aukeratutakoKarta.imprimatu();
				}
				System.out.print("	");int zenb = sc.nextInt();
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
	public void imprimatuX_ (int luzeera)
	{
		for (int i = 1; i <= luzeera; i++)
		{
			System.out.print("_");
		}
	}
}
