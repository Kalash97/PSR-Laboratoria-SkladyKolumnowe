package com.tables;

import java.time.Duration;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateTable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MovieTableManager implements TableManagerInterface{

	private CqlSession session;
	
	public void createTable() {
		
		CreateTable createTable = SchemaBuilder.createTable("movie")
			.withPartitionKey("id", DataTypes.INT)
			.withColumn("title", DataTypes.TEXT)
			.withColumn("durationTimeInMinutes", DataTypes.INT);
		
		session.execute(createTable.build().setTimeout(Duration.ofSeconds(30)));
		
	}
	
}
