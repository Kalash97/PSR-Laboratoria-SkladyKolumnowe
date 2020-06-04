package com.actions.customerActions;

import java.time.Duration;

import com.actions.Action;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowCustomerByIdAction implements Action{

	private CqlSession session;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		cv.print("podaj ID");
		int id = Integer.parseInt(cv.getValidInt());
		
		Select select = QueryBuilder.selectFrom("customer").all().whereColumn("id").isEqualTo(QueryBuilder.literal(id));
		ResultSet resultSet = session.execute(select.build().setTimeout(Duration.ofSeconds(30)));
		
		if (resultSet.iterator().hasNext()) {
			cv.showAllCustomers(resultSet);
		}else {
			cv.print("nie ma takiego widza");
		}
	}

	@Override
	public String getName() {
		return "ShowCustomerById";
	}

}
