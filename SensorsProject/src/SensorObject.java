import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SensorObject extends UnicastRemoteObject implements Sensorable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SensorObject() throws RemoteException {
		super();
	}

	public ArrayList<Sensor> findAll() {
		try {
			System.out.println("Invoke findAll from " + getClientHost());
		} catch (ServerNotActiveException snae) {
			snae.printStackTrace();
		}
		return SensorRepository.findAll();
	}

	public String sayHello() throws RemoteException {
				
		try {
			System.out.println("Invoke sayHello from " + getClientHost());
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		}
		return "Hello!";
	}

}
