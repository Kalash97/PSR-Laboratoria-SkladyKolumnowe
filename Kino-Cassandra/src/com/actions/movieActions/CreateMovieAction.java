package com.actions.movieActions;

import java.time.Duration;

import com.actions.Action;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.Insert;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateMovieAction implements Action{

	private CqlSession session;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		cv.print("podaj ID:");
		String id = cv.getValidInt();
		cv.print("podaj tytu³:");
		String title = cv.getValidString();
		cv.print("podaj czas trwania:");
		String durationTimeInMinutes = cv.getValidInt();
		
		Insert insert = QueryBuilder.insertInto("movie")
			.value("id", QueryBuilder.raw(id))
			.value("title", QueryBuilder.raw( title))
			.value("durationTimeInMinutes", QueryBuilder.raw(durationTimeInMinutes));
		
		session.execute(insert.build().setTimeout(Duration.ofSeconds(30)));
	}

	@Override
	public String getName() {
		return "CreateMovie";
	}

}
