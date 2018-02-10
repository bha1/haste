/**
 * @author Peeyush Bhawan
 * https://github.com/bha1
 * https://github.com/rogueagent
 *
 *
 */

package test.java;

import org.junit.Assert;
import org.junit.Test;

import haste.PreparedStatementBuilder;

public class ScriptBuilderTest {
	@Test
	public void testCleanInputString(){
		PreparedStatementBuilder builder = new PreparedStatementBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("12-oct-2017"), "12-oct-2017");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCleanInputString1(){
		PreparedStatementBuilder builder = new PreparedStatementBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("12--oct-2017"),null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCleanInputString2(){
		PreparedStatementBuilder builder = new PreparedStatementBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("12-oct-2017 OR 1=1"), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testCleanInputString3(){
		PreparedStatementBuilder builder = new PreparedStatementBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("12-oct-2017;delete"), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCleanInputString4(){
		PreparedStatementBuilder builder = new PreparedStatementBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("12-09-2017"), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCleanInputString5(){
		PreparedStatementBuilder builder = new PreparedStatementBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("12-oc_-2017"), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCleanInputString6(){
		PreparedStatementBuilder builder = new PreparedStatementBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("ODX_SIT4"), "ODX_SIT4");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCleanInputString7(){
		PreparedStatementBuilder builder = new PreparedStatementBuilder();
		try {
			Assert.assertEquals(builder.cleanInputString("ODX_SIT4--"), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
