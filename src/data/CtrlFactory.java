package data;

import data.lists.ListsController;
import data.logIn.PassController;

public class CtrlFactory {
	//Getters
	public PassController getPassController() {return new PassController();}
	
	public ListsController getListsController() {return new ListsController();}
			
}
