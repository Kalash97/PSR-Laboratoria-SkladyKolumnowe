package com.actions.movieActions;

import com.actions.Action;
import com.entities.Movie;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteMovieAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		int id = cv.getValidInt("podaj ID");
		
		p.delete(id, Movie.class);
	}

	@Override
	public String getName() {
		return "DeleteMovie";
	}

}
