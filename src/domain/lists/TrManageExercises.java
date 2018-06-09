package domain.lists;

import java.io.File;
import java.io.IOException;
import data.CtrlFactory;
import data.lists.ListsController;
import view.main.JarPath;

public class TrManageExercises {
	//Attributes
	private ListsController ctrl;
	String path = JarPath.getPath() + "Exercises/";
	
	//Constructor
	public TrManageExercises() {
		CtrlFactory fac = new CtrlFactory();
		ctrl = fac.getListsController();
	}
	
	//Songs Management
	public String[] getAll() throws IOException {
		return ctrl.getAll(path);
	}
	
	public int getNumber() throws IOException {
		return ctrl.getNumber(path);
	}
	
	public boolean check(String title, String type) throws IOException {
		String name = type.trim() + " - " + title.trim();
		return ctrl.check(path, name);
	}
	
	public boolean addNew(String title, String type, String file, String notes) throws IOException {
		String info;
		info = title.trim() + " - " + type.trim() + " - " + file.trim() +" - " + notes.trim();
		String name = type.trim() + " - " + title.trim();
		return ctrl.addNew(path, name, info);	
	}
	
	public boolean remove(String name, File file) throws IOException {
		if (!(file == null)) {
			if (ctrl.removeFile(path, file)) {
				return ctrl.remove(path, name);
			} else {
				return false;
			}
		}
		return ctrl.remove(path, name);
	}
	
	public String[] getInfo(String name) throws IOException {
		return ctrl.getInfo(path, name).split(" - ");
	}
	
	public boolean saveFile(String name, String type, File f) throws IOException {
		name = type.trim() + " - " + name.trim();
		return ctrl.saveFile(path, name, f);
	}
	
	public boolean openFile(File file) throws IOException {
		return ctrl.openFile(path, file);
	}
	
	public boolean removeOriginalFile(File file) throws IOException {
		return ctrl.removeFile(path, file);
	}
}
