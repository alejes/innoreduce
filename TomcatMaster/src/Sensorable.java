

import java.rmi.*;
import java.util.ArrayList;

public interface Sensorable extends Remote {
	public ArrayList<Sensor> selectAll() throws RemoteException;

	public ArrayList<Sensor> selectAll(String query) throws RemoteException;
	
	
	
	public String sayHello() throws RemoteException;
}