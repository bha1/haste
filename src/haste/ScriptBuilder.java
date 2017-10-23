package haste;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ScriptBuilder {
	private static final Logger logger = Logger.getLogger(ScriptBuilder.class.getName());
	
	public String getScript(String fileName, String schemaPattern ,String schemaName, String[] vars){
		
		try{
			if(vars != null){
				for(int i=0;i<vars.length;i++){
					vars[i]=cleanInputString(vars[i]);
				}
			}
		}
		catch(Exception e){
			logger.fatal("failed while parsing date params");
			e.printStackTrace();
		}
		
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
		logger.info("sql query : " + builder.toString());
		return builder.toString();
	}
	
	public String cleanInputString(String var) throws Exception{
		if(var.matches("^\\d\\d-[a-z|A-Z]{3}-\\d\\d\\d\\d$")){
			return var;
		}
		return null;
	}
}
