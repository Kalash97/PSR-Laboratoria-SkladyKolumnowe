package com.entities;

import lombok.Getter;
import lombok.Setter;

public class Movie extends Entity{
	

	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private String title;
	
	@Getter
	@Setter
	private Integer durationTimeInMinutes;
	
}
