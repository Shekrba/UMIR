package schema.abstractFactory;

import javax.swing.JEditorPane;

import app.Singleton;
import schema.handlers.Handler;
import schema.handlers.JSONHandler;
import schema.parsers.JSONParser;
import schema.parsers.Parser;
import schema.validators.JSONValidator;
import schema.validators.Validator;

public class JSONFactory extends AbstractFactory{

	@Override
	public Parser createParser() {
		return new JSONParser();
	}

	@Override
	public Validator createValidator() {
		return new JSONValidator();
	}

	@Override
	public Handler createHandler(JEditorPane jep) {
		return new JSONHandler(jep);
	}


}
