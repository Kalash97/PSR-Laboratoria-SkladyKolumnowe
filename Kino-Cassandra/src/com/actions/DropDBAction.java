package com.actions;

import com.datastax.oss.driver.api.core.CqlSession;
import com.persistence.Persistence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DropDBAction implements Action{
	
	private CqlSession session;
	private Persistence p;
	
	@Override
	public void launch() {
		p.dropDB("cinema");
		session.close();
		System.exit(1);
	}

	@Override
	public String getName() {
		return "DropDB";
	}

}
