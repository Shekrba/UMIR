package schema.parsers;

import app.model.Schema;

public interface Parser {

	abstract public Schema parse(Object o);
	
}
