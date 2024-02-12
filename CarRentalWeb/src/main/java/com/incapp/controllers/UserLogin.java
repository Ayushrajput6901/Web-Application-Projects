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
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			DAO db=new DAO();
			String result=db.userLogin(email, password);
			db.closeConnection();
			HttpSession session=request.getSession();
			if(result==null) {
				session.setAttribute("msg", "Invalid Entries!");
				response.sendRedirect("User.jsp");
			}else if(result.equalsIgnoreCase("Pending")) {
				session.setAttribute("msg", "Your OTP Verification is Pending!");
				response.sendRedirect("User.jsp");
			}else {
				session.setAttribute("user_name", result);
				session.setAttribute("user_email", email);
				response.sendRedirect("UserHome.jsp");
			}
		}catch (Exception e) {
			response.sendRedirect("ExpPage.jsp");
			e.printStackTrace();
		}
	}

}
