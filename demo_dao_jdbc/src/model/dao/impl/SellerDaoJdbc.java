package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJdbc implements SellerDao{
	
	private Connection conn;
	
	public SellerDaoJdbc(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public void insert(Seller obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO seller(Name,Email,BirthDate,BaseSalary,DepartmentId) VALUES(?,?,?,?,?)");
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getEmail());
			ps.setDate(3, obj.getBirthDate());
			ps.setDouble(4, obj.getBaseSalary());
			ps.setInt(5, obj.get);
		}
		catch (SQLException e) {
			System.out.println("Falied to insert into Database: "+ e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Seller obj) {
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			throw new DbException("Error deleting user: "+e.getMessage());
		} 
		finally {
			DB.closeStatement(ps);
		}
		
	}
	

	@Override
	public Seller findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT seller.*, department.Name FROM seller "
					+ "INNER JOIN department ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Department dep = instanciateDep(rs);
				Seller sl = instanciateSeller(rs, dep);
				return sl;
			}
			return null;
			

		} catch (SQLException e) {
			throw new DbException("Failed insert into Database: "+ e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public List<Seller> findAll() {
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Seller> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement("Select seller.*, Department.Name FROM seller "
					+ "INNER JOIN department ON seller.DepartmentId = department.Id");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Department dep = instanciateDep(rs);
				Seller seller = instanciateSeller(rs,dep);
				list.add(seller);
					
				
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException("Error fetching all users: "+ e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}

	private Seller instanciateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(dep);
		return seller;
	}

	private Department instanciateDep(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("Department.Name"));
		return dep;
	}


	@Override
	public List<Seller> findByDepartment(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT seller.*, Department.Name "
					+ "FROM seller INNER JOIN department ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? ORDER BY seller.Name;");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer,Department> map = new HashMap<>();
			
			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				if (dep == null) {
					dep = instanciateDep(rs);
					map.put(id, dep);
				} 
 				Seller seller = instanciateSeller(rs, dep);
				list.add(seller);
			}
			return list;
		}
		catch (SQLException e) {
			System.out.println("Error fetching by department: "+ e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return null;
	}
	
}
