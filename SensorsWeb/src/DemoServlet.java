import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		// Do required initialization
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DemoServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String query = request.getParameter("queryText");
//		// Set response content type
//		
//		response.setContentType("text/html");
//		
//		// Actual logic goes here.
//		PrintWriter out = response.getWriter();
////		String QRY = "SELECT * FROM sensors.sensorvalues WHERE recordValue < 1.57;";
//		out.print(GetResult(query));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);
		String query = request.getParameter("queryText");
		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
//		String QRY = "SELECT * FROM sensors.sensorvalues WHERE recordValue < 1.57;";
//		out.print("Print govno.");

		out.print(GetResult(query));
	}

	public static String GetResult(String query) {

		ArrayList<ConnectionArgs> connArgs = new ArrayList<ConnectionArgs>();
		connArgs.add(new ConnectionArgs("", "", "192.168.56.101"));
		connArgs.add(new ConnectionArgs("", "", "192.168.56.102"));

		StringBuilder result = new StringBuilder();

//		for (int i = 0; i < connArgs.size(); i++) {
			try {
				Registry registry = LocateRegistry.getRegistry("192.168.56.101");
				// Lookup server object
				Sensorable sensorRegistry = (Sensorable) registry.lookup("Sensor");
				// System.out.println(sensorRegistry.sayHello());
				ArrayList<Sensor> sensors = sensorRegistry.selectAll(query);
				for (Sensor s : sensors) {
					result.append(s.toString() + "<br>");
					// System.out.println(s.toString());
				}
				// }
			} catch (Exception e) {
				System.out.println("Server at ");
//				System.out.println("Server at " + connArgs.get(i).getUrl() + " down!");
				// e.printStackTrace();
			}
//		}
		return result.toString();
	}

}
