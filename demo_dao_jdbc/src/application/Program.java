package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Department dt = new Department(3,"Computers");
		Seller sl = new Seller(2,"Sergio","Sergio@gmail.com",new Date(),2000.0,dt);
		SellerDao sd = DaoFactory.createSeller();
		System.out.println(sd);
	}

}
