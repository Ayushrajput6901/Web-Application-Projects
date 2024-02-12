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
@WebServlet("/VerifyOTP")
public class VerifyOTP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			int otp=Integer.parseInt(request.getParameter("otp"));
			
			DAO db=new DAO();
			boolean result=db.checkOTP(email,otp);
			db.closeConnection();
			HttpSession session=request.getSession();
			if(result) 
				session.setAttribute("msg", "OTP Verified!");
			else
				session.setAttribute("msg", "Invalid OTP!");
			
			response.sendRedirect("User.jsp");
		}catch (Exception e) {
			response.sendRedirect("ExpPage.jsp");
			e.printStackTrace();
		}
	}

}
