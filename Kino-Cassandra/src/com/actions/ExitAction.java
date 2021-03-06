package com.actions;

import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExitAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		cv.print("closing the connection");
		p.exitProgram();
		System.exit(1);
	}

	@Override
	public String getName() {
		return "Exit";
	}

}
