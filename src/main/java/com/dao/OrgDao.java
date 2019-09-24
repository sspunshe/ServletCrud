package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.model.Organization;
import com.model.User;

public class OrgDao {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/orguser", "root", "root");
			System.out.println("Established");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static int save(Organization org) {
		int status = 0;
		Connection con = OrgDao.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"insert into organization(Name,Email, Password, Address, City) Values(?,?,?,?,?)");
			ps.setString(1, org.getName());
			ps.setString(2, org.getEmail());
			ps.setString(3, org.getPassword());
			ps.setString(4, org.getAddress());
			ps.setString(5, org.getCity());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;

	}

	public static ResultSet login(String email, String pass) {
		ResultSet rs = null;
		Connection con = OrgDao.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("Select * from organization where Email = ? and Password = ?");
			ps.setString(1, email);
			ps.setString(2, pass);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static boolean checkEmail(String email) {
		ResultSet rs = null;
		boolean status = false;
		Connection con = OrgDao.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("Select * from organization where Email = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next())
				status = true;
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public static List<User> getUser(int id) {
		List<User> list = new ArrayList<User>();
		Connection con = OrgDao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement("select * from user where Oid =" + id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString(2));
				user.setDesignation(rs.getString(3));
				list.add(user);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
	
	public static int addUser(User user) {
		int status = 0;
		Connection con = OrgDao.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into user(Name,Designation,Oid) Values(?,?,?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getDesignation());
			ps.setInt(3, user.getOid());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

}
