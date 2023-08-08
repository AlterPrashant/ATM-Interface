package com.codsoft;

import java.sql.SQLException;
import java.util.Scanner;



public class ATMBank {
	// Global Variables - Reuse any time this variable

	Scanner sc = new Scanner(System.in);
	ConnectToDatabaseCode ctdCode;

	// Constructor
	public ATMBank() throws SQLException {
		// here we are creating object...
		ctdCode = new ConnectToDatabaseCode();

	}

	public void Login_Customer() throws SQLException {
		System.out.println("Enter Customer Name : ");
		String name = sc.next();
		System.out.println("Enter Customer Passoword");
		String password = sc.next();
		boolean flag = ctdCode.login(name, password);
		if (flag) {
			System.out.println("Login is Successful...");
			display_Menu();

		} else {
			System.out.println("Login Failed ! , Try again");
			Login_Customer();
		}

	}

	// display all menu
	public void display_Menu() throws SQLException {
		System.out.println("***********************Menu***********************");
		System.out.println();
		System.out.println("1. Check Balance");
		System.out.println("2. Withdraw Money");
		System.out.println("3. Deposit Money");
		System.out.println("4. Logout");
		System.out.println("*******************************************");
		System.out.println("Select Your Choice : ");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			check_Balance();
			break;

		case 2:
			withdraw_Money();
			break;

		case 3:
			deposit_Money();
			break;

		case 4:
			log_out();
			break;

		default:
			System.out.println("invalid  choice...." + choice);

		}

	}

	public void log_out() {
		System.out.println("Good Bye !");
		System.exit(0);

	}

	public void deposit_Money() throws SQLException {
		System.out.println("Enter Amount you want to deposit : ");
		int amount = sc.nextInt();
		boolean flag = ctdCode.DepositMoney(ctdCode.CustomerID, amount);
		if (flag) {
			System.out.println("Successfully deposited Money : " + amount);
			System.out.println();
			display_Menu();

		}

	}

	public void withdraw_Money() throws SQLException {
		System.out.println("Enter Amount you want to withdraw : ");
		int amount = sc.nextInt();
		boolean flag = ctdCode.withDrawMoney(ctdCode.CustomerID, amount);
		if (flag) {
			System.out.println("take Your money : " + amount);
			System.out.println();
			display_Menu();
		} else {
			System.out.println("Not enough money in your Account");
			System.out.println();
			display_Menu();
		}

	}

	public void check_Balance() throws SQLException {
		int balance = ctdCode.getBalance(ctdCode.CustomerID);
		System.out.println("Your total Balance in Your Account is : " + balance);
		System.out.println();
		display_Menu();

	}

	public static void main(String[] args) {
		try {
			ATMBank atm1 = new ATMBank();
			atm1.Login_Customer();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
