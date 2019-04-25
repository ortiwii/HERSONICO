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
			Karta nireKarta = Teklatua.getNireTeklatua().irakurriAukera("Zure zelaiko karten artean, zeini eman nahi diziozu "+this.sendatuKop+" bizitza punto?", 
					Partida.getNirePartida().getUnekoJokalaria().getNireZelaikoKartak());
			this.emanDefentsa(nireKarta);
		}
		public void emanDefentsa (Karta pKarta)
		{
			if (pKarta != null)
			{	
				((Morroia)pKarta).kartaHoniErasotu(this.sendatuKop*(-1));
				pKarta.imprimatu();
			}
			else
			{
				System.out.println("Ez diozu inori emango defentsa");
			}
		}

}
