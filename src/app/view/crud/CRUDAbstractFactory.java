package app.view.crud;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import app.Singleton;
import app.model.ForeignKey;
import app.model.Table;
import app.model.attribute.Attribute;
import app.view.boolComponent.BoolComponent;
import app.view.charComponent.CharComponent;
import app.view.dateComponent.DateComponent;
import app.view.intComponent.IntComponent;
import app.view.numberComponent.NumberComponent;
import app.view.stringComponent.StringComponent;

/*
 * @author Igor Antoloviæ
 * Klasa abstract factory za dijaloge za CRUD operacije.
 * */
public abstract class CRUDAbstractFactory {

	protected HashMap<String,JPanel> fkComps=new HashMap<String,JPanel>();
	protected HashMap<String, JComponent> fkVals=new HashMap<String,JComponent>();
	protected ArrayList<Attribute> attributes;
	protected ArrayList<IValid> comps;
	protected HashMap<String, JComponent> actionComps=new HashMap<String,JComponent>();
	protected JButton zoom;
	
	/*
	 * Proverava da li su svi unosi validni prilikom potvrde unosa
	 * */
	public boolean jeValidan(){
		for(IValid i : comps){
			if(!i.jeValidan())
				return false;
		}
		return true;
	}
	
	public HashMap<String, JComponent> getActionComps() {
		return actionComps;
	}

	public void setActionComps(HashMap<String, JComponent> actionComps) {
		this.actionComps = actionComps;
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}

	public ArrayList<IValid> getComps() {
		return comps;
	}

	public void setComps(ArrayList<IValid> comps) {
		this.comps = comps;
	}

	/*
	 * Kreira sve komponente za unos i vraca vertikalno uredjen panel sa njima.
	 * */
	abstract public JPanel generateComps();
	
	public CRUDAbstractFactory(ArrayList<Attribute> attributes) {
		super();
		this.attributes = attributes;
	}
	
	/*
	 * Proverava da li je komponenta od stranog kljuca i grupise komponente istog stranog kljuca
	 * */
	protected JPanel CheckFK(Attribute a) {
		Table table = Singleton.getInstance().getMainFrame().getShowingParrent();
		
		ArrayList<ForeignKey> fk = table.getForeignKeys();
		
		for (ForeignKey foreignKey : fk) {
			if(foreignKey.getReferencinAttr().contains(a.getCode())) {
					JPanel panel=fkComps.get(foreignKey.getDependencyCode());
					if(panel!=null){
						JPanel jp=(JPanel) panel.getComponent(1);
						jp.add(Box.createVerticalStrut(10));
						return jp;
					}else {
						panel=new JPanel();
						BoxLayout bl=new BoxLayout(panel,BoxLayout.X_AXIS);
						panel.setLayout(bl);
						panel.add(Box.createHorizontalGlue());
						JPanel p=new JPanel();
						BoxLayout bl1=new BoxLayout(p,BoxLayout.Y_AXIS);
						p.setLayout(bl1);
						p.add(Box.createVerticalStrut(10));
						panel.add(p);
						zoom=new JButton();
						zoom.setIcon(new ImageIcon("images/zoom.png"));
						
						zoom.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								ZoomDialog zd=new ZoomDialog(foreignKey,fkVals);
								
							}
						});
						
						
						panel.add(zoom);
						panel.add(Box.createHorizontalGlue());
						Border blackline = BorderFactory.createLineBorder(Color.black);
						panel.setBorder(blackline);
						//panel.add(new JButton("Zoom"));
						fkComps.put(foreignKey.getDependencyCode(), panel);
						return p;
					}
			}
		}
		return null;
	}
	

	abstract public StringComponent createStringComponent(Attribute a);
	abstract public NumberComponent createNumberComponent(Attribute a);
	abstract public DateComponent createDateComponent(Attribute a);
	abstract public BoolComponent createBoolComponent(Attribute a);
	abstract public IntComponent createIntComponent(Attribute a);
	abstract public CharComponent createCharComponent(Attribute a);
	
}
