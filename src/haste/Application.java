package haste;

import java.io.Console;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Application {
public static void main(String[] args) {
	//Console console =  System.console();
	BasicConfigurator.configure();
	Logger logger = Logger.getLogger(Application.class.getName());
	Logger.getLogger(Application.class.getName()).setLevel(Level.INFO);
	logger.debug("this is in debug mode");
	logger.info("info mode");
	logger.error("this is not working");
	logger.warn("warning");
	logger.fatal("fatal error");
	DataBaseConnector.connect(null);
	new BannerPrinter().printBanner();
	ExcelSheetGenerator generator = new ExcelSheetGenerator();
	generator.sheetBuilder();
	//console.printf("Haste Application Starting !!!!");
	//console.printf("Haste Application Starting !!!!");
}
}
