package com.actions.customerActions;

import com.actions.Action;
import com.entities.Customer;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCustomerAction implements Action{

//	private CqlSession session;
	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		int id = cv.getValidInt("podaj ID:");
		
		cv.print("Podaj imiê");
		String name = cv.read();
		
		cv.print("podaj nazwisko");
		String lastName = cv.read();
		
		Customer c = new Customer();
		
		c.setId(id);
		c.setLastName(lastName);
		c.setName(name);
		
		p.save(c);
	}

	@Override
	public String getName() {
		return "CreateCustomer";
	}

}
