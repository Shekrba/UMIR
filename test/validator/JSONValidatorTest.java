package validator;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.powermock.api.mockito.PowerMockito.mock; 
import static org.powermock.api.mockito.PowerMockito.when; 
import static org.powermock.api.mockito.PowerMockito.mockStatic; 
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import app.Singleton;
import schema.validators.JSONValidator;


@RunWith(PowerMockRunner.class) 
@PrepareForTest(fullyQualifiedNames = "app.*") 
public class JSONValidatorTest {

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

	@Test(expected = java.lang.NullPointerException.class)
	public void testValidate() {
		JSONValidator v = new JSONValidator();
		v.validate("{}");
	}
	
	@Test
	public void test1Validate() {
		
		InputStream inputStream;
		Schema schema = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(new File("meta/schema.json")));
			JSONObject metaSchema = new JSONObject(new JSONTokener(inputStream));
			schema = SchemaLoader.load(metaSchema);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mockStatic(Singleton.class); 
		Singleton mock = mock(Singleton.class); 
		when(Singleton.getInstance()).thenReturn(mock);
		Locale.setDefault(new Locale("sr","RS"));
		when(Singleton.getInstance().getResourceBundle()).thenReturn(ResourceBundle.getBundle( "MessageResources.MessageResources", Locale.getDefault()));
		when(Singleton.getInstance().getMetaSchema()).thenReturn(schema);
		
		JSONValidator v = new JSONValidator();
		JSONObject o = new JSONObject("{ \"name\": \"Primer\", \"rootPackage\":{ \"packageName\": \"Primer\", \"children\":[ { \"packageName\":\"Podpaket\", \"children\" : [ ] }, { \"tableName\": \"Naseljeno mesto\", \"code\": \"NM\", \"attributes\":[ { \"name\": \"Drzava\", \"code\": \"DRZ_ID\", \"type\": { \"typeName\": \"char\", \"length\": 3 } }, { \"name\": \"Post. broj\", \"code\": \"NM_PB\", \"type\": { \"typeName\": \"char\", \"length\": 5 } }, { \"name\": \"Naziv\", \"code\": \"NM_NAZIV\", \"type\": { \"typeName\": \"varchar\", \"maxLength\": 128, \"preDef\":\"BP\", \"unique\": true, \"req\": true } }, { \"name\": \"Broj stanovnika\", \"code\": \"NM_STAN\", \"type\": { \"typeName\": \"double\", \"decimal\": 10, \"preDef\": 0 } }, { \"name\": \"Grad\", \"code\": \"NM_GRAD\", \"type\": { \"typeName\": \"boolean\" } } ], \"primaryKey\": [\"DRZ_ID\",\"NM_PB\"], \"foreignKeys\": [ { \"dependencyName\": \"Pripada drzavi\", \"referencingAttr\": [\"DRZ_ID\"], \"referencedTable\": \"DRZ\", \"referencedAttr\": [\"DRZ_ID\"] } ] }, { \"tableName\": \"Drzava\", \"code\": \"DRZ\", \"attributes\":[ { \"name\": \"Oznaka\", \"code\": \"DRZ_ID\", \"type\": { \"typeName\": \"char\", \"length\": 3 } }, { \"name\": \"Naziv\", \"code\": \"DRZ_NAZIV\", \"type\": { \"typeName\": \"varchar\", \"maxLength\": 128, \"req\": true, \"unique\": true } }, { \"name\": \"Glavni grad\", \"code\": \"NM_PB\", \"type\": { \"typeName\": \"char\", \"length\": 5, \"unique\": true } }, { \"name\": \"Drzava glavni grad\", \"code\": \"DRZ_ID_GG\", \"type\": { \"typeName\": \"char\", \"length\": 3 } } ], \"primaryKey\": [\"DRZ_ID\"], \"foreignKeys\": [ { \"dependencyName\": \"Glavni grad\", \"referencingAttr\": [\"DRZ_ID_GG\",\"NM_PB\"], \"referencedTable\": \"NM\", \"referencedAttr\": [\"DRZ_ID\",\"NM_PB\"] } ] }, { \"tableName\": \"Student\", \"code\": \"STU\", \"attributes\":[ { \"name\": \"Indeks\", \"code\": \"STU_INDEKS\", \"type\": { \"typeName\": \"varchar\", \"maxLength\": 15 } }, { \"name\": \"Datum rodjenja\", \"code\": \"STU_DATE\", \"type\": { \"typeName\": \"date\", \"preDef\": \"01.02.1996.\" } }, { \"name\": \"Ime\", \"code\": \"STU_IME\", \"type\": { \"typeName\": \"varchar\", \"maxLength\": 64, \"req\": true } }, { \"name\": \"Prezime\", \"code\": \"STU_PREZIME\", \"type\": { \"typeName\": \"varchar\", \"maxLength\": 64, \"req\": true } }, { \"name\": \"Drzava rodjenja\", \"code\": \"STU_DRZRODJ\", \"type\": { \"typeName\": \"char\", \"length\": 3, \"req\":true } }, { \"name\": \"Mesto rodjenja\", \"code\": \"STU_MESTORODJ\", \"type\": { \"typeName\": \"char\", \"length\": 5, \"req\":true } }, { \"name\": \"Drzava stanovanja\", \"code\": \"STU_DRZSTAN\", \"type\": { \"typeName\": \"char\", \"length\": 3 } }, { \"name\": \"Mesto stanovanja\", \"code\": \"STU_MESTOSTAN\", \"type\": { \"typeName\": \"char\", \"length\": 5 } } ], \"primaryKey\": [\"STU_INDEKS\"], \"foreignKeys\": [ { \"dependencyName\": \"Mesto rodjenja\", \"referencingAttr\": [\"STU_DRZRODJ\",\"STU_MESTORODJ\"], \"referencedTable\": \"NM\", \"referencedAttr\": [\"DRZ_ID\",\"NM_PB\"] }, { \"dependencyName\": \"Mesto stanovanja\", \"referencingAttr\": [\"STU_DRZSTAN\",\"STU_MESTOSTAN\"], \"referencedTable\": \"NM\", \"referencedAttr\": [\"DRZ_ID\",\"NM_PB\"] } ] } ] } }");
		assertEquals(v.validate(o.toString()).toString(), o.toString());
	}
}
