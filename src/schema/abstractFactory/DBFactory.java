package schema.abstractFactory;

import javax.swing.JEditorPane;

import schema.handlers.DBHandler;
import schema.handlers.Handler;
import schema.parsers.DBParser;
import schema.parsers.Parser;
import schema.validators.DBValidator;
import schema.validators.Validator;

public class DBFactory extends AbstractFactory{

	@Override
	public Parser createParser() {
		// TODO Auto-generated method stub
		return new DBParser();
	}

	@Override
	public Validator createValidator() {
		// TODO Auto-generated method stub
		return new DBValidator();
	}

	@Override
	public Handler createHandler(JEditorPane jep) {
		// TODO Auto-generated method stub
		return new DBHandler(jep);
	}

}
