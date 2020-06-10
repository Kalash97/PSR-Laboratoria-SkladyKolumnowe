package com.actions;

import com.persistence.Persistence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DropDBAction implements Action{
	
	private Persistence p;
	
	@Override
	public void launch() {
		p.dropDB("cinema");
		System.exit(1);
	}

	@Override
	public String getName() {
		return "DropDB";
	}

}
