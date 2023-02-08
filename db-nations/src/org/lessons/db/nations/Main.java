package org.lessons.db.nations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/db-nations";
		String user = "root";
		String password = "root";

		Scanner s = new Scanner(System.in);

		try (Connection con = DriverManager.getConnection(url, user, password)) {

			String sql = "SELECT countries.name, countries.country_id, regions.name, continents.name \n"
					+ "FROM countries \n" + "Inner join regions on countries.region_id  = regions.region_id  \n"
					+ "Inner join continents on regions.continent_id = continents.continent_id \n"
					+ "Where countries.name like ? \n" + "Order by countries.name ";

			try (PreparedStatement ps = con.prepareStatement(sql)) {
				System.out.println("Inserisci una stringa di ricerca ");

				ps.setString(1, "%" + s.nextLine() + "%");

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						System.out.println("Nation:" + rs.getString(2) + " -- " + " Id:" + rs.getString(1) + " -- "
								+ " Region:" + rs.getString(3) + " -- " + " Continent:" + rs.getString(4));
					}
				}
			}
		}
		s.close();
	}
}
