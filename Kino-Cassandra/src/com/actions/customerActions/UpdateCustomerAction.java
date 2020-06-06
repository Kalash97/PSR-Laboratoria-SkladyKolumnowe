package com.actions.customerActions;

import com.actions.Action;
import com.entities.Customer;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCustomerAction implements Action {

	private ConsoleView cv;
	private Persistence p;

	@Override
	public void launch() {
		int id = cv.getValidInt("podaj ID");

		cv.print("podaj imiê:");
		String name = cv.read();

		cv.print("podaj nazwisko:");
		String lastName = cv.read();

		if (!"".equals(name)) {
			p.update(id, Customer.class, "name", name);
		}

		if (!"".equals(lastName)) {
			p.update(id, Customer.class, "lastName", lastName);
		}

	}

	@Override
	public String getName() {
		return "UpdateCustomer";
	}

}
