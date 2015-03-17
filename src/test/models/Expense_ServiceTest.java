package test.models;

import static org.junit.Assert.*;
import models.Account_Service;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 *
 */
public class Expense_ServiceTest {
	
	private Account_Service accser;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		accser = new Account_Service();
	}

	@Test
	public void testGetAccountIdByUsernameAndType() {
		
		int Expected_id = 1;
		int Actual_id = 0;
		try {
			Actual_id = accser.getAccountIdByUsernameAndType(1, "CASH");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(Expected_id, Actual_id);
	}

	@Test
	
	public void testGetAccountIdByBankAccountIdAndType() {

		int Expected_id = 1;
		int Actual_id = 0;
		try {
			Actual_id = accser.getAccountIdByBankAccountIdAndType(0, "CASH");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(Expected_id, Actual_id);
	}

	@Test
	public void testGetPaymentTypeByAccountId() {

		String Expected_type ="CASH";
		String Actual_type = "";
		try {
			 Actual_type = accser.getPaymentTypeByAccountId(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(Expected_type, Actual_type);
	}

}
