package kartak;

public class Heroia  
{
	//atributoak
	private int bizitza;

	//eraikitzailea
	public Heroia() 
	{
		this.bizitza = 30;
	}
	
	//gainontzeko metodoak
	public int getBizitza()
	{
		return this.bizitza;
	}
	
	public void setBizitza(int pBizitza) // Metodo hau jUniten implementazioa errazteko baino ez da.
	{
		this.bizitza = pBizitza;
	}
	public void honiErasoEgin (int pErasoa)
	{
		this.bizitza = this.bizitza - pErasoa;
	}
	public boolean bizirikDago ()
	{
		boolean emaitza = true;
		if (this.bizitza <= 0)
		{
			emaitza = false;
		}
		return emaitza;
	}
	public void inprimatu ()
	{
		System.out.println("Heroia, "+this.bizitza+" bizitza puntu");
	}
}