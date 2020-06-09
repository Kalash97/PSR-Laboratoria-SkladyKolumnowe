package com.actions;

import com.entities.Seance;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveMovieFromSeanceAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer id = cv.getValidInt("podaj ID seansu");
		
		p.update(id, Seance.class, "movieId", null);
	}

	@Override
	public String getName() {
		return "RemoveMovieFromSeance";
	}

}
