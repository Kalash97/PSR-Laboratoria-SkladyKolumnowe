package com.actions.customerActions;

import com.actions.Action;
import com.entities.Customer;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowCustomerByIdAction implements Action{

	private ConsoleView cv;
	private Persistence p;
	
	@Override
	public void launch() {
		int id = cv.getValidInt("podaj ID");
		
		String result = p.findById(id, Customer.class);
		cv.print(result);
	}

	@Override
	public String getName() {
		return "ShowCustomerById";
	}

}
