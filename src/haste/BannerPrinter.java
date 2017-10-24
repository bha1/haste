package haste;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BannerPrinter {
	public void printBanner() {
		InputStream in = getClass().getResourceAsStream("/resources/banner.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
