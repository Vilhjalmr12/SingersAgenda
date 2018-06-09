package view.students;

import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import domain.lists.TrManageStudents;
import view.main.MainMenu;
import zMyUtils.Messages.ErrorMessage;
import zMyUtils.Messages.RightMessage;

public class CtrlElev {
	//Attributes
	private TrManageStudents tr;
	
	//Constructors
	public CtrlElev() {
		tr = new TrManageStudents();
	}
	
	//Messages
  	public void errorMessage(JFrame caller, String text) {
  		ErrorMessage msg = new ErrorMessage(caller, text);
  		msg.setAlwaysOnTop(true);
  		msg.setVisible(true);
  		caller.setEnabled(false);
  	}
  	
  	public void errorMessage(JDialog caller, String text) {
  		ErrorMessage msg = new ErrorMessage(caller, text);
  		msg.setAlwaysOnTop(true);
  		msg.setVisible(true);
  		caller.setEnabled(false);
  	}
  	
  	public void rightMessage(JDialog caller, String text) {
  		RightMessage msg = new RightMessage(caller , text);
  		msg.setAlwaysOnTop(true);
  		msg.setVisible(true);
  		caller.setEnabled(false);
  	}
  	
  	//Check only letters
  	public boolean checkOnlyLetters(String text) {
  		boolean correct = true;
		for (int i=0; i<text.length(); ++i) {
			if (!((text.charAt(i) >= 'a' && text.charAt(i) <= 'z') 
					|| (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') 
					|| text.charAt(i) == ' ' || text.charAt(i) == 'ñ'
					|| text.charAt(i) == 'Ñ' || text.charAt(i) == 'ç'
					|| text.charAt(i) == 'Ç' || text.charAt(i) == 'á'
					|| text.charAt(i) == 'Á' || text.charAt(i) == 'é'
					|| text.charAt(i) == 'É' || text.charAt(i) == 'í'
					|| text.charAt(i) == 'Í' || text.charAt(i) == 'ó'
					|| text.charAt(i) == 'Ó' || text.charAt(i) == 'ú'
					|| text.charAt(i) == 'Ú' || text.charAt(i) == 'ä'
					|| text.charAt(i) == 'Ä' || text.charAt(i) == 'ë'
					|| text.charAt(i) == 'Ë' || text.charAt(i) == 'ï'
					|| text.charAt(i) == 'Ï' || text.charAt(i) == 'ö'
					|| text.charAt(i) == 'Ö' || text.charAt(i) == 'ü'
					|| text.charAt(i) == 'Ü' || text.charAt(i) == 'å')) {
						correct = false;
			}
		}
		return correct;
  	}
	
	//Students
	public void setStudents(MainMenu caller) {
		try {
			String[] elev;
			elev = tr.getAll();
			if (elev.length == 0) {
				errorMessage(caller, "No Hay Alumnos Registrados");
			}
			caller.setMainDisplayTitle("Alumnos");
			caller.setStudents(elev);
		} catch (IOException e) {
			errorMessage(caller, "Internal Error!");
		}
	}
	
	protected int getNumber(JDialog caller) {
		try {
			return (tr.getNumber());
		} catch (IOException e) {
			errorMessage(caller, "Internal Error!");
			return -1;
		}
	}
	
	protected boolean check(JDialog caller, String name) {
		try {
			return tr.check(name);
		} catch (IOException e) {
			errorMessage(caller, "Internal Error!");
		}
		return false;
	}
	
	protected boolean createNew(JDialog caller, String name, String telf, String mail,String range, String notes) {
		try {
			return tr.addNew(name, telf, mail, range, notes);
		} catch (IOException e) {
			errorMessage(caller, "Internal Error!");
		}
		return false;
	}
	
	protected boolean remove(JDialog caller, String info) {
		try {
			return tr.remove(info);
		} catch (IOException e) {
			errorMessage(caller, "Internal Error!");
		}
		return false;
	}
	
	protected String[] getInfo(JDialog caller, String name) {
		try {
			return tr.getInfo(name);
		} catch (IOException e) {
			errorMessage(caller, "Internal Error!");
		}
		return null;
	}
}
