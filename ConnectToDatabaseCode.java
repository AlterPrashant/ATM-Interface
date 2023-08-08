package com.codsoft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDatabaseCode {

	// Variables

	Statement stmt = null;
	ResultSet rs = null;
	Connection con = null;

	int CustomerID = 0;
	int Balance_Money = 0;

	// Constructor => COnstructor is Spacial Function whoes name is same as class
	// name and have no return type .

	public ConnectToDatabaseCode() {

		try {
			// Class.forName("com.mysql.jdbc.Driver"); old driver class
			Class.forName("com.mysql.cj.jdbc.Driver"); // new driver class
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "root");

			System.out.println("SuccessFully Connected to Database.....");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Function with return type-- this function helps to do connect database and do
	// to login part

	public boolean login(String name, String password) throws SQLException {
		stmt = con.createStatement();

		rs = stmt.executeQuery(
				"select * from customer where CustomerName='" + name + "' and CustomerPassword='" + password + "'");

		if (rs.next()) {
			CustomerID = rs.getInt("CustomerID");
			return true;

		} else {
			return false;
		}

	}

	// get money
	public int getBalance(int id) throws SQLException {
		rs = stmt.executeQuery("select BalanceAmount from Account where CustomerID=" + id + "");
		if (rs.next()) {
			Balance_Money = rs.getInt("BalanceAmount");

			return Balance_Money;

		} else {
			return 0;
		}

	}

	// withdraw money

	public boolean withDrawMoney(int id, int amount) throws SQLException {
		if (amount > Balance_Money) {
			System.out.println("Insufficent balance ...");
			return false;

		} else {
			stmt.executeUpdate(
					"update Account set BalanceAmount=" + (Balance_Money - amount) + " where customerid=" + id);
			return true;
		}

	}

	// deposit money

	public boolean DepositMoney(int id, int amount) throws SQLException {

		int a = stmt.executeUpdate(
				"update Account set BalanceAmount=" + (Balance_Money + amount) + " where customerid=" + id);

		if (a == 1) {
			return true;
		} else {

			return false;
		}

	}

}
