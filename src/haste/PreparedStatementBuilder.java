package haste;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class PreparedStatementBuilder {
	private static final Logger logger = Logger.getLogger(PreparedStatementBuilder.class.getName());

	public PreparedStatement getPreparedStatement(Connection conn, String fileName, String schemaPattern, String schemaName,
			String[] vars) {
		PreparedStatement statement = null;
		try {
			schemaName = cleanInputString(schemaName);
			String sqlStatement = readSqlFromFile(fileName, schemaPattern, schemaName);
			statement = conn.prepareStatement(sqlStatement);
			if (vars != null) {
				for (int i = 0; i < vars.length; i++) {
					vars[i] = cleanInputString(vars[i]);
				}
				statement.setString(1, vars[0]);
			}
		} catch (Exception e) {
			logger.fatal("failed while parsing date params");
			e.printStackTrace();
		}

		return statement;
	}

	public String cleanInputString(String var) throws Exception {
		if(var.matches("=") || var.matches("--")){
			return null;
		}else if (var.matches("^\\d\\d-[a-z|A-Z]{3}-\\d\\d\\d\\d$")) {
			return var;
		} else if (var.matches("^\\w+$")) {
			return var;
		}
		return null;
	}

	public String readSqlFromFile(String fileName, String schemaPattern, String schemaName) {
		InputStream in = getClass().getResourceAsStream("/resources/" + fileName);
		StringBuilder builder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			while ((line = br.readLine()) != null) {

				builder.append(line.replaceAll(schemaPattern, schemaName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("sql query : " + builder.toString());
		return builder.toString();
	}
}
