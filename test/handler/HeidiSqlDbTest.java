package handler;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.HashMap;

import javax.swing.JComponent;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import app.Singleton;
import app.database.HeidiSqlDb;

public class HeidiSqlDbTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddData() {
		
		HeidiSqlDb heidi = new HeidiSqlDb();
		
		assertTrue(heidi.addData(new HashMap<String, JComponent>()) == false);
	}

	@Test
	public void testUpdateData() {
		HeidiSqlDb heidi = new HeidiSqlDb();
		
		assertTrue(heidi.updateData(new HashMap<String, JComponent>()) == false);
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void testDeleteData() {
		HeidiSqlDb heidi = new HeidiSqlDb();
		
		heidi.deleteData(new Object [0]);
	}

}
