package haste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

public class DataBaseConnector {
	
	public static void connect(String[] args) {
		String URL = "jdbc:oracle:thin:@hostName:1521:Service";
		String USER = "user";
		String PASS = "password";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt=conn.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT S.SUBMISSION_ID, S.OFFER_ID,  S.APPLICANT_STATUS, S.SUBMISSION_STATUS, TO_CHAR(S.LAST_UPDATE_DATE, 'DD/Mon/YY HH:MM:SS AM'),S.FACILITY_ID,S.LOAN_AMOUNT,S.OUTCOME_STRING  FROM DIGX_OR_SUBMISSION_SUMMARY S WHERE OFFER_ID NOT IN ('NCS001','NCS006','NCS007') AND S.SUBMISSION_STATUS = 'DEDUPE' ORDER BY LAST_UPDATE_DATE DESC ");  
			while (rs.next()) {
				int columnCount = rs.getMetaData().getColumnCount();
				for(int i = 1 ; i<=columnCount;i++){
					System.out.print(" "+rs.getString(i));
				}
				System.out.println(" ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
