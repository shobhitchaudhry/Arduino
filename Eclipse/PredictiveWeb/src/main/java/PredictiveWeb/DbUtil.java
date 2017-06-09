package PredictiveWeb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbUtil {

	public static String insertDetailsHana(String firstName, String lastName, int drive, int coffee, int gender,
			int age) {
		String returnID = null;
		try {
			System.out.println("Inserting sql");
			Connection con = DriverManager.getConnection("jdbc:sqlanywhere:UserID=DBA;Password=Test123!");
			Statement stmt = con.createStatement();
			stmt.execute(
					"Insert into RealTimeData (FirstName, LastName, Age, Gender, Coffee, Drive) values ('" + firstName
							+ "','" + lastName + "','" + age + "','" + gender + "','" + coffee + "','" + drive + "')");
			Statement stmt2 = con.createStatement();
			ResultSet rs = stmt2.executeQuery("SELECT @@IDENTITY");

			while (rs.next()) {
				returnID = rs.getString(1);
			}
			System.out.println("Done inserting data");
			stmt.close();
			con.close();
			return (returnID);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return returnID;
	}

	public static String viewOverallStats() {
		//headers:
		String table = "<tr><th><b>Rank</b></th><th><b>Name</b></th><th><b>Score</b></th></tr>";
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlanywhere:UserID=DBA;Password=Test123!");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT RANK() OVER (ORDER BY (Level1+Level2+Level3) DESC) Rank, Firstname,(Level1+Level2+Level3) AS TotalScore FROM RealTimeData order by TotalScore desc");

			while (rs.next()) {
				table+= "<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)
						+ "</td></tr>";
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		}
		return table;
	}

	public static String[] viewDetailsHana(String id) {
		String scores[] = new String[3];
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlanywhere:UserID=DBA;Password=Test123!");
			Statement stmt = con.createStatement();

			String query = "SELECT rr_Level1,rr_Level2,rr_Level3 FROM Predicted_Output where id=?";
			PreparedStatement p = con.prepareStatement(query);
			p.setString(1, id);
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				String FirstLevel = rs.getString(1);
				String SecondLevel = rs.getString(2);
				String ThirdLevel = rs.getString(3);

				scores[0] = FirstLevel;
				scores[1] = SecondLevel;
				scores[2] = ThirdLevel;
				System.out.println(
						"Level 1 Prediction: " + FirstLevel + ", Level2: " + SecondLevel + ", Level3: " + ThirdLevel);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		}
		return scores;
	}
}
