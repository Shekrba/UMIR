package schema.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.json.JSONObject;

import app.Singleton;
import app.model.Schema;
import app.view.MainFrame;
import app.view.MenuBar;
import app.view.ToolBar;
import app.view.TreePanel;
import schema.abstractFactory.AbstractFactory;
import schema.controller.actions.ValidateSchemaAction;
import schema.handlers.Handler;

import schema.parsers.Parser;
import schema.validators.Validator;


public class SchemaDialog extends JDialog implements Observer{
	
	private Validator validator;
	private Parser parser;
	private Handler handler;
	private JEditorPane tp;
	private SchemaToolBar toolbar;
	private SchemaMenuBar menubar;
	private JButton okButton;
	
	
	
	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public SchemaToolBar getToolbar() {
		return toolbar;
	}

	public void setToolbar(SchemaToolBar toolbar) {
		this.toolbar = toolbar;
	}

	public SchemaMenuBar getMenubar() {
		return menubar;
	}

	public void setMenubar(SchemaMenuBar menubar) {
		this.menubar = menubar;
	}

	public SchemaDialog() {
		super();
	}
	
	public void initDialog(AbstractFactory factory) {
		
		this.setTitle(Singleton.getInstance().getResourceBundle().getString("metaschema editor"));
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		menubar=new SchemaMenuBar();
		this.setJMenuBar(menubar);
		
		toolbar=new SchemaToolBar();
	    add(toolbar, BorderLayout.NORTH);
	   
	    
	    JScrollPane jsp=new JScrollPane();
	    tp=new JTextPane();
	    tp.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
	    tp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    jsp.setViewportView(tp);
	    this.add(jsp);
	    this.setParams(factory);
	    //tp.setText(handler.getText());
		
		
		okButton=new JButton(Singleton.getInstance().getResourceBundle().getString("confirm"));
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Object obj= validator.validate(handler.getText());
				if(obj!=null) {
					Schema sc=parser.parse(obj);
					//JOptionPane.showMessageDialog(null, "Bravo!");
					if(sc==null) {
						JOptionPane.showMessageDialog(null, "Parsing error!");
					}else {
						Singleton.getInstance().setSchema(sc);
						MainFrame mf=Singleton.getInstance().getMainFrame();
						mf.getTree().initTree();
						mf.revalidate();
						mf.repaint();
						SchemaDialog.this.setVisible(false);
					}
				}
				
			}
			
		});
		this.add(okButton,BorderLayout.SOUTH);
		
		this.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				tp.setText(Singleton.getInstance().getSchema().getMetaCode());
			}
			
		});
		
		this.setVisible(true);
		
		
	}

	public void setParams(AbstractFactory factory) {
		setValidator(factory.createValidator());
		setParser(factory.createParser());
		setHandler(factory.createHandler(tp));
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		if(validator==null) {
			tp.setText("");
			tp.setEnabled(false);
		}else
			tp.setEnabled(true);
		this.validator = validator;
	}

	public Parser getParser() {
		return parser;
	}

	public void setParser(Parser parser) {
		if(parser==null) {
			tp.setText("");
			tp.setEnabled(false);
		}else
			tp.setEnabled(true);
		this.parser = parser;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		if(handler==null) {
			tp.setText("");
			tp.setEnabled(false);
		}else
			tp.setEnabled(true);
		this.handler = handler;
	}

	public JEditorPane getTp() {
		return tp;
	}

	public void setTp(JEditorPane tp) {
		this.tp = tp;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String text=(String) arg1;
		tp.setText(text);
		
	}
	
}
