package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	void insert();
	void update();
	void deleteById(Integer id);
	void findById(Integer id);
	List<Department> findAll();
}
