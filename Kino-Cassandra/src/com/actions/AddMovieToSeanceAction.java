package com.actions;

import com.entities.Movie;
import com.entities.Seance;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddMovieToSeanceAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer movieId = cv.getValidInt("podaj ID filmu");
		Integer seanceId = cv.getValidInt("podaj ID seansu");
		
		if(p.getEntityId(movieId, Movie.class) == null || p.getEntityId(seanceId, Seance.class) ==null) {
			cv.print("nie ma takiego filmu lub seansu");
			return;
		}
		
		p.update(seanceId, Seance.class, "movieId", movieId);
		
	}

	@Override
	public String getName() {
		return "AddMovieToSeance";
	}

}
