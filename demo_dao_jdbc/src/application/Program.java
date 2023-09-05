package application;

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
		
	}

}
