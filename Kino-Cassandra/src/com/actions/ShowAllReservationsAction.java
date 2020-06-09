package com.actions;

import java.util.List;

import com.entities.CustomerInSeance;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowAllReservationsAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		List<String> all = p.findAll(CustomerInSeance.class);
		cv.print(all);
	}

	@Override
	public String getName() {
		return "ShowAllReservations";
	}

}
