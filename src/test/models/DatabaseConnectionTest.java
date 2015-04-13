package models;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseConnectionTest {
    private DatabaseConnection dc;

	@Test
	public void testGetInstance() {
		try{
		DatabaseConnection.getInstance();
		}catch(Exception e){
			Assert.fail();
		}
	}

	@Test
	public void testGetConnection() {
		try{
		DatabaseConnection.getInstance().getConnection();
	}catch(Exception e){
		Assert.fail();
	}
	}
    @Test
    public void testcloseConnection(){
    	try{
    	DatabaseConnection.getInstance().closeConnection();
    }catch(Exception e){
		Assert.fail();
	}
    }

}
