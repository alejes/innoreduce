import java.sql.*;
import java.util.*;

public class SensorRepository {

	public static ArrayList<Sensor> findAll() {
		ArrayList<Sensor> arr = new ArrayList<Sensor>();

		try {
			String QRY = "SELECT * FROM sensors.sensorvalues LIMIT 5;";
			Connection con = DBManager.getInstance().getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(QRY);

			while (rs.next()) {
				Sensor s = new Sensor();
				s.setId(rs.getString("sensorId"));
				s.setType(rs.getString("sensorType"));
				s.setDate(rs.getDate("recordDate"));
				s.setValue(rs.getDouble("recordValue"));
				arr.add(s);				
			}
			stmt.close();

		} catch (SQLException se) {
			System.out.println(se);
		}
		return arr;
	}
}
