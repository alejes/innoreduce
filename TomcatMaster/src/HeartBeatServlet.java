
import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.servlet.*;
import javax.servlet.http.*;

public class HeartBeatServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7378873008184742401L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
//		System.out.println("check");
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			String action = request.getParameter("action");
			if (action.equals("sendHeartBeat")) {
				
				String ip = request.getParameter("checkip");
				
				//String ip = "192.168.56.101";
				
				System.out.println(ip);
				Boolean answer = heartbeatMaintain(request, response, ip);
				System.out.println(ip + " - " + answer.toString());
				out.write(answer.toString());
			}
		} catch (Exception ex) {
			System.out.println("crash1");
		}
	}

	public boolean heartbeatMaintain(HttpServletRequest request, HttpServletResponse response, String Url)
			throws ServletException, IOException {
		try {
			Registry registry = LocateRegistry.getRegistry(Url);
			// Lookup server object
			Sensorable sensorRegistry = (Sensorable) registry.lookup("Sensor");
			String s = sensorRegistry.sayHello();
			
			if (s.equals("Hello!"))
				return true;
			return false;
		} catch (Exception e) {
			return false;
		}

	}
}
