package com.tables;

import java.time.Duration;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateTable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SeanceTableManager implements TableManagerInterface{

	private CqlSession session;
	
	@Override
	public void createTable() {
		CreateTable createTable = SchemaBuilder.createTable("seance")
			.withPartitionKey("id", DataTypes.INT)
			.withColumn("priceOfTicket", DataTypes.DOUBLE);
		
		session.execute(createTable.build().setTimeout(Duration.ofSeconds(30)));
	}

}
