package com.actions.movieActions;

import java.time.Duration;

import com.actions.Action;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowMovieByIdAction implements Action{

	private CqlSession session;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		int id = cv.getValidInt("podaj ID");
		
		Select select = QueryBuilder.selectFrom("movie").all().whereColumn("id").isEqualTo(QueryBuilder.literal(id));
		ResultSet resultSet = session.execute(select.build().setTimeout(Duration.ofSeconds(30)));
		
		if (resultSet.iterator().hasNext()) {
			cv.showAllMovies(resultSet);
		}else {
			cv.print("nie ma takiego filmu");
		}
	}

	@Override
	public String getName() {
		return "ShowMovieById";
	}

}
