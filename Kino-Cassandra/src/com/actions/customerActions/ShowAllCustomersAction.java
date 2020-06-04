package com.actions.customerActions;

import java.time.Duration;

import com.actions.Action;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowAllCustomersAction implements Action{
	
	private CqlSession session;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Select select = QueryBuilder.selectFrom("customer").all();
		SimpleStatement simpleStatement = select.build();
		ResultSet resultSet = session.execute(simpleStatement.setTimeout(Duration.ofSeconds(30)));
		
		cv.showAllCustomers(resultSet);
	}

	@Override
	public String getName() {
		return "ShowAllCustomers";
	}

}
