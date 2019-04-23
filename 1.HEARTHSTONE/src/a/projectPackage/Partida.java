package a.projectPackage;

import kartak.Heroia;
import kartak.ListaKartak;

public class Partida 
{
	//atributoak
	private static Partida nirePartida = null;
	private Jokalaria[] listaJokalariak;
	private static int unekoTxanda;
	
	//eraikitzailea
	private Partida() 
	{
		this.listaJokalariak = new Jokalaria[2];
		Partida.unekoTxanda=0;
	}
	
	//gainontzeko metodoak
	public static Partida getNirePartida()
	{
		if (nirePartida == null)
		{
			nirePartida = new Partida();
		}
		return nirePartida;
	}

	public void hasieratuPartida ()
	{
		//TODO
		//Jokalariak hasieratu
		for (int i = 1; i<=2; i++)
		{
			String pIzena = Teklatua.getNireTeklatua().Irakurri("Sartu "+i+". jokalariaren izena");
			Jokalaria jok = new Jokalaria (pIzena);
			jok.hasieratuJokalaria();
			this.listaJokalariak[i] = jok;
		}
	}
	public String jokatu() 
	{
		//TODO
		
		return "Jokalari irabazlearen izena";
	}
	public void hurrengoTxanda() 
	{
		//Suma 1 a uneko txanda
		Partida.unekoTxanda = Partida.unekoTxanda + 1;
		//TODO, porque no se si necesita algo mas 
	}
	public boolean irabazia()
	{
		boolean emaitza = false;
		if (this.listaJokalariak[0].irabazia() || this.listaJokalariak[1].irabazia())
		{
			emaitza = true;
		}
		return emaitza;
	}
	
	public void inprimatuPartida() 
	{
		//TODO
	}
		//getters
	public Jokalaria getUnekoJokalaria ()
	{
		if (Partida.unekoTxanda % 2 == 0)
		{
			return this.listaJokalariak[0];
		}
		else
		{
			return this.listaJokalariak[1];
		}
	}
	public Heroia getHeroiEtsaia ()
	{
		//como la unekotxanda si es bikoti (%2=0) es el jugador 1, y si no el jugador 2, 
		// si unekotxanda es bikoti devolvemos el heroe del jugador 2, y si no del 1, es decir,
		// hacemos lo contrario
		if (Partida.unekoTxanda % 2 != 0)
		{
			return this.listaJokalariak[0].getHeroia();
		}
		else
		{
			return this.listaJokalariak[1].getHeroia();
		}
	}
	public Jokalaria getJokalariEtsaia ()
	{
		if (Partida.unekoTxanda % 2 != 0)
		{
			return this.listaJokalariak[0];
		}
		else
		{
			return this.listaJokalariak[1];
		}
	}
	public int getUnekoTxanda ()
	{
		return Partida.unekoTxanda;
	}
}
