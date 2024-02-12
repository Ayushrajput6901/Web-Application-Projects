package com.incapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.incapp.modals.DAO;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/ChangeEnquiryStatus")
public class ChangeEnquiryStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			DAO db=new DAO();
			db.changeEnquiryStatus(id);
			db.closeConnection();
			response.sendRedirect("Enquiries.jsp");
		}catch (Exception e) {
			response.sendRedirect("ExpPage.jsp");
			e.printStackTrace();
		}
	}

}
