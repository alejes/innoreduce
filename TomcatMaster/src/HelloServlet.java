import org.json.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String path = "\\..\\..\\..\\serversList.json";

	@Override
	public void init() throws ServletException {
		// Servlet initialization code here
		super.init();
	}

	private boolean checkip(String ip) {
		Pattern pattern = Pattern
				.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
		return pattern.matcher(ip).matches();
	}

	private void redirect(HttpServletResponse response, PrintWriter out, String to) {
		response.setHeader("Location", to);
		out.println("<html><body><script  type='text/javascript'>window.location='" + to + "';</script></body></html>");
	}

	private JSONArray readJSONArray() {
		BufferedReader br = null;
		String jsonText = "";
		try {
			String sCurrentLine;
			br = new BufferedReader(
					new FileReader(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + path));
			while ((sCurrentLine = br.readLine()) != null) {
				jsonText += sCurrentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation());
			System.out.println(e.getMessage());
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		JSONObject obj = new JSONObject(jsonText);
		JSONArray arr = obj.getJSONArray("ip");
		return arr;
	}
	
	private JSONObject writeJSONObject(ArrayList<String> ipArray){
		JSONObject obj = new JSONObject();
		obj.put("ip", ipArray);
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(
					new FileWriter(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + path)));
			out.write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation());
			System.out.println(e.getMessage());
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}	
		
		return obj;
	}
	
	private void addServerIp(String ip) {
		JSONArray arr = readJSONArray();
		// sorting
		ArrayList<String> ipArray = new ArrayList<String>();
		for (int i = 0; i < arr.length(); i++)
			if (!ip.equals(arr.getString(i)))
				ipArray.add(arr.getString(i));
		ipArray.add(ip);
		Collections.sort(ipArray);
		
		JSONObject obj = writeJSONObject(ipArray);
		System.out.println("Write response:");
		System.out.println(obj.toString());
	}

	private void deleteServerIp(String ip) {
		JSONArray arr = readJSONArray();
		// sorting
		ArrayList<String> ipArray = new ArrayList<String>();
		for (int i = 0; i < arr.length(); i++)
			if (!ip.equals(arr.getString(i)))
				ipArray.add(arr.getString(i));
		
		JSONObject obj = writeJSONObject(ipArray);
		System.out.println("Write response:");
		System.out.println(obj.toString());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		if (action.equals("addip")) {
			String ip = request.getParameter("addip");
			if (!checkip(ip)) {
				redirect(response, out, request.getRequestURL() + "../../?wrongIp=" + ip);
			} else {
				addServerIp(ip);
				redirect(response, out, request.getRequestURL() + "../../?ipadded");
			}
			out.println("Wait for redirection");
		} else if (action.equals("deleteServer")) {
			String ip = request.getParameter("deleteip");
			if (!checkip(ip)) {
				out.println("CHECK FAILED");
				System.out.println("CHECK FAILED");
			} else {
				System.out.println(ip);
				deleteServerIp(ip);
				out.println("OK");
				System.out.println("DELETE OK");
			}
		} else if (action.equals("sendRequest")) {
			String query = request.getParameter("request");
			System.out.println(query);
			String results = GetResult(query);
			// String results = "RYKI ZHOPA BUSTREJ";
			out.print(results);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	@Override
	public void destroy() {
		// resource release
		super.destroy();
	}

	public String GetResult(String query) {
		
		JSONArray arr = readJSONArray();
		ArrayList<ConnectionArgs> connArgs = new ArrayList<ConnectionArgs>();
		
		for (int i = 0; i < arr.length(); i++){
			if (arr.get(i) != null)
				connArgs.add(new ConnectionArgs("", "", arr.getString(i)));
		}

		StringBuilder result = new StringBuilder();
		// SELECT * FROM sensors.sensorvalues WHERE recordValue < 1.57;

		for (int i = 0; i < connArgs.size(); i++) {
			try {
				Registry registry = LocateRegistry.getRegistry(connArgs.get(i).getUrl());
				// Lookup server object
				Sensorable sensorRegistry = (Sensorable) registry.lookup("Sensor");
				ArrayList<Sensor> sensors = sensorRegistry.selectAll(query);
				for (Sensor s : sensors) {
					result.append(s.toString() + "<br>");
				}
			} catch (Exception e) {

				System.out.println("Server at " + connArgs.get(i).getUrl() + " down!");
				result.append("Server at " + connArgs.get(i).getUrl() + " down!<br/>\n");
			}
		}
		return result.toString();
	}
}