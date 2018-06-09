package domain.lists;

import java.io.IOException;
import data.CtrlFactory;
import data.lists.ListsController;
import view.main.JarPath;

public class TrManageStudents {
	//Attributes
	private ListsController ctrl;
	String path = JarPath.getPath() + "Students/";
	
	//Constructor
	public TrManageStudents() {
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
	
	public boolean check(String name) throws IOException {
		return ctrl.check(path, name);
	}
	
	public boolean addNew(String name, String telf, String mail, String range, String notes) throws IOException {
		String info;
		info = name.trim() + " - " + telf.trim() + " - " + mail.trim() + " - " + range.trim() + " - " + notes.trim();
		return ctrl.addNew(path, name, info);
	}
	
	public boolean remove(String name) throws IOException {
		return ctrl.remove(path, name);
	}
	
	public String[] getInfo(String name) throws IOException {
		return ctrl.getInfo(path, name).split(" - ");
	}
}
