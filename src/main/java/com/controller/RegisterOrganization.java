package com.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.OrgDao;
import com.model.Organization;

public class RegisterOrganization extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String add = request.getParameter("add");
		String city = request.getParameter("city");
		Organization org = new Organization();
		org.setName(name);
		org.setEmail(email);
		org.setPassword(pass);
		org.setAddress(add);
		org.setCity(city);
		boolean status = OrgDao.checkEmail(email);
		try {
			if (status) {
				out.println("<p>Account Already Exist...</p>");
				request.getRequestDispatcher("Login.jsp").include(request, response);
			}
			else {
				int status1 = OrgDao.save(org);
				if (status1 > 0) {
					out.print("<p>Registration successful!</p>");
					request.getRequestDispatcher("Login.jsp").include(request, response);
				} else {
					out.println("Sorry! Unable to Register");
					request.getRequestDispatcher("index.html").include(request, response);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
