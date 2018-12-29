package com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Rahul
 *
 */
public class B_Logic {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet Rs = null;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbLibrary?useSSL=false", "root", "root");
			stmt = con.createStatement();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	void add(int bookNumber, String bookName, String bookAuther, int qty, double price) {
		String query = "insert into Book values(" + bookNumber + ",'" + bookName.toLowerCase() + "','" + bookAuther
				+ "'," + qty + "," + price + ")";
		try {
			if (isBookAvailable(bookName) || isBookAvailable(bookNumber)) {
				System.out.println("This Record is already Exists ");
			} else {

				stmt.executeUpdate(query);
				System.out.println("Book " + bookName + " Is Inserted ");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	void delete(String bookName) {
		String query = "delete from Book where BookName= '" + bookName.toLowerCase() + "'";
		try {
			if (isBookAvailable(bookName.toLowerCase())) {
				stmt.executeUpdate(query);
				System.out.println("Book Name " + bookName + " Record is deleted");
			} else {
				System.out.println(bookName + " Record is not available");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	void display() {
		String query = "Select * from Book";
		System.out.println("Book's Available ");
		try {
			Rs = stmt.executeQuery(query);
			System.out.println("BookNo\tName\tAuthor\tQuant\tPrice");
			while (Rs.next()) {
				System.out.println(Rs.getInt(1) + "\t" + Rs.getString(2) + "\t" + Rs.getString(3) + "\t" + Rs.getInt(4)
						+ "\t" + Rs.getDouble(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	void purchase(int noOfBook, String strBookName) {
		String query = "select QTY,price from Book where BookName='" + strBookName.toLowerCase() + "'";
		try {
			if (isBookAvailable(strBookName.toLowerCase())) {
				Rs = stmt.executeQuery(query);
				int qty = 0;
				double price = 0;
				while (Rs.next()) {
					qty = Rs.getInt(1);
					price = Rs.getDouble(2);
				}
				if (qty < noOfBook || qty == 0) {
					System.out.println("No suffient Books are available");
				} else {
					int total = qty - noOfBook;
					price = noOfBook * price;
					String query1 = "update Book set QTY=" + total + " where BookName ='" + strBookName + "'";
					stmt.executeUpdate(query1);
					System.out.println("***************Bill****************");
					System.out.println("Name\tQuant\tPrice");
					System.out.println(strBookName + "\t" + noOfBook + "\t" + price);
				}
			} else {
				System.out.println("Book " + strBookName + " is not available");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	boolean isBookAvailable(String bookName) {
		boolean blnFlag = false;
		String query2 = " Select * from Book ";
		try {

			Rs = stmt.executeQuery(query2);
			while (Rs.next()) {
				if (Rs.getString(2).equals(bookName)) {
					blnFlag = true;
					break;
				} else {
					blnFlag = false;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return blnFlag;
	}

	boolean isBookAvailable(int id) {
		boolean blnFlag = false;
		String query2 = " Select * from Book ";
		try {
			Rs = stmt.executeQuery(query2);
			while (Rs.next()) {
				if (Rs.getInt(1) == id) {
					blnFlag = true;
					break;
				} else {
					blnFlag = false;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return blnFlag;
	}

}
