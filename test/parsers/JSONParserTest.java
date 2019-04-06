package parsers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import app.model.ForeignKey;
import app.model.Schema;
import app.model.Table;
import app.model.attribute.Attribute;
import app.model.attribute.AttributeType;
import app.model.attribute.StringType;
import schema.parsers.FirstPassTable;
import schema.parsers.JSONParser;

public class JSONParserTest {

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

	@Test(expected=org.json.JSONException.class)
	public void testParse() {
		JSONObject o=new JSONObject("{}");
		JSONParser p=new JSONParser();
		p.parse(o);
	}

	@Test
	public void testCheckParserErrors() {
		JSONParser p=new JSONParser();
		HashMap<String,Attribute> attrs1=new HashMap<String,Attribute>();
		attrs1.put("ime", new Attribute("ime","ime",new StringType("varchar",128,null,true,false)));
		FirstPassTable fpt=new FirstPassTable("Aaa","a",attrs1,new ArrayList<String>(),new ArrayList<ForeignKey>());
		Table tRefced=new Table(fpt);
		
		
		FirstPassTable fptb=new FirstPassTable("Bbb","b",attrs1,new ArrayList<String>(),new ArrayList<ForeignKey>());
		Table t=new Table(fptb);
		
		ArrayList<String> rinAttr=new ArrayList<String>();
		rinAttr.add("ime");
		ArrayList<String> redAttr=new ArrayList<String>();
		redAttr.add("sss");
		String redTable="a";
		ForeignKey fk=new ForeignKey(rinAttr,"sadsa",redTable,redAttr,"sadsa");
		HashMap<String,Table> allTables=new HashMap<String,Table>();
		allTables.put(tRefced.getCode(), tRefced);
		allTables.put(t.getCode(),t);
		p.setAllTables(allTables);
		p.checkParserErrors();
		assertTrue(p.getErr()==false);
	}

	

	@Test(expected=org.json.JSONException.class)
	public void testHajhohajho() {
		JSONArray o=new JSONArray("[{}]");
		JSONParser p=new JSONParser();
		p.hajhohajho(o);
	}

}
