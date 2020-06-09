package com.actions;

import com.entities.CustomerInSeance;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteReservationAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer id = cv.getValidInt("podaj ID");
		
		p.delete(id, CustomerInSeance.class);
	}

	@Override
	public String getName() {
		return "DeleteReservation";
	}

}
