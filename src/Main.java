// Author: Xhoi Caveli
// Date: 04/04/2022
// Desc: This program is used to create, update, delete and view data from a binary file using serialization. 
// 		 It's a menu driven program that allows the user to do all that.

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static List<Person> people=new ArrayList<>();
	
	public static void main(String[] args) {

		people.add(new Person("Xhoi Caveli", "6309282342", "05/13/2000", "xcaveli@gmial.com"));
		people.add(new Person("Rei Caveli", "6309282723", "06/24/2006", "rcaveli@gmial.com"));
		people.add(new Person("Rajmonda Todori", "6309272622", "12/15/1999", "rtodori@gmail.com"));
		people.add(new Person("Ermira Todori", "6309282722", "05/14/2002", "etodori@gmial.com"));
		
		try {
			menu();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void menu() throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Menu");
		System.out.print("1) Add information into a file.\r\n"
				+ "2) Retrieve information from a file and display them.\r\n"
				+ "3) Delete information.\r\n"
				+ "4) Update information.\r\n"
				+ "5) Exit.\r\n");
		System.out.print("Choice: ");
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1:
				addInformation();
				break;
			case 2:
				retreiveInfo();
				break;
			case 3:
				deleteInfo();
				break;
			case 4:
				updateInfo();
				break;
			case 5:
				System.exit(0);
		}
		sc.close();
	}

	private static void updateInfo() throws IOException, ClassNotFoundException {
		
		File file = new File("people.bin");
		
		if (file.length() == 0) { 
			System.out.println("No data yet."); 
			} 
		else { 
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("people.bin"))) {
				List<Person> peopleRead = (List<Person>) ois.readObject();
				people = peopleRead;
				
				Scanner sc = new Scanner(System.in);
				
				System.out.println("Choose a person to edit 1-" + (people.size()) + ": ");
				int index = sc.nextInt();
				int choice = 0;
				do {
					System.out.println("Choose one of the following fields");
					System.out.print("1. Name\r\n"
							+ "2. Phone Number\r\n"
							+ "3. Date of Birth\r\n"
							+ "4. Email\r\n"
							+ "5. Go back\r\n" + "Choice: ");
					choice = sc.nextInt();
					sc.nextLine();
					
					switch(choice) {
					case 1:
						System.out.print("Update name to: ");
						people.get(index - 1).setName(sc.nextLine());
						break;
					case 2:
						System.out.print("Update phone number to: ");
						people.get(index - 1).setPhoneNumber(sc.nextLine());
						break;
					case 3:
						System.out.print("Update date of birth to: ");
						people.get(index - 1).setDateOfBirth(sc.nextLine());
						break;
					case 4:
						System.out.print("Update email to: ");
						people.get(index - 1).setEmail(sc.nextLine());
						break;
					}
				} while(choice != 5);
				
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("people.bin"))) {
					oos.writeObject(people);
				}		
				System.out.println();
				
				try {
					menu();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				
				sc.close();
			}	
		}
	}

	private static void retreiveInfo() throws IOException, ClassNotFoundException {
		File file = new File("people.bin");
		
		if (file.length() == 0) { 
			System.out.println(); 
			System.out.println("No data yet."); 
			System.out.println(); 
			} 
		else { 
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("people.bin"))) {
				List<Person> peopleRead = (List<Person>) ois.readObject();
	
				System.out.println();
				for(int i = 0; i < peopleRead.size(); i++){
					System.out.println(i + 1 + ".");
					System.out.println(peopleRead.get(i));
				}	
			}
		}
		

		try {
			menu();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void addInformation() throws IOException, ClassNotFoundException  {

		Scanner sc = new Scanner(System.in);
		
		Person person = new Person();
		
		System.out.print("\nEnter name: ");
		person.setName(sc.nextLine());
		System.out.print("Enter phone number: ");
		person.setPhoneNumber(sc.nextLine());
		System.out.print("Enter date of birth: ");
		person.setDateOfBirth(sc.nextLine());
		System.out.print("Enter email: ");
		person.setEmail(sc.nextLine());
		
		people.add(person);
		
	
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("people.bin"))) {
			oos.writeObject(people);
		}		
		System.out.println();

		try {
			menu();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		sc.close();
		
	}

	private static void deleteInfo() throws IOException {
		File file = new File("people.bin");
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
		System.out.println("All data deleted.");
		System.out.println();
		try {
			menu();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
