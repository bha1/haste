package haste;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import resources.HasteConstants;

public class Application {

	private static final Logger logger = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		BasicConfigurator.configure();
		Logger.getLogger(Application.class.getName()).setLevel(Level.INFO);

		// read all params from prompt
		String OBP_URL = args[0];
		String OBP_SCHEMA_NAME = args[1];
		String OBP_USERNAME = args[2];
		String OBP_PASSWORD = args[3];
		String OBDX_URL = args[4];
		String OBDX_SCHEMA_NAME = args[5];
		String OBDX_USERNAME = args[6];
		String OBDX_PASSWORD = args[7];
//		String DATE_SCRIPT_1 = args[8];
//		String DATE_SCRIPT_2 = args[9];
		
		// date for DCO numbers from the beginning of time
		String DATE_SCRIPT_1 = "28-Aug-2017";
		
		Calendar cal = Calendar.getInstance();

		String dateRangeInput = null;
		if(args.length > 8){
			dateRangeInput = args[8];
		}
		int dateRange = -3;
		if(dateRangeInput != null){
			try{
				dateRange = -1 * Integer.parseInt(dateRangeInput);
			}catch(NumberFormatException e){
				logger.info("Number format incorrect");
				logger.log(Level.ERROR, "this went wrong", e);
			}
		}
		//Add one day to current date.
		cal.add(Calendar.DATE, dateRange);
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		String DATE_SCRIPT_2 = format.format(cal.getTime());
		
		
		String DATE_SCRIPT_3 = "22-Nov-2017";

		logger.info("Starting application!");
		new BannerPrinter().printBanner();

		// TODO this is a future feature
		// Console console = System.console();
		// console.printf("Enter password for OBP DB \n");
		// char[] obpPassword = console.readPassword();
		// console.printf("\n");
		// console.printf("Enter password for OBP DB \n");
		// char[] obdxPassword = console.readPassword();

		DataBaseConnector connector = new DataBaseConnector();

		Connection obpConn = connector.getConnection(OBP_URL, OBP_USERNAME, OBP_PASSWORD);
		Connection obdxConn = connector.getConnection(OBDX_URL, OBDX_USERNAME, OBDX_PASSWORD);

		ExcelSheetUtil generator = new ExcelSheetUtil();
		XSSFWorkbook workbook = generator.getNewWorkBook();
		XSSFSheet sheet = null;
		PreparedStatementBuilder builder = new PreparedStatementBuilder();
		Object[][] objArr = connector.getResponseArray(obpConn, builder.getPreparedStatement(obpConn, "obp_script1.txt",
				HasteConstants.NGPCOB_SCHEMA, OBP_SCHEMA_NAME, new String[] { DATE_SCRIPT_1 }));
		sheet = generator.createNewSheetInWorkBook(workbook, HasteConstants.SHEETS[0]);
		generator.writeRecordSetToSheet(objArr, sheet);

		objArr = connector.getResponseArray(obpConn, builder.getPreparedStatement(obpConn, "obp_script2.txt",
				HasteConstants.NGPCOB_SCHEMA, OBP_SCHEMA_NAME, new String[] { DATE_SCRIPT_2 }));
		sheet = generator.createNewSheetInWorkBook(workbook, HasteConstants.SHEETS[1]);
		generator.writeRecordSetToSheet(objArr, sheet);
		
		objArr = connector.getResponseArray(obpConn, builder.getPreparedStatement(obpConn, "obp_script3.txt",
				HasteConstants.NGPCOB_SCHEMA, OBP_SCHEMA_NAME, new String[] { DATE_SCRIPT_2 }));
		sheet = generator.createNewSheetInWorkBook(workbook, HasteConstants.SHEETS[2]);
		generator.writeRecordSetToSheet(objArr, sheet);

		objArr = connector.getResponseArray(obdxConn, builder.getPreparedStatement(obdxConn, "obdx_script1.txt",
				HasteConstants.OBDX_SCHEMA, OBDX_SCHEMA_NAME, new String[] { DATE_SCRIPT_2 }));
		sheet = generator.createNewSheetInWorkBook(workbook, HasteConstants.SHEETS[3]);
		generator.writeRecordSetToSheet(objArr, sheet);
		
		objArr = connector.getResponseArray(obdxConn, builder.getPreparedStatement(obdxConn, "obdx_script2.txt",
				HasteConstants.OBDX_SCHEMA, OBDX_SCHEMA_NAME, new String[] { DATE_SCRIPT_3 }));
		sheet = generator.createNewSheetInWorkBook(workbook, HasteConstants.SHEETS[4]);
		generator.writeRecordSetToSheet(objArr, sheet);
		
		DataBaseConnector.closeConnection(obpConn);
		DataBaseConnector.closeConnection(obdxConn);
		XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
		generator.writeSheetToDisk(workbook);

		logger.info("Stopping application");

	}
}
