package com.actions.movieActions;

import java.util.List;

import com.actions.Action;
import com.entities.Movie;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowAllMoviesAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		List<String> all = p.findAll(Movie.class);
		cv.print(all);
	}

	@Override
	public String getName() {
		return "ShowAllMovies";
	}

}
