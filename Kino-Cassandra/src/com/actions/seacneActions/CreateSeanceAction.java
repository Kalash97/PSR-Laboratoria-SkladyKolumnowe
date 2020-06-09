package com.actions.seacneActions;

import com.actions.Action;
import com.entities.Seance;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateSeanceAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer id = cv.getValidInt("podaj ID");
		Double priceOfTicket = cv.getValidDouble("podaj cenê biletu:");
		
		Seance seance = new Seance();
		
		seance.setId(id);
		seance.setPriceOfTicket(priceOfTicket);
		seance.setMovieId(null);

		p.save(seance);
		
	}

	@Override
	public String getName() {
		return "CreateSeance";
	}

}
