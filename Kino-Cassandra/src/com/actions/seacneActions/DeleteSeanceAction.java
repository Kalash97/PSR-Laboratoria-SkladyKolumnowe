package com.actions.seacneActions;

import com.actions.Action;
import com.entities.Seance;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteSeanceAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		int id = cv.getValidInt("podaj ID");
		
		p.delete(id, Seance.class);
	}

	@Override
	public String getName() {
		return "DeleteSeance";
	}

}
