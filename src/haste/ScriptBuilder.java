package haste;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ScriptBuilder {
	private static final Logger logger = Logger.getLogger(ScriptBuilder.class.getName());
	
	public String getScript(String fileName, String schemaPattern ,String schemaName){
		InputStream in = getClass().getResourceAsStream("/resources/"+fileName);
		StringBuilder builder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))){
			   String line = null;
			   while (( line = br.readLine()) != null){
				   
				   builder.append(line.replaceAll(schemaPattern,schemaName));
			   }
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return builder.toString();
	}
}
