package trebetasunak;
import kartak.Morroia;

public class TrebetasunaVendetta extends Trebetasuna
{
	//atributoak
	
	//eraikitzailea
	public TrebetasunaVendetta() 
	{
		super ("Vendetta", "momentu kritiko batean dagoenean, eraso puntuak %200 batean igoko dira turno batean");
	}

	//gainontzeko metodoak
	public void erabiliTrebetasuna(Morroia pMorroia)
	{
		if (pMorroia.getBizitza() == 1)
		{
//			pMorroia.setErasoa(pMorroia.getErasoa()*2);
			
			if (!pMorroia.getTrebetasunaErabilita())
			{
				int erasoaHandituta = pMorroia.getErasoa() * 2;
				pMorroia.setErasoa(erasoaHandituta);
				pMorroia.setTrebetasunaErabilita(); 
				// Este metodo hace de chivato y te dice que ya ha utilizado vendetta asi
				// la siguiente vez que lo uses con esta carta no va a funcionar porque esta en true.
			}
			
		}
	}
}
