package haste;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class BannerPrinter {
public void printBanner(){
	System.out.println("***********************");
	InputStream in = getClass().getResourceAsStream("/resources/banner.txt");
	try (BufferedReader br = new BufferedReader(new InputStreamReader(in))){
		   String line = null;
		   while ((line = br.readLine()) != null) {
		       System.out.println(line);
		   }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
}
}
