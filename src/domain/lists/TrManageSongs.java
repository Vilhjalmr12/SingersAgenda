package domain.lists;

import java.io.IOException;
import data.CtrlFactory;
import data.lists.ListsController;
import view.main.JarPath;

public class TrManageSongs {
	//Attributes
	private ListsController ctrl;
	String path = JarPath.getPath() + "Songs/";
	
	//Constructor
	public TrManageSongs() {
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
	
	public boolean check(String title, String artist) throws IOException {
		String name = artist.trim() + " - " + title.trim();
		return ctrl.check(path, name);
	}
	
	public boolean addNew(String title, String artist, String range, String notes) throws IOException {
		String info;
		info = title.trim() + " - " + artist.trim() + " - " + range.trim() + " - " + notes.trim();
		String name = artist.trim() + " - " + title.trim();
		return ctrl.addNew(path, name, info);
	}
	
	public boolean remove(String name) throws IOException {
		return ctrl.remove(path, name);
	}
	
	public String[] getInfo(String name) throws IOException {
		return ctrl.getInfo(path, name).split(" - ");
	}
}
