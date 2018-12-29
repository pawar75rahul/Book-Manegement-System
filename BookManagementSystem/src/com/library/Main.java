package com.library;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;

		do {
			System.out.println("Enter Your choice");
			System.out.println("--------------------------------------------------");
			System.out.println("1) Add Book");
			System.out.println("2) Delete Book");
			System.out.println("3) Display Book");
			System.out.println("4) Purches Book");

			choice = sc.nextInt();
			B_Logic bl = new B_Logic();

			if (choice == 1) {

				System.out.println("Enter Book-Id Number");
				int bookNumber = sc.nextInt();

				System.out.println("Enter Book Name");
				String bookName = sc.next();

				System.out.println("Enter Book Auther Name");
				String bookAuther = sc.next();

				System.out.println("Book QTY ");
				int qty = sc.nextInt();

				System.out.println("Enter Price");
				double price = sc.nextDouble();
				bl.add(bookNumber, bookName, bookAuther, qty, price);

			} else if (choice == 2) {
				System.out.println("Enter Book Name");
				String strBookName = sc.next();
				
				
				bl.delete(strBookName);

			} else if (choice == 3) {
				bl.display();

			} else if (choice == 4) {
				System.out.println("Which Book You Want to Purches ?");
				System.out.println("Enter Book Name");
				String bookName = sc.next();

				System.out.println("How Many Book You Want to Purches");
				int noOfBook = sc.nextInt();

				bl.purchase(noOfBook, bookName);
			}

		} while (choice != 0);
		sc.close();
	}
}
