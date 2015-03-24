package test.views;

import static org.junit.Assert.*;

import org.jdesktop.swingx.JXTreeTable;

import views.ViewExpensesPanel;

public class TestViewExpensesPanel {
	
	@org.junit.Test
	public void TestGetTable() {
		ViewExpensesPanel v =new ViewExpensesPanel("abc", "all", "credit", "unpaid");
		JXTreeTable testTable = v.getTable();
		assertNotNull(testTable);
	}
	
	

}
