package com.actions.movieActions;

import com.actions.Action;
import com.entities.Movie;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowMovieByIdAction implements Action{

	private ConsoleView cv;
	private Persistence p;
	
	@Override
	public void launch() {
		int id = cv.getValidInt("podaj ID");
		
		String result = p.findById(id, Movie.class);
		cv.print(result);
	}

	@Override
	public String getName() {
		return "ShowMovieById";
	}

}
