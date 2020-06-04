package com.view;

import java.util.Scanner;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.utils.ValidUtil;

public class ConsoleView {
	private Scanner scanner = new Scanner(System.in);
	public void print(String msg) {
		System.out.println(msg);
	}
	
	public String read() {
		return scanner.nextLine();
	}
	
	public String getValidString() {
		return "'" + read() + "'";
	}
	
	public String getValidInt() {
		String line;
		do {
			line = read();
		}while(!ValidUtil.isIntInstance(line));
		return line;
	}
	
	public String getValidDouble(String msg) {
		String line;
		do {
			print(msg);
			line = read();
		}while(!ValidUtil.isDoubleInstance(line));
		return line;
	}
	
	public void showAllMovies(ResultSet resultSet) {
		for(Row r : resultSet) {
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
		for(Row row : resultSet) {
			print("seance: ");
			print("ID: " + row.getInt("id"));
			print("Price of the ticket: " + row.getDouble("priceOfTicket"));
			print("");
		}
	}
}
