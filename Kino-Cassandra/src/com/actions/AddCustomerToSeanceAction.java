package com.actions;

import com.entities.CustomerInSeance;
import com.persistence.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddCustomerToSeanceAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		
		Integer id = cv.getValidInt("podaj id rekordu");
		
		Integer customerId = cv.getValidInt("podaj Id klienta");
		Integer seanceId = cv.getValidInt("podaj Id seansu");
		
		CustomerInSeance cis = new CustomerInSeance();
		
		cis.setId(id);
		cis.setIdCustomer(customerId);
		cis.setIdSeance(seanceId);
		
		p.save(cis);
	}
	
	@Override
	public String getName() {
		return "AddCustomerToSeance";
	}
}
