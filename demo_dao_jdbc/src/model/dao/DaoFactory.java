package model.dao;

import db.DB;
import model.dao.impl.DepDaoJdbc;
import model.dao.impl.SellerDaoJdbc;

public class DaoFactory {

	
	public static SellerDao createSeller() {
		return new SellerDaoJdbc(DB.getConnection());
	}
	public static DepartmentDao createDep() {
		return new DepDaoJdbc(DB.getConnection());
	}
}
