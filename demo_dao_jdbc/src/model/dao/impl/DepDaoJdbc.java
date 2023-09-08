package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepDaoJdbc implements DepartmentDao {

	private Connection conn;
	
	public DepDaoJdbc(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void insert(Department dep) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO department(Name) VALUES (?)");
			
			ps.setString(1, dep.getName());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Failed to insert Department in Database: "+ e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void update(Department dep) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE department SET Name = ? Where Id = ?");
			ps.setString(1, dep.getName());
			ps.setInt(2, dep.getId());
			
			ps.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Failed to UPDATE Department in Database: "+ e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			ps.setInt(1, id);
			
			ps.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Failed to DELETE Department in Database: "+ e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Department dep = new Department(rs.getInt("Id"),rs.getString("Name"));
				return dep;
			}
		}
		catch (SQLException e) {
			System.out.println("Failed to FETCH Department in Database: "+ e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		return null;
		
	}
	
	@Override
	public List<Department> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<Department>();
		try {
			ps = conn.prepareStatement("SELECT * FROM department");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Department dep = new Department(rs.getInt("Id"),rs.getString("Name"));
				list.add(dep);
			}
			return list;
		}
		catch (SQLException e) {
			System.out.println("Failed to FETCH Department in Database: "+ e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
		return null;
	}
	
	
}
