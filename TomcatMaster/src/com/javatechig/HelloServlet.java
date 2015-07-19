package com.javatechig;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// Servlet initialization code here
		super.init();
	}

	private boolean checkip(String ip){
		Pattern pattern = Pattern.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"); 
 		return pattern.matcher(ip).matches();
	}
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");
	 	
	 	if (action.equals("addip")){
	 		String ip = request.getParameter("addip");
	 		if (!checkip(ip)){
	 			//out.println("WrongIp");
	 			response.setHeader("Location", "../?wrongIp="+ip);
	 		}
	 		else{
	 			response.setHeader("Location", "../?ipadded");
	 		}
	 		
	 	}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 	
	}

	@Override
	public void destroy() {
		// resource release
		super.destroy();
	}
}