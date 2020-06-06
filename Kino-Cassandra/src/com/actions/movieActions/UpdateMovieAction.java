package com.actions.movieActions;

import com.actions.Action;
import com.entities.Movie;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateMovieAction implements Action {

	private ConsoleView cv;
	private Persistence p;

	@Override
	public void launch() {
		int id = cv.getValidInt("podaj ID");

		cv.print("podaj tytu³:");
		String title = cv.read();

		cv.print("podaj czas trwania:");
		String durationTimeInMinutes = cv.read();

		if (!"".equals(title)) {
			p.update(id, Movie.class, "title", title);
		}

		if (!"".equals(durationTimeInMinutes)) {
			p.update(id, Movie.class, "durationTimeInMinutes", Integer.parseInt(durationTimeInMinutes));
		}

	}

	@Override
	public String getName() {
		return "UpdateMovie";
	}

}
