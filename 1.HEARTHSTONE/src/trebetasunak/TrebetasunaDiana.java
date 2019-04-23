package trebetasunak;
import kartak.Morroia;

public class TrebetasunaDiana extends Trebetasuna
{
	//atributoak
	
	//eraikitzailea
	public TrebetasunaDiana() 
	{
		super ("Diana", "Trebetasun hau izatekotan, "
				+ "jokalariak jaso ditzakeen eraso guztiak karta honi joango dira, hau hil arte");
	}
	
	//gainontzeko metodoak
	public void erabiliTrebetasuna(Morroia pMorroia)
	{
		pMorroia.setDiana();
	}
}
