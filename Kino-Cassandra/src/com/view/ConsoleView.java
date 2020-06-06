package com.view;

import java.util.List;
import java.util.Scanner;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class ConsoleView {
	private Scanner scanner = new Scanner(System.in);

	@SuppressWarnings("rawtypes")
	public void print(List l) {
		for(Object o : l) {
			print(o.toString());
		}
	}
	
	public void print(String msg) {
		System.out.println(msg);
	}

	public String read() {
		return scanner.nextLine();
	}

	public String read(String msg) {
		print(msg);
		return read();
	}

	public Integer getValidInt(String msg) {
		
		print(msg);
		while (true) {
			try {
				return Integer.parseInt(read());
			} catch (ClassCastException e) {
			
			}
		}
	}

	public Double getValidDouble(String msg) {

		print(msg);
		while (true) {
			try {
				return Double.parseDouble(read());
			} catch (ClassCastException e) {
			
			}
		}
	}

	public void showAllMovies(ResultSet resultSet) {
		for (Row r : resultSet) {
			print("movie: ");
			print("ID: " + r.getInt("id"));
			print("Title " + r.getString("title"));
			print("Duration time: " + r.getInt("durationTimeInMinutes") + " min");
			print("");
		}

	}

	public void showAllCustomers(ResultSet resultSet) {

		for (Row row : resultSet) {
			print("customer: ");
			print("ID: " + row.getInt("id"));
			print("Name " + row.getString("name"));
			print("Last name " + row.getString("lastName"));
			print("");
		}
	}

	public void showAllSeances(ResultSet resultSet) {
		for (Row row : resultSet) {
			print("seance: ");
			print("ID: " + row.getInt("id"));
			print("Price of the ticket: " + row.getDouble("priceOfTicket"));
			print("");
		}
	}
}
