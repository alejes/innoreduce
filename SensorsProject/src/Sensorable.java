import java.rmi.*;
import java.util.ArrayList;

public interface Sensorable extends Remote {
	public ArrayList<Sensor> findAll() throws RemoteException;

	public String sayHello() throws RemoteException;
}
