package a.projectPackage;

import java.io.BufferedReader;
import java.io.FileReader;

public class Fitxategiak 
{
	public String irakurriTxt (String helbidea)
	{
		String texto = "";
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(helbidea));
			String temp = "";
			String bfRead = br.readLine();
			while (bfRead != null)
			{
				temp = temp + bfRead;
				bfRead = br.readLine();
			}
			texto = temp;
			br.close();
		}
		catch (Exception e) 
		{
			System.out.println("No se encontro el archivo");
		}
		return texto;
	}

}
