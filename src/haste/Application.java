package haste;

import java.io.Console;

import org.apache.commons.collections4.Get;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Application {
	
	private static final Logger logger = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		
//		Console console = System.console();
		BasicConfigurator.configure();
		Logger.getLogger(Application.class.getName()).setLevel(Level.INFO);
//		logger.debug("this is in debug mode");
//		logger.info("info mode");
//		logger.error("this is not working");
//		console.printf("enter password\n");
//		char[] password = console.readPassword();
//		System.out.println(password);
//		logger.warn("warning");
//		logger.fatal("fatal error");
//		DataBaseConnector.connect(null);
		// console.printf("Haste Application Starting !!!!");
		// console.printf("Haste Application Starting !!!!");
		logger.info("Starting application!");
		new BannerPrinter().printBanner();
		DataBaseConnector connector = new DataBaseConnector();
		
		String str = ("SELECT S.SUBMISSION_ID, S.OFFER_ID,  S.APPLICANT_STATUS, S.SUBMISSION_STATUS, TO_CHAR(S.LAST_UPDATE_DATE, 'DD/Mon/YY HH:MM:SS AM') AS LAST_UPDATE_DATE,S.FACILITY_ID,S.LOAN_AMOUNT,S.OUTCOME_STRING  FROM DIGX_OR_SUBMISSION_SUMMARY S WHERE OFFER_ID NOT IN ('NCS001','NCS006','NCS007') AND S.SUBMISSION_STATUS = 'DEDUPE' ORDER BY LAST_UPDATE_DATE DESC ");  
		
		Object[][] objArr = connector.getResponseArray(connector.getConnection("URL", "USERNAME", "PASSWORD"), str);
		
		
		ExcelSheetUtil generator = new ExcelSheetUtil();
		XSSFWorkbook workbook = generator.getNewWorkBook();
		XSSFSheet sheet = generator.createNewSheetInWorkBook(workbook, "OBDX");
		
		generator.writeRecordSetToSheet(objArr, sheet);
		generator.writeSheetToDisk(workbook);
		
		logger.info("Stopping application");
		
	}
}
