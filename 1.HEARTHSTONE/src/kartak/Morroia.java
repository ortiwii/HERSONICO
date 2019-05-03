	package kartak;

import a.projectPackage.Partida;
import a.projectPackage.Teklatua;
import trebetasunak.*;
public class Morroia extends Karta 
{
	//atributoak
	private int erasoa;
	private int bizitza;
	private Trebetasuna trebetasuna;
		//por si tiene la habilidad diana
	private boolean niriSoilikErasoAhal;
	private boolean trebetasunaErabilita = false;
	
	//eraikitzailea
	public Morroia(int pIdKarta, String pIzena, String pDeskribapena, int pBalioa, int pErasoa, int pBizitza, Trebetasuna pTrebetasuna) 
	{
		super (pIdKarta, pIzena, pDeskribapena, pBalioa, false);
			//trebetasuna
		this.trebetasuna = pTrebetasuna;
		if (pTrebetasuna != null)
		{
			this.trebetasuna.erabiliTrebetasuna (this);
		}
			//atributoen hasieratzea
		this.niriSoilikErasoAhal = false;
		this.bizitza = pBizitza;
		this.erasoa = pErasoa;
	}
	
	//gainontzeko metodoak
	public void jokatuKarta() 
	{
		if (this.erasoAhal())
		{
			//Orain etsaiaren Karten artean, erasoko duen bat aukeratuko du
			String mezua = "	-> "+this.getIzena()+"-k, "+Partida.getNirePartida().getJokalariEtsaia().getIzena()+"-en zelaiko karten artean nori erasotuko dion esan:";
			Karta erasotukoDenKarta = Teklatua.getNireTeklatua().irakurriEtsaiarenKarta(mezua);
			if (erasotukoDenKarta != null)
			{
				//Erasoa egin behar da
				if (erasotukoDenKarta instanceof Morroia)
				{
					((Morroia) erasotukoDenKarta).kartaHoniErasotu(this.erasoa);
				}
				this.kartaHoniErasotu(((Morroia) erasotukoDenKarta).getErasoa());
				
				//Gemen eguneraketa
				int gemak = Partida.getNirePartida().getUnekoJokalaria().getGemak();
				gemak = gemak - super.getBalioa();
				Partida.getNirePartida().getUnekoJokalaria().setGemak(gemak);
				
				//Egoeraren eguneraketa
				super.setErasoDezakeen(false);
				
				//imprimaketa
				if (this.bizitza > 0)
				{
					System.out.println("		- "+Partida.getNirePartida().getUnekoJokalaria().getIzena()+"-ren "+this.izena+" "+this.bizitza+" bizitza puntu ditu");
				}
				
				else if (((Morroia)erasotukoDenKarta).getBizitza() > 0)
				{
					System.out.println("		- "+Partida.getNirePartida().getJokalariEtsaia().getIzena()+"-ren "+erasotukoDenKarta.getIzena()+" "+((Morroia)erasotukoDenKarta).getBizitza()+" bizitza puntu ditu");
				}
			}
			else
			{	//	=null bada esan nahi du Heroiari eraso egin nahi diola
				if (Partida.getNirePartida().getJokalariEtsaia().getNireZelaikoKartak().getDianaDutenKartenLista() != null)
				{
					System.out.println("		- Ez diozu inori erasotuko");

					//Egoeraren eguneraketa
					super.setErasoDezakeen(true);
				}
				else
				{
					Partida.getNirePartida().getHeroiEtsaia().honiErasoEgin(this.erasoa);
					if (Partida.getNirePartida().getHeroiEtsaia().getBizitza() <= 0)
					{
						System.out.println("		- HEROIA-ri erasotu diozu, eta HIL EGIN DA !!");
					}
					else
					{
						System.out.println("		- HEROIA-ri erasotu diozu, eta "+Partida.getNirePartida().getHeroiEtsaia().getBizitza()+" puntu ditu orain");
					}
					
					//Egoeraren eguneraketa
					super.setErasoDezakeen(false);
				}
			}
		}
		else
		{
			System.out.println("Aukeratu Duzun kartak ezin dezakee erasorik egin");
		}
	}
	public void imprimatu ()
	{
		if (this.getErasoDezakeen())
		{	
			if (this.trebetasuna != null)
			{
				System.out.println(super.izena+" [Bizitza = "+this.getBizitza()+", Erasoa = "+this.getErasoa()+"] "+this.trebetasuna.getIzena()+" trebetasuna ("+super.balioa+" gema)"+"// Eraso AHAL");
			}
			else
			{
				System.out.println(super.izena+" [Bizitza = "+this.getBizitza()+", Erasoa = "+this.getErasoa()+"] Ez dauka trebetasunik ("+super.balioa+" gema)"+"// Eraso AHAL");
			}
		}
		else
		{
			if (this.trebetasuna != null)
			{
				System.out.println(super.izena+" [Bizitza = "+this.getBizitza()+", Erasoa = "+this.getErasoa()+"] "+this.trebetasuna.getIzena()+" trebetasuna ("+this.balioa+" gema)"+"// EZIN Eraso");
			}
			else
			{
				System.out.println(super.izena+" [Bizitza = "+this.getBizitza()+", Erasoa = "+this.getErasoa()+"] Ez dauka trebetasunik ("+this.balioa+" gema)"+"// EZIN Eraso");
			}
		}
	}
	public int kartaHoniErasotu (int pErasoa)
	{
		this.bizitza = this.bizitza - pErasoa;
		return this.erasoa;
	}
	public void erabiliTrebetasuna()
	{
		this.trebetasuna.erabiliTrebetasuna(this);
	}
	
			//Siempre hay que comprobar esto para que pueda atacar
	private boolean erasoAhal()
	{
		if (this.trebetasuna instanceof TrebetasunaErasoJarraia)
		{
			super.setErasoDezakeen(true);
		}
		else if (super.getZelairaAteratakoTxanda() + 1 == Partida.getNirePartida().getUnekoTxanda())
		{
			super.setErasoDezakeen(true);
		}
		return super.getErasoDezakeen();
	}
	//cada vez que empieza el turno de un jugador hay que usar este metodo 
	public void egoeraEguneratu ()
	{
		if (super.getZelairaAteratakoTxanda() < Partida.getNirePartida().getUnekoTxanda() || this.trebetasuna instanceof TrebetasunaErasoJarraia)
		{
			super.setErasoDezakeen(true);
		}
		else
		{
			super.setErasoDezakeen(false);
		}
		
	}
	public Karta kopiaBatEgin ()
	{
		Morroia emaitza = new Morroia (this.getId(),this.getIzena(),this.getDeskribapena(),this.balioa, this.erasoa, this.bizitza, this.trebetasuna);
		return emaitza;
	}
	//trebetasunak
	public void egikarituTrebetasuna ()
	{
		if (this.trebetasuna != null)
		{
			this.trebetasuna.erabiliTrebetasuna(this);
		}
	}
		//diana
	public void setDiana ()
	{
		this.niriSoilikErasoAhal = true;
	}
	//getters
	public int getErasoa ()
	{
		return this.erasoa;
	}
	public int getBizitza ()
	{
		return this.bizitza;
	}
	public boolean getNiriSoilikErasoAhal ()
	{
		return this.niriSoilikErasoAhal;
	}
	
	public boolean getTrebetasunaErabilita()
	{
		return this.trebetasunaErabilita;
	}
	//setters
	public void setErasoa (int pErasoa)
	{
		this.erasoa = pErasoa;
	}
	
	public void setTrebetasunaErabilita()
	{
		this.trebetasunaErabilita = true;
	}
}
