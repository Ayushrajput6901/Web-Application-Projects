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
@WebServlet("/DeleteCar")
public class DeleteCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String reg_no=request.getParameter("reg_no");
			DAO db=new DAO();
			db.deleteCar(reg_no);
			db.closeConnection();
			HttpSession session=request.getSession();
			session.setAttribute("msg", "Car Deleted Successfully!");
			response.sendRedirect("CarOwnerHome.jsp");
		}catch (Exception e) {
			response.sendRedirect("ExpPage.jsp");
			e.printStackTrace();
		}
	}

}
