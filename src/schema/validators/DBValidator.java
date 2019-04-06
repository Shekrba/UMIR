package schema.validators;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBValidator implements Validator{

	@Override
	public Object validate(String s) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim8-2","psw-2018-tim8-2","tim8-213636262");
			Statement stmt=conn.createStatement();
			for (String line : s.split(System.getProperty("line.separator"))) {
				stmt.execute(line);
			}
			
			stmt.close();
		} catch (Exception ex) {
			conn=null;
			ex.printStackTrace();
		}
		
		return conn;
	}

}
