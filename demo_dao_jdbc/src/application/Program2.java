package application;

import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		try {
			//INSERT
			
			DepartmentDao dep = DaoFactory.createDep();
			Department depIn = new Department(9,"Foods");
			dep.insert(depIn);
			
			//UPDATE
			depIn.setName("Items");
			dep.update(depIn);
			
			//DELETE
			dep.deleteById(10);
			
			//FINDBYID
			Department department = dep.findById(3);
			System.out.println(department);
			//FINDALL
			
			List<Department> list = new ArrayList<Department>();
			list = dep.findAll();
			for(Department x: list) {
				System.out.println(x);
			}
			
		}
		finally {
			DB.closeConnection();
		}
		
		
	}

}
