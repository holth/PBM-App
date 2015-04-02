package test.views;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.jdesktop.swingx.JXTreeTable;
import org.junit.*;

import views.DataTable;

public class DataTableTest {
	
	DataTable table;
	
	@Before
	public void setUp() {
		ArrayList<String[]> content = new ArrayList<String[]>();
		String[] arr2 = new String[2];
		arr2[0] = "t1";
		arr2[1] = "t2";
		content.add(arr2);
		
		table = new DataTable(content);
	}
	
	/**
	 * Test the createTreeTable() method of DataTable class.
	 * The method must return a JXTreeTable.
	 */
	@Test
	public void TestCreateTreeTable() {
		JXTreeTable treeTable = table.createTreeTable();
		assertNotNull(treeTable);
	}
	
}
