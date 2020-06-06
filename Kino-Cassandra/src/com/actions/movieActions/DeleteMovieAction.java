package com.actions.movieActions;

import java.time.Duration;

import com.actions.Action;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteMovieAction implements Action{

	private CqlSession session;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		int id = cv.getValidInt("podaj ID");
		
		Delete delete = QueryBuilder.deleteFrom("movie")
			.whereColumn("id")
			.isEqualTo(QueryBuilder.literal(id));
		
		session.execute(delete.build().setTimeout(Duration.ofSeconds(30)));
	}

	@Override
	public String getName() {
		return "DeleteMovie";
	}

}
