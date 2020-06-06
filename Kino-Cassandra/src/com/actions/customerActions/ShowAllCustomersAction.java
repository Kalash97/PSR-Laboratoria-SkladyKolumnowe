package com.actions.customerActions;

import java.util.List;

import com.actions.Action;
import com.entities.Customer;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowAllCustomersAction implements Action{
	
	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		List<String> all = p.findAll(Customer.class);
		cv.print(all);
	}

	@Override
	public String getName() {
		return "ShowAllCustomers";
	}

}
