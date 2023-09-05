package application;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJdbc;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		List<Seller> list = new ArrayList<>();
		SellerDao sellerDao = DaoFactory.createSeller();
		
		list = sellerDao.findAll();
		for (Seller x: list) {
			System.out.println(x);
		}
		System.out.println("FIND BY DEPARTMENT");
		list = sellerDao.findByDepartment(2);
		for(Seller x: list) {
			System.out.println(x);
		}
		
		System.out.println("INSERT INTO DATABASE");
		Seller sl = new Seller(null,"Geova","Geova@gmail.com",new Date(),5000.0,new Department(2,null));
		sellerDao.insert(sl);
		
		System.out.println("UPDATE INTO DATABASE");
		sl = sellerDao.findById(1);
		sl.setName("ChangedName");
		sellerDao.update(sl);
		
		
		list = sellerDao.findAll();
		for (Seller x: list) {
			System.out.println(x);
		}
	}

}
