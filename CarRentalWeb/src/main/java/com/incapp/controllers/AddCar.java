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
@WebServlet("/AddCar")
@MultipartConfig
public class AddCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name=request.getParameter("name");
			String reg_no=request.getParameter("reg_no");
			String fuel_type=request.getParameter("fuel_type");
			String location=request.getParameter("location");
			int seats=Integer.parseInt(request.getParameter("seats"));
			int rate_per_day=Integer.parseInt(request.getParameter("rate_per_day"));
			Part part=request.getPart("photo");
			InputStream photo=part.getInputStream();
			

			HttpSession session=request.getSession();
			String owner_email=(String)session.getAttribute("owner_email");
			
			HashMap< String, Object> car=new HashMap<>();
			car.put("name", name);
			car.put("reg_no", reg_no);
			car.put("fuel_type", fuel_type);
			car.put("location", location);
			car.put("seats", seats);
			car.put("rate_per_day", rate_per_day);
			car.put("photo", photo);
			car.put("owner_email", owner_email);
			
			DAO db=new DAO();
			boolean result=db.addCar(car);
			db.closeConnection();
			if(result)
				session.setAttribute("msg", "Car Added Successfully!");
			else
				session.setAttribute("msg", "Car Already Exist!");
			
			response.sendRedirect("CarOwnerHome.jsp");
		}catch (Exception e) {
			response.sendRedirect("ExpPage.jsp");
			e.printStackTrace();
		}
	}

}
