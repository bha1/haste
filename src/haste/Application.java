package haste;

import java.io.Console;

public class Application {
public static void main(String[] args) {
	//Console console =  System.console();
	DataBaseConnector.connect(null);
	new BannerPrinter().printBanner();
	ExcelSheetGenerator generator = new ExcelSheetGenerator();
	generator.sheetBuilder();
	//console.printf("Haste Application Starting !!!!");
	//console.printf("Haste Application Starting !!!!");
}
}
