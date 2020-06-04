package com.actions.customerActions;

import java.time.Duration;

import com.actions.Action;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.Insert;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCustomerAction implements Action{

	private CqlSession session;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		cv.print("podaj ID:");
		String id = cv.getValidInt();
		
		cv.print("Podaj imiê");
		String name = cv.getValidString();
		
		cv.print("podaj nazwisko");
		String lastName = cv.getValidString();
		
		Insert insert = QueryBuilder.insertInto("customer")
			.value("id", QueryBuilder.raw(id))
			.value("name", QueryBuilder.raw(name))
			.value("lastName", QueryBuilder.raw(lastName));
		
		session.execute(insert.build().setTimeout(Duration.ofSeconds(30)));
	}

	@Override
	public String getName() {
		return "CreateCustomer";
	}

}
