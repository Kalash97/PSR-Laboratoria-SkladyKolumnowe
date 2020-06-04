package com.tables;

import java.time.Duration;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateTable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerTableManager implements TableManagerInterface{

	private CqlSession session;
	
	@Override
	public void createTable() {
		
		CreateTable createTable = SchemaBuilder.createTable("customer")
			.withPartitionKey("id", DataTypes.INT)
			.withColumn("name", DataTypes.TEXT)
			.withColumn("lastName", DataTypes.TEXT);
		
		session.execute(createTable.build().setTimeout(Duration.ofSeconds(30)));
	}

}
