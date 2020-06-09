package com.entities;

import lombok.Getter;
import lombok.Setter;

public class CustomerInSeance extends Entity{

	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private Integer idCustomer;
	
	@Getter
	@Setter
	private Integer idSeance;
}
