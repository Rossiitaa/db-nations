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

			String sql = "SELECT countries.country_id  as id_paese ,countries.name as nome_paese, regions.name as nome_regione, continents.name as nome_continente\n"
					+ "FROM countries \n" + "Inner join regions \n" + "on countries.region_id  = regions.region_id  \n"
					+ "Inner join continents \n" + "on regions.continent_id = continents.continent_id \n"
					+ "Order by countries.name ";

			try (PreparedStatement ps = con.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {
						System.out.println("Nation:" + rs.getString(2) + " -- " + " Id:" + rs.getString(1) + " -- "
								+ " Region:" + rs.getString(3) + " -- " + " Continent:" + rs.getString(4));
					}
				}
			}
		}
	}
}
