package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.OrgDao;
import com.model.User;

public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String desig = request.getParameter("designation");
		HttpSession session = request.getSession();
		int oid = (Integer)session.getAttribute("Id");
		User user = new User();
		user.setName(name);
		user.setDesignation(desig);
		user.setOid(oid);
		int status = OrgDao.addUser(user);
		if (status > 0) {
			 out.print("<p>User Added successfully!</p>");
			request.getRequestDispatcher("Welcome.jsp").include(request, response);
		} else {
			 out.println("Sorry! unable to add user");
			 request.getRequestDispatcher("AddUser.jsp").include(request, response);
		}
	}

}
