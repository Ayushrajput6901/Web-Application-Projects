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
@WebServlet("/CarOwnerLogin")
public class CarOwnerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			DAO db=new DAO();
			String result=db.carOwnerLogin(email, password);
			db.closeConnection();
			HttpSession session=request.getSession();
			if(result==null) {
				session.setAttribute("msg", "Invalid Entries!");
				response.sendRedirect("CarOwner.jsp");
			}else if(result.equalsIgnoreCase("Pending")) {
				session.setAttribute("msg", "Your Status is Pending!");
				response.sendRedirect("CarOwner.jsp");
			}else {
				session.setAttribute("name", result);
				session.setAttribute("owner_email", email);
				response.sendRedirect("CarOwnerHome.jsp");
			}
		}catch (Exception e) {
			response.sendRedirect("ExpPage.jsp");
			e.printStackTrace();
		}
	}

}
