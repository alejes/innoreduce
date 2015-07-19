package com.javatechig;

import org.json.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	private void redirect(HttpServletResponse response, PrintWriter out, String to){
		response.setHeader("Location", to);
		out.println("<html><body><script  type='text/javascript'>window.location='"+to+"';</script></body></html>");
	}
	private void addServerIp(String ip){
		
		BufferedReader br = null;
		
		String jsonText = "";
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()+"\\..\\..\\..\\..\\..\\serversList.json"));
			
			while ((sCurrentLine = br.readLine()) != null) {
				jsonText += sCurrentLine;
			}
 
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation());
			System.out.println(e.getMessage());
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		 JSONObject obj = new JSONObject(jsonText);
		 JSONArray arr = obj.getJSONArray("ip");
		 
		 //sorting
		 ArrayList<String> ipArray = new  ArrayList<String>();
		 for (int i = 0; i < arr.length(); i++)
			    if (!ip.equals(arr.getString(i)))
			    	ipArray.add(arr.getString(i));
		 ipArray.add(ip);
		 Collections.sort(ipArray);
		 
		 obj = new JSONObject(jsonText);
		 
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
	 			redirect(response, out, request.getRequestURL() + "../../?wrongIp="+ip);
	 		}
	 		else{
	 			addServerIp(ip);
	 			redirect(response, out, request.getRequestURL() + "../../?ipadded");
	 		}
	 		out.println("Wait for redirection");
	 	}
	 	else if (action.equals("sendRequest")){
	 		String query = request.getParameter("request");
	 		System.out.println(query);
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