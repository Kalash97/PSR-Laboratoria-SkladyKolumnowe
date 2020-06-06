package com.entities;

import lombok.Getter;
import lombok.Setter;

public class Customer extends Entity{
	
	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String lastName;
	
}
