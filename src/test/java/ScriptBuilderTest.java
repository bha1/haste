package test.java;

import org.junit.Assert;
import org.junit.Test;

import haste.ScriptBuilder;

public class ScriptBuilderTest {
	@Test
	public void testCleanInputString(){
		ScriptBuilder builder = new ScriptBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("12-oct-2017"), "12-oct-2017");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCleanInputString1(){
		ScriptBuilder builder = new ScriptBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("12--oct-2017"),null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCleanInputString2(){
		ScriptBuilder builder = new ScriptBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("12-oct-2017 OR 1=1"), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testCleanInputString3(){
		ScriptBuilder builder = new ScriptBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("12-oct-2017;delete"), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCleanInputString4(){
		ScriptBuilder builder = new ScriptBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("12-09-2017"), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCleanInputString5(){
		ScriptBuilder builder = new ScriptBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("12-oc_-2017"), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
