package com.actions.movieActions;

import java.time.Duration;

import com.actions.Action;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;
import com.utils.ValidUtil;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateMovieAction implements Action {

	private CqlSession session;
	private ConsoleView cv;

	@Override
	public void launch() {
		cv.print("podaj ID");
		int id = Integer.parseInt(cv.getValidInt());

		cv.print("podaj tytu³:");
		String title = cv.read();

		cv.print("podaj czas trwania:");
		String durationTimeInMinutes = cv.read();

		Select select = QueryBuilder.selectFrom("movie").all().whereColumn("id").isEqualTo(QueryBuilder.literal(id));
		ResultSet resultSet = session.execute(select.build().setTimeout(Duration.ofSeconds(30)));
		
		if (resultSet.iterator().hasNext()) {

			if (!"".equals(title)) {
				Update update = QueryBuilder.update("movie").setColumn("title", QueryBuilder.raw("'" + title + "'"))
						.whereColumn("id").isEqualTo(QueryBuilder.literal(id));

				session.execute(update.build().setTimeout(Duration.ofSeconds(30)));
			}

			if (!"".equals(durationTimeInMinutes)) {
				if (ValidUtil.isIntInstance(durationTimeInMinutes)) {
					int durationTimeInMinutesInt = Integer.parseInt(durationTimeInMinutes);
					Update update = QueryBuilder.update("movie")
							.setColumn("durationTimeInMinutes", QueryBuilder.literal(durationTimeInMinutesInt))
							.whereColumn("id").isEqualTo(QueryBuilder.literal(id));

					session.execute(update.build().setTimeout(Duration.ofSeconds(30)));
				}
			}
		}else {
			cv.print("Nie ma takiego filmu");
		}

	}

	@Override
	public String getName() {
		return "UpdateMovie";
	}

}
