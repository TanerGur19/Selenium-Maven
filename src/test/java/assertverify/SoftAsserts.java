package assertverify;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAsserts {

	SoftAssert soeftAssert= new SoftAssert();  //  its a  class. when assertion fails 
	
	@Test
	public void test1() {
		int i= 100;
		int j= 200;
		
		System.out.println("First Assertion: " );
		soeftAssert.assertEquals(i, j);
		
		System.out.println("Second Assertion: " );
		soeftAssert.assertNotEquals(i,j);
		
		System.out.println("Third Assertion: " );
		soeftAssert.assertTrue(i> j);
		
		soeftAssert.assertAll();
		
		
		
		
		
	}
	
}
