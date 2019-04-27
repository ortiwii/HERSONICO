package kartak;

import a.projectPackage.Partida;

public abstract class Karta 
{
	//atributoak
	private int idKarta;
	protected String izena;
	private String deskribapena;
	protected int balioa;
	//textoaren kolorerako konstanteak
//	private static final String ANSI_RED = "\u001B[31m";
//	private static final String ANSI_BLUE = "\u001B[34m";
//	private static final String ANSI_RESET = "\u001B[0m";
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
	public abstract void imprimatu();
	public void setZelairaAteratakoTxanda ()
	{
		this.zelairaAteratakoTxanda = Partida.getNirePartida().getUnekoTxanda();
	}
	public void emanDeskribapena ()
	{
		//MORROIA baldin bada kolore Gorria izango du textoak
		//SORGINKERIA baldin bada kolore Urdina izango du
		
		if (this instanceof Morroia)
		{
			System.out.println(this.idKarta+": "+this.deskribapena);
		}
		else if (this instanceof Sorginkeria)
		{
			System.out.println(this.idKarta+": "+this.deskribapena);
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
		public String getIzena ()
		{
			return this.izena;
		}
		public String getDeskribapena ()
		{
			return this.deskribapena;
		}
}
	