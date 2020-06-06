package com.actions.seacneActions;

import com.actions.Action;
import com.entities.Seance;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateSeanceAction implements Action {

	private ConsoleView cv;
	private Persistence p;

	@Override
	public void launch() {
		cv.print("podaj ID");
		int id = cv.getValidInt("podaj ID");
		cv.print("podaj cenê biletu:");
		String priceOfTicket = cv.read();
		
		if (!"".equals(priceOfTicket)) {
			p.update(id, Seance.class, "priceOfTicket", Double.parseDouble(priceOfTicket));
		}
	}

	@Override
	public String getName() {
		return "UpdateSeance";
	}

}
