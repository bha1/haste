package haste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class DataBaseConnector {

	private static final Logger logger = Logger.getLogger(DataBaseConnector.class.getName());

	private String URL_PREFIX = "jdbc:oracle:thin:@";
	
	static{
		Logger.getLogger(DataBaseConnector.class.getName()).setLevel(Level.INFO);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.info("Oracle driver loaded");
		} catch (ClassNotFoundException e) {
			logger.fatal("Oracle DB dependency can't be resolved.");
			e.printStackTrace();
		}  
	}
	
	public Connection getConnection(String url,String userName, String password){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL_PREFIX+url, userName, password);
			logger.info("Connection established with"+url);
		} catch (SQLException e) {
			logger.fatal("Data-base connection can't be established");
			e.printStackTrace();
		}
		return conn;
	}
	
	public Object[][] getResponseArray(Connection conn, PreparedStatement preparedStatement){
		Object[][] resultSets = null;
		try {
			ResultSet rs=preparedStatement.executeQuery();
			int columnCount = rs.getMetaData().getColumnCount();
			ArrayList<Object[]> list = new ArrayList<>();
			Object[] headerSet = new Object[columnCount];
			for(int i = 1;i<=columnCount ;i++){
				//TODO this will break at some time or other
				headerSet[i-1] = rs.getMetaData().getColumnName(i);
			}
			list.add(headerSet);
			while(rs.next()){
				Object[] rowSet = new Object[columnCount];
				for(int i = 1 ; i<=columnCount;i++){
					rowSet[i-1] = rs.getObject(i);
				}
				list.add(rowSet);
			}
			resultSets = list.toArray(new Object[list.size()][]);
			logger.info("Statement execution complete.");
		} catch (SQLException e) {
			logger.info("faliure while executing sql query.");
			e.printStackTrace();
		}  
		return resultSets;
	}
	
	public static void closeConnection(Connection connection){
		try {
			connection.close();
			logger.info("connection closed"+connection.getMetaData().getURL());
		} catch (SQLException e) {
			logger.fatal("Something failed while closing connection");
			e.printStackTrace();
		}
	}

}
