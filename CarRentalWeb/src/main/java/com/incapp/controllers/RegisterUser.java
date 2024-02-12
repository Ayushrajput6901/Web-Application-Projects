package com.incapp.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.incapp.modals.DAO;
import com.incapp.modals.SendMail;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name=request.getParameter("name");
			String phone=request.getParameter("phone");
			String email=request.getParameter("email");
			String address=request.getParameter("address");
			String password=request.getParameter("password");
			
			HashMap< String, String> user=new HashMap<>();
			user.put("name", name);
			user.put("phone", phone);
			user.put("email", email);
			user.put("address", address);
			user.put("password", password);
			
			DAO db=new DAO();
			boolean result=db.registerUser(user);
			db.closeConnection();
			HttpSession session=request.getSession();
			if(result) 
				session.setAttribute("msg", "Thank You for Registration, Verify OTP that has been sent to your email id!");
			else
				session.setAttribute("msg", "Email Id ALready Exist!");
			
			response.sendRedirect("User.jsp");
		}catch (Exception e) {
			response.sendRedirect("ExpPage.jsp");
			e.printStackTrace();
		}
	}

}
