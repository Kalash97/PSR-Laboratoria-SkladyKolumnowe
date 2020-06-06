package com.actions.movieActions;

import com.actions.Action;
import com.entities.Movie;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateMovieAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer id = cv.getValidInt("podaj ID");
		String title = cv.read("podaj tytu³");
		Integer durationTimeInMinutes = cv.getValidInt("podaj czas trwania:");
		
		Movie movie = new Movie();
		
		movie.setId(id);
		movie.setTitle(title);
		movie.setDurationTimeInMinutes(durationTimeInMinutes);
		
		p.save(movie);
	}

	@Override
	public String getName() {
		return "CreateMovie";
	}

}
