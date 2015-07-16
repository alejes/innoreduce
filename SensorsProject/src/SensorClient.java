import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class SensorClient {

	public static void main(String[] args) {

		ArrayList<ConnectionArgs> connArgs = new ArrayList<ConnectionArgs>();
		connArgs.add(new ConnectionArgs("slave-1", "2773844", "192.168.56.101"));
		connArgs.add(new ConnectionArgs("slave-1", "2773844", "192.168.56.102"));

		for (int i = 0; i < connArgs.size(); i++) {
			try {
				Registry registry = LocateRegistry.getRegistry(connArgs.get(i)
						.getUrl());
				// Lookup server object
				Sensorable sensorRegistry = (Sensorable) registry
						.lookup("Sensor");
//				System.out.println(sensorRegistry.sayHello());
				ArrayList<Sensor> sensors = sensorRegistry.findAll();
				for (Sensor s : sensors) {
					System.out.println(s.toString());
				}
				// }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
