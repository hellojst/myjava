package com.stjia.javabase.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author stjia
 * @date 2018年5月9日
 */
public class JdbcJava {

	public static void main(String[] args) {
		
		//加载数据库驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees?characterEncoding=utf8&useSSL=true", "root", "vislecaina");
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from titles limit 10 offset 3");
			while(resultSet.next()) {
				System.out.print(resultSet.getInt(1) + ";");
				System.out.print(resultSet.getString(2) + ";");
				System.out.print(resultSet.getDate(3) + ";");
				System.out.print(resultSet.getDate(4) + ";");
				System.out.println();
			};
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
