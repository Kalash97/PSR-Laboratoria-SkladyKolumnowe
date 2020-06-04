package com.actions.seacneActions;

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
public class ShowAllSeancesAction implements Action{

	private CqlSession session;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Select query = QueryBuilder.selectFrom("seance").all();
		SimpleStatement simpleStatement = query.build();
		ResultSet resultSet = session.execute(simpleStatement.setTimeout(Duration.ofSeconds(30)));
		
		cv.showAllSeances(resultSet);
	}

	@Override
	public String getName() {
		return "ShowAllSeances";
	}

}
