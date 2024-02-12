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
@WebServlet("/BookCar")
public class BookCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			HttpSession session=request.getSession();
			String user_email=(String)session.getAttribute("user_email");
			String reg_no=request.getParameter("reg_no");
			int rate_per_day=Integer.parseInt(request.getParameter("rate_per_day"));
			int days=Integer.parseInt(request.getParameter("days"));
			int total=rate_per_day*days;
			String owner_email=request.getParameter("owner_email");
			
			DAO db=new DAO();
			db.bookCar(reg_no,user_email,owner_email,days,total);
			db.closeConnection();
			session.setAttribute("msg", "Thank You for Booking!");
			
			response.sendRedirect("UserBookings.jsp");
		}catch (Exception e) {
			response.sendRedirect("ExpPage.jsp");
			e.printStackTrace();
		}
	}

}
