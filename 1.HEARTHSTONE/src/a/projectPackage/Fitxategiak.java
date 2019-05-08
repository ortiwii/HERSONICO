package a.projectPackage;

import java.io.BufferedReader;
import java.io.FileReader;

public class Fitxategiak 
{
	private static Fitxategiak nireFitxategiak;
	private BufferedReader br;
	
	private Fitxategiak ()
	{}
	
	public static synchronized Fitxategiak getFitxategiak ()
	{
		if (nireFitxategiak == null)
		{
			nireFitxategiak = new Fitxategiak();
		}
		return nireFitxategiak;
	}
	public String irakurriTxt (String helbidea)
	{
		String texto = "";
		try 
		{
			this.br = new BufferedReader(new FileReader(helbidea));
			String temp = "";
			String bfRead = br.readLine();
			while (bfRead != null)
			{
				temp = temp + bfRead;
				bfRead = br.readLine();
			}
			texto = temp;
		}
		catch (Exception e) 
		{
			System.out.println("Ez da fitxategia aurkitu");
		}
		return texto;
	}
}
