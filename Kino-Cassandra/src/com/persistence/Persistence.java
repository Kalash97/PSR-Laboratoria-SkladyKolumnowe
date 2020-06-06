package com.persistence;

import java.util.List;

import com.entities.Entity;

public interface Persistence{

	public void save(Entity e);
	
	public List<String> findAll(Class<? extends Entity> entity);
	
	public String findById(Integer id, Class<? extends Entity> entity); 
	
	void update(Integer id, Class<? extends Entity> entity, String fieldName, Object newValue);

	void delete(Integer id, Class<? extends Entity> entity);
}
