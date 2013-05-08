package control;

import dao.DAOEngine;

public class DAOControl {
	
	private DAOEngine daoEngine;
	
	public DAOControl() {
		daoEngine =  new DAOEngine();
	}
	
	//getters
	public DAOEngine getDaoEngine() {
		return daoEngine;
	}
	
}
