package trebetasunak;
import kartak.Morroia;

public class TrebetasunaErasoJarraia extends Trebetasuna
{
	//atributoak
	
	//eraikitzailea
	public TrebetasunaErasoJarraia() 
	{
		super ("Eraso Jarraia", "zelaian sartu bezain laster eraso egin dezake,"
				+ " berriz jokalari honen txanda izan arte itxaron gabe");
	}

	//gainontzeko metodoak
	public void erabiliTrebetasuna(Morroia pMorroia)
	{
		pMorroia.setErasoJarraia();
	}
}
