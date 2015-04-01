package test.views;

import java.util.ArrayList;

import org.jdesktop.swingx.JXTreeTable;
import org.junit.Assert;

import views.DataTable;

public class TestDataTable {

	@org.junit.Test
	public void TestCreateTreeTable() {
		ArrayList<String[]> content = new ArrayList<String[]>();
		String[] arr2 = new String[2];
		arr2[0] = "t1";
		arr2[1] = "t2";
		content.add(arr2);
		DataTable t=new DataTable(content);
		JXTreeTable treeTbl = t.createTreeTable();
		Assert.assertNotNull(treeTbl);
		
	}
	
}
