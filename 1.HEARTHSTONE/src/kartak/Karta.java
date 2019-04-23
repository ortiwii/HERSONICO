package kartak;

import a.projectPackage.Partida;

public abstract class Karta 
{
	//atributoak
	private int idKarta;
	private String izena;
	private String deskribapena;
	private int balioa;
	//textoaren kolorerako konstanteak
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_BLUE = "\u001B[34m";
	private static final String ANSI_RESET = "\u001B[0m";
	//jokatu karta kudeatzeko
	private int zelairaAteratakoTxanda;
	//Creo que esto hace falta, porque no puedes sacar una carta y que ataque del tiron, 
	//tienes que esperar un turno
	private boolean erasoAhalDu;

	//eraikitzailea
	public Karta(int pIdKarta, String pIzena, String pDeskribapena, int pBalioa, boolean pErasoAhalDu) 
	{
		this.idKarta = pIdKarta;
		this.izena = pIzena;
		this.deskribapena = pDeskribapena;
		this.balioa = pBalioa;
		
		this.erasoAhalDu = pErasoAhalDu;
	}
	
	//gainontzeko metodoak	
	public abstract void jokatuKarta();
	
	public boolean idHauDu(int pId)
	{
		boolean emaitza = false;
		if (this.idKarta == pId)
		{
			emaitza = true;
		}
		return emaitza;
	}
	public void imprimatu()
	{
		//MORROIA baldin bada kolore Gorria izango du textoak
		//SORGINKERIA baldin bada kolore Urdina izango du
			
			//Eraso egin ahal badu, Eraso Ahal ipiniko du, bestela Ezin Eraso
		if (this instanceof Morroia)
		{
			if (this.getErasoDezakeen())
			{
				System.out.println(ANSI_RED+this.izena+" [Bizitza = "+((Morroia)this).getBizitza()+", Erasoa = "+((Morroia)this).getErasoa()+ANSI_RESET+" ("+this.balioa+" gema)"+"// Eraso AHAL");
			}
			else
			{
				System.out.println(ANSI_RED+this.izena+" [Bizitza = "+((Morroia)this).getBizitza()+", Erasoa = "+((Morroia)this).getErasoa()+ANSI_RESET+" ("+this.balioa+" gema)"+"// EZIN Eraso");
			}
		}
		else if (this instanceof Sorginkeria)
		{
			System.out.println(ANSI_BLUE+this.izena+ANSI_RESET+" ("+this.balioa+" gema)");
		}
	}
	protected void setZelairaAteratakoTxanda ()
	{
		this.zelairaAteratakoTxanda = Partida.getNirePartida().getUnekoTxanda();
	}
	public void emanDeskribapena ()
	{
		//MORROIA baldin bada kolore Gorria izango du textoak
		//SORGINKERIA baldin bada kolore Urdina izango du
		
		if (this instanceof Morroia)
		{
			System.out.println(ANSI_RED+this.idKarta+": "+this.deskribapena+ANSI_RESET);
		}
		else if (this instanceof Sorginkeria)
		{
			System.out.println(ANSI_BLUE+this.idKarta+": "+this.deskribapena+ANSI_RESET);
		}
	}
	public void setErasoDezakeen (boolean pBool)
	{
		this.erasoAhalDu = pBool;
	}
	public abstract void egoeraEguneratu();
		//getters
		public int getBalioa ()
		{
			return this.balioa;
		}
		public int getZelairaAteratakoTxanda ()
		{
			return this.zelairaAteratakoTxanda;
		}
		public boolean getErasoDezakeen ()
		{
			return this.erasoAhalDu;
		}
}
	