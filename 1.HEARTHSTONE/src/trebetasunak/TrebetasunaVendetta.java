package trebetasunak;
import kartak.Morroia;

public class TrebetasunaVendetta extends Trebetasuna
{
	//atributoak
	
	//eraikitzailea
	public TrebetasunaVendetta() 
	{
		super ("Vendetta", "momentu kritiko batean dagoenean, eraso puntuak %150 batean igoko dira");
	}

	//gainontzeko metodoak
	public void erabiliTrebetasuna(Morroia pMorroia)
	{
		if (pMorroia.getBizitza() == 1)
		{
			
		}
	}
}
