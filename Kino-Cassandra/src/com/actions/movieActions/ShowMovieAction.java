package com.actions.movieActions;

import java.util.List;

import com.actions.Action;
import com.entities.Movie;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowMovieAction implements Action {

	private ConsoleView cv;
	private Persistence p;

	@Override
	public void launch() {
		String col = cv.read("kolumna");

		String read = cv.read("vartoœæ");

		List<String> list;

		try {
			list = p.findBy(col, Integer.parseInt(read), Movie.class);
		} catch (NumberFormatException e) {
			list = p.findBy(col, read, Movie.class);
		}

		cv.print(list);
	}

	@Override
	public String getName() {
		return "ShowMovie";
	}

}
