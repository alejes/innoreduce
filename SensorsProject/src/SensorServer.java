import java.net.InetAddress;
import java.rmi.registry.*;

public class SensorServer {
	public static void main(String[] args) {

		try {
			// Create and get reference to rmi registry
			Registry registry = LocateRegistry.createRegistry(1099);

			// Instantiate server object
			SensorObject so = new SensorObject();

			// Register server object
			registry.rebind("Sensor", so);
			
			InetAddress addr = InetAddress.getLocalHost();
		    
			System.out.println("SensorServer on " + addr.getHostAddress() + " is created!");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
