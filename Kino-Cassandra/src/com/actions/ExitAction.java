package com.actions;

import com.datastax.oss.driver.api.core.CqlSession;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExitAction implements Action{

	private CqlSession session;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		cv.print("closing the connection");
		session.close();
		System.exit(1);
	}

	@Override
	public String getName() {
		return "Exit";
	}

}
