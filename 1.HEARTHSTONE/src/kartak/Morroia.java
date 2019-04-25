	package kartak;

import a.projectPackage.Partida;
import a.projectPackage.Teklatua;
import trebetasunak.Trebetasuna;
import trebetasunak.TrebetasunaDiana;
import trebetasunak.TrebetasunaErasoJarraia;

public class Morroia extends Karta 
{
	//atributoak
	private int erasoa;
	private int bizitza;
	private Trebetasuna trebetasuna;
		//por si tiene la habilidad diana
	private boolean niriSoilikErasoAhal;
	
	//eraikitzailea
	public Morroia(int pIdKarta, String pIzena, String pDeskribapena, int pBalioa, int pErasoa, int pBizitza, Trebetasuna pTrebetasuna) 
	{
		super (pIdKarta, pIzena, pDeskribapena, pBalioa, false);
			//trebetasuna
		this.trebetasuna = pTrebetasuna;
//		this.trebetasuna.erabiliTrebetasuna (this);
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
			Karta erasotukoDenKarta = Teklatua.getNireTeklatua().irakurriEtsaiarenKarta();
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
			}
			else
			{	//	=null bada esan nahi du Heroiari eraso egin nahi diola
				Partida.getNirePartida().getHeroiEtsaia().honiErasoEgin(this.erasoa);
			}
		}
		else
		{
			System.out.println("Aukeratu Duzun kartak ezin dezakee erasorik egin");
		}
		//behin karta erabilita, ezin dezake berriz ere eraso egin
		super.setErasoDezakeen(false);
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
	//trebetasunak
		//diana
	public void setDiana ()
	{
		this.niriSoilikErasoAhal = true;
	}
		//eraso jarraia
	public void setErasoJarraia ()
	{
		super.setErasoDezakeen(true);
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
}
