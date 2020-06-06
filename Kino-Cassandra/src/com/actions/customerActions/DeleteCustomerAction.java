package com.actions.customerActions;

import com.actions.Action;
import com.entities.Customer;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCustomerAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
				
		int id = cv.getValidInt("podaj ID");
		
		p.delete(id, Customer.class);
	}

	@Override
	public String getName() {
		return "DeleteCustomer";
	}

}
