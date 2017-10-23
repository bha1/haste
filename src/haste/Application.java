package haste;

import java.io.Console;
import java.sql.Connection;

import org.apache.commons.collections4.Get;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import resources.HasteConstants;

public class Application {

	private static final Logger logger = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		// Console console = System.console();
		BasicConfigurator.configure();
		Logger.getLogger(Application.class.getName()).setLevel(Level.INFO);
		// logger.debug("this is in debug mode");
		// logger.info("info mode");
		// logger.error("this is not working");
		// console.printf("enter password\n");
		// char[] password = console.readPassword();
		// System.out.println(password);
		// logger.warn("warning");
		// logger.fatal("fatal error");
		// DataBaseConnector.connect(null);
		// console.printf("Haste Application Starting !!!!");
		// console.printf("Haste Application Starting !!!!");
		String OBP_URL = args[0];
		String OBP_SCHEMA_NAME = args[1];
		String OBP_USERNAME = args[2];
		String OBP_PASSWORD = args[3];
		String OBDX_URL = args[4];
		String OBDX_SCHEMA_NAME = args[5];
		String OBDX_USERNAME = args[6];
		String OBDX_PASSWORD = args[7];
		

		logger.info("Starting application!");
		new BannerPrinter().printBanner();
		Console console = System.console();
		console.printf("Enter password for OBP DB \n");
		char[] obpPassword = console.readPassword();
		console.printf("\n");
		console.printf("Enter password for OBP DB \n");
		char[] obdxPassword = console.readPassword();
		DataBaseConnector connector = new DataBaseConnector();

		// String str = ("SELECT S.SUBMISSION_ID, S.OFFER_ID,
		// S.APPLICANT_STATUS, S.SUBMISSION_STATUS, TO_CHAR(S.LAST_UPDATE_DATE,
		// 'DD/Mon/YY HH:MM:SS AM') AS
		// LAST_UPDATE_DATE,S.FACILITY_ID,S.LOAN_AMOUNT,S.OUTCOME_STRING FROM
		// DIGX_OR_SUBMISSION_SUMMARY S WHERE OFFER_ID NOT IN
		// ('NCS001','NCS006','NCS007') AND S.SUBMISSION_STATUS = 'DEDUPE' ORDER
		// BY LAST_UPDATE_DATE DESC ");
		Connection obpConn = connector.getConnection(OBP_URL, OBP_USERNAME, obpPassword.toString());
		Connection obdxConn = connector.getConnection(OBDX_URL, OBDX_USERNAME, obdxPassword.toString());
		ExcelSheetUtil generator = new ExcelSheetUtil();
		XSSFWorkbook workbook = generator.getNewWorkBook();
		XSSFSheet sheet = null;
		ScriptBuilder builder = new ScriptBuilder();
		Object[][] objArr = connector.getResponseArray(obpConn,
				builder.getScript("obp_script1.txt", HasteConstants.NGPCOB_SCHEMA, "NGPCOB"));
		sheet = generator.createNewSheetInWorkBook(workbook, "OBP1");
		generator.writeRecordSetToSheet(objArr, sheet);

		connector.getResponseArray(obpConn,
				builder.getScript("obp_script2.txt", HasteConstants.NGPCOB_SCHEMA, "NGPCOB"));
		sheet = generator.createNewSheetInWorkBook(workbook, "OBP2");
		generator.writeRecordSetToSheet(objArr, sheet);

		connector.getResponseArray(obdxConn,
				builder.getScript("obdx_script1.txt", HasteConstants.OBDX_SCHEMA, "OBDX_PRD"));
		sheet = generator.createNewSheetInWorkBook(workbook, "OBDX1");
		generator.writeRecordSetToSheet(objArr, sheet);

		generator.writeSheetToDisk(workbook);

		logger.info("Stopping application");

	}
}
