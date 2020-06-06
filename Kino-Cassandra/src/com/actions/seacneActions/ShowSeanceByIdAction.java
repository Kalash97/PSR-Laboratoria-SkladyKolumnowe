package com.actions.seacneActions;

import com.actions.Action;
import com.entities.Seance;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowSeanceByIdAction implements Action{

	private ConsoleView cv;
	private Persistence p;
	
	@Override
	public void launch() {
		int id = cv.getValidInt("podaj ID");
		
		String result = p.findById(id, Seance.class);
		cv.print(result);
	}

	@Override
	public String getName() {
		return "ShowSeanceById";
	}

}
