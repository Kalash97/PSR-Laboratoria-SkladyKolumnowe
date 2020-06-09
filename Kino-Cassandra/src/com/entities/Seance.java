package com.entities;

import lombok.Getter;
import lombok.Setter;

public class Seance extends Entity{
	
	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private Double priceOfTicket;
	
	@Getter
	@Setter
	private Integer movieId;
	
}
