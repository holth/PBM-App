package test.views;

import static org.junit.Assert.*;

import views.AddExpensePanel;
import views.ExpensesFrame;
import views.ViewExpensesPanel;

public class TestExpensesFrame {
	
	@org.junit.Test
	public void testExpensesFrame()
	{
		ExpensesFrame ef = new ExpensesFrame("abc");
		
		String s = ef.getUsername();
		assertSame("abc",s);
		
		ViewExpensesPanel vep = ef.getExpensesPanel();
		assertNotNull(vep);
		
		AddExpensePanel aep = ef.getPurchasePanel();
		assertNotNull(aep);
		
	    aep = ef.getBillPanel();
		assertNotNull(aep);
		
		s = ef.getFilterCategory();
		assertNotNull(s);
		
		s = ef.getFilterType();
		assertNotNull(s);
		
		s = ef.getFilterStatus();
		assertNotNull(s);
		
	}

}
