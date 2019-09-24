package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.OrgDao;

public class LoginOrganization extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		ResultSet rs = OrgDao.login(email, pass);
		try {
			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				session.setAttribute("Id", rs.getInt(1));
				out.println("<p>Login Successful</p>");
				request.getRequestDispatcher("Welcome.jsp").include(request, response);

			} else {
				out.print("<p>Email ID or Password does not match..</p>");
				request.getRequestDispatcher("Login.jsp").include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
