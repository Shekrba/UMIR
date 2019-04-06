package schema.abstractFactory;

import javax.swing.JEditorPane;

import schema.handlers.Handler;
import schema.parsers.Parser;
import schema.validators.Validator;

public abstract class AbstractFactory {

	abstract public Parser createParser();
	abstract public Validator createValidator();
	abstract public Handler createHandler(JEditorPane jep);
}
