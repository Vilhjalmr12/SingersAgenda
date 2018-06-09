package view.exercises;

import java.io.File;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import domain.lists.TrManageExercises;
import view.main.MainMenu;
import zMyUtils.Messages.ErrorMessage;
import zMyUtils.Messages.RightMessage;

public class CtrlExercises {
	//Attributes
private TrManageExercises tr;
	
	//Constructors
	public CtrlExercises() {
		tr = new TrManageExercises();
	}
	
	//Messages
	protected void errorMessage(JFrame caller, String text) {
  		ErrorMessage msg = new ErrorMessage(caller, text);
  		msg.setAlwaysOnTop(true);
  		msg.setVisible(true);
  		caller.setEnabled(false);
  	}
  	
  	protected void errorMessage(JDialog caller, String text) {
  		ErrorMessage msg = new ErrorMessage(caller, text);
  		msg.setAlwaysOnTop(true);
  		msg.setVisible(true);
  		caller.setEnabled(false);
  	}
  	
  	protected void rightMessage(JDialog caller, String text) {
  		RightMessage msg = new RightMessage(caller , text);
  		msg.setAlwaysOnTop(true);
  		msg.setVisible(true);
  		caller.setEnabled(false);
  	}
  	
  	//Check only letters
  	protected boolean checkOnlyLetters(String text) {
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
	
	//Exercises
	public void setExercises(MainMenu caller) {
		try {
			String[] exercises;
			exercises = tr.getAll();
			if (exercises.length == 0) {
				errorMessage(caller, "No Hay Ejercicios Registrados");
			}
			caller.setMainDisplayTitle("Ejercicios");
			caller.setExercises(exercises);
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
	
	protected boolean check(JDialog caller, String name, String type) {
		try {
			return tr.check(name, type);
		} catch (IOException e) {
			errorMessage(caller, "Internal Error!");
		}
		return false;
	}
	
	protected boolean createNew(JDialog caller, String name, String type, String file, String notes) {
		try {
			return tr.addNew(name, type, file, notes);
		} catch (IOException e) {
			errorMessage(caller, "Internal Error!");
		}
		return false;
	}
	
	protected boolean remove(JDialog caller, String exercise, File file) {
		try {
			return tr.remove(exercise, file);
		} catch (IOException e) {
			errorMessage(caller, "Internal Error!");
		}
		return false;
	}
	
	protected String[] getInfo(JDialog caller, String exercise) {
		try {
			return tr.getInfo(exercise);
		} catch (IOException e) {
			errorMessage(caller, "Internal Error!");
		}
		return null;
	}
	
	protected boolean saveFile(JDialog caller, String name, String type, File f) {
		try {
			return tr.saveFile(name, type, f);
		} catch (IOException e) {
			errorMessage(caller, "Internal Error!");
		}
		return false;
	}
	
	protected void openFile(JDialog caller, File file) {
		try {
			if (!tr.openFile(file)) {
				errorMessage(caller, "Internal Error!");
			}
		} catch (IOException e) {
			errorMessage(caller, "Internal Error!");
		}
	}
	
	protected void removeOriginalFile(JDialog caller, File file) {
		try {
			if (!tr.removeOriginalFile(file)) {
				errorMessage(caller, "Internal Error!");
			}
		} catch (IOException e) {
			errorMessage(caller, "Internal Error!");
		}
	}
}
