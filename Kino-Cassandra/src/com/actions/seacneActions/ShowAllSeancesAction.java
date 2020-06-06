package com.actions.seacneActions;

import java.util.List;

import com.actions.Action;
import com.entities.Seance;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowAllSeancesAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		List<String> all = p.findAll(Seance.class);
		cv.print(all);
	}

	@Override
	public String getName() {
		return "ShowAllSeances";
	}

}
