package com.actions.seacneActions;

import java.time.Duration;

import com.actions.Action;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowSeanceByIdAction implements Action{

	private CqlSession session;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		cv.print("podaj ID");
		int id = Integer.parseInt(cv.getValidInt());
		
		Select select = QueryBuilder.selectFrom("seance").all().whereColumn("id").isEqualTo(QueryBuilder.literal(id));
		ResultSet resultSet = session.execute(select.build().setTimeout(Duration.ofSeconds(30)));
	
		if (resultSet.iterator().hasNext()) {
			cv.showAllSeances(resultSet);
		}else {
			cv.print("nie ma takiego seansu");
		}
	}

	@Override
	public String getName() {
		return "ShowSeanceById";
	}

}
