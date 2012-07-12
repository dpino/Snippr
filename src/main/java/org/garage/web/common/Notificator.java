package org.snippr.web.common;

import org.zkoss.zul.Label;

/**
 * 
 * @author Diego Pino García <dpino@igalia.com>
 *
 */
public class Notificator {
	
	private static final String INFO = "info";
	
	private static final String ERROR = "error";
	
	private Label label;
	
	public static Notificator create(Label label) {
		return new Notificator(label);
	}
	
	private Notificator(Label label) {
		this.label = label;
	}
	
	public void info(String msg) {
		showMessage(msg, INFO);		
	}
	
	public void error(String msg) {
		showMessage(msg, ERROR);
	}
	
	public void showMessage(String msg, String sclass) {
		label.setValue(msg);
		label.setClass(sclass);
		label.setVisible(true);
	}
	
}