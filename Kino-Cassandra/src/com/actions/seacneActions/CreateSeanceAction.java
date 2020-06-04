package com.actions.seacneActions;

import java.time.Duration;

import com.actions.Action;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.Insert;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateSeanceAction implements Action{

	private CqlSession session;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		cv.print("podaj ID:");
		String id = cv.getValidInt();
		String priceOfTicket = cv.getValidDouble("podaj cenê biletu:");
		
		Insert insert = QueryBuilder.insertInto("seance")
			.value("id", QueryBuilder.raw(id))
			.value("priceOfTicket", QueryBuilder.raw(priceOfTicket));
		
		session.execute(insert.build().setTimeout(Duration.ofSeconds(30)));

	}

	@Override
	public String getName() {
		return "CreateSeance";
	}

}
