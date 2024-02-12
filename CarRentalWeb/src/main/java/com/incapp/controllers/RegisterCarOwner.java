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

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/RegisterCarOwner")
@MultipartConfig
public class RegisterCarOwner extends HttpServlet {
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
			Part part=request.getPart("idproof");
			InputStream idproof=part.getInputStream();
			
			HashMap< String, String> carOwner=new HashMap<>();
			carOwner.put("name", name);
			carOwner.put("phone", phone);
			carOwner.put("email", email);
			carOwner.put("address", address);
			carOwner.put("password", password);
			
			DAO db=new DAO();
			boolean result=db.registerCarOwner(carOwner,idproof);
			db.closeConnection();
			HttpSession session=request.getSession();
			if(result)
				session.setAttribute("msg", "Thank You for Registration, We will update you soon!");
			else
				session.setAttribute("msg", "Email Id ALready Exist!");
			
			response.sendRedirect("CarOwner.jsp");
		}catch (Exception e) {
			response.sendRedirect("ExpPage.jsp");
			e.printStackTrace();
		}
	}

}
