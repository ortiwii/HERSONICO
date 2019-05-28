package kartak;

import a.projectPackage.Partida;
import a.projectPackage.Teklatua;

public class SorginkeriaDefentsazkoaSendatu extends SorginkeriaDefentsazkoa 
{
	
	//atributoak
		private int sendatuKop;

		//eraikitzailea
		public SorginkeriaDefentsazkoaSendatu(int pIdKarta, String pIzena, String pDeskribapena,int pBalioa, int pSendatuKop) 
		{
			super (pIdKarta, pIzena, pDeskribapena,pBalioa);
			this.sendatuKop = pSendatuKop;
		}
		
		//gainontzeko metodoak
		public void jokatuKarta ()
		{
			Karta nireKarta = Teklatua.getNireTeklatua().irakurriAukera("	Zure zelaiko karten artean, zeini eman nahi diziozu "+this.sendatuKop+" bizitza punto?", 
					Partida.getNirePartida().getUnekoJokalariarenZelaikoKartak());
			if (nireKarta != null)
			{
				this.emanDefentsa(nireKarta);
				if (((Morroia)nireKarta).getBizitza() > 0)
				{
					System.out.println("		- "+Partida.getNirePartida().getUnekoJokalariarenIzena()+"-ren "+nireKarta.getIzena()+" "+((Morroia)nireKarta).getBizitza()+" bizitza puntu ditu");
				}
			}
			else
			{
				System.out.println("		- Ez diozu inori bizitza emango");
			}
		}
		public void emanDefentsa (Karta pKarta)
		{
			if (pKarta != null)
			{	
				((Morroia)pKarta).kartaHoniErasotu(this.sendatuKop*(-1));
				Partida.getNirePartida().setUnekoJokalariarenGemak(Partida.getNirePartida().getUnekoJokalariarenGemak() - super.balioa);
			}
			else
			{
				System.out.println("Ez diozu inori emango defentsa");
			}
		}
		public Karta kopiaBatEgin ()
		{
			SorginkeriaDefentsazkoaSendatu emaitza = new SorginkeriaDefentsazkoaSendatu(this.getId(), this.izena, this.getDeskribapena(), this.balioa, this.sendatuKop);
			return emaitza;
		}
		public void imprimatu ()
		{
			System.out.println("Sorginkeria: "+super.getIzena()+", "+this.sendatuKop+" bizitza punto sendatzen ditu ("+this.balioa+" gema)");
		}
}
