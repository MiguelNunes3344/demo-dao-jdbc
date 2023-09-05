package application;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		List<Seller> list = new ArrayList<>();
		SellerDao sellerDao = DaoFactory.createSeller();
		try {
			//FIND ALL
			list = sellerDao.findAll();
			for (Seller x: list) {
				System.out.println(x);
			}
			
			//FIND BY DEPARTMENT
			list = sellerDao.findByDepartment(2);
			for(Seller x: list) {
				System.out.println(x);
			}
			
			//FIND BY ID
			
			Seller sel = sellerDao.findById(3);
			System.out.println(sel);
			
			//INSERT
			
			Seller sl = new Seller(null,"Geova","Geova@gmail.com",new Date(),5000.0,new Department(2,null));
			sellerDao.insert(sl);
			
			//UPDATE
			
			sl = sellerDao.findById(1);
			sl.setName("ChangedName");
			sellerDao.update(sl);
			
			
			//DELETE
			
			sellerDao.deleteById(24);
		}
		finally {
			DB.closeConnection();
		}
		
		
	}

}
