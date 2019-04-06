package app.database;

import java.util.HashMap;

import javax.swing.JComponent;

/**
 * @author Milan Skrbic
 *
 *Ovo je interfejs koji je napravljen da bi ga nasledili svi hendleri kad bi se implementirali. Poenta je u
 *tome da aplikacija nije ogranicena samo na jedan tip baze
 */
public interface DBConnectionHandler {
	
	
	public abstract void insertData();
	public abstract boolean addData(HashMap<String, JComponent> comps);
	public abstract boolean updateData(HashMap<String, JComponent> comps);
	public abstract boolean deleteData(Object objects[]);
	
}
