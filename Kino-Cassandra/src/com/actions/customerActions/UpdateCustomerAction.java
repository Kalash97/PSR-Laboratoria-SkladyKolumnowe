package com.actions.customerActions;

import java.time.Duration;

import com.actions.Action;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCustomerAction implements Action {

	private CqlSession session;
	private ConsoleView cv;

	@Override
	public void launch() {
		cv.print("podaj ID");
		int id = Integer.parseInt(cv.getValidInt());

		cv.print("podaj imiê:");
		String name = cv.read();

		cv.print("podaj nazwisko:");
		String lastName = cv.read();

		Select select = QueryBuilder.selectFrom("customer").all().whereColumn("id").isEqualTo(QueryBuilder.literal(id));
		ResultSet resultSet = session.execute(select.build().setTimeout(Duration.ofSeconds(30)));

		if (resultSet.iterator().hasNext()) {
			if (!"".equals(name)) {
				Update update = QueryBuilder.update("customer").setColumn("name", QueryBuilder.raw("'" + name + "'"))
						.whereColumn("id").isEqualTo(QueryBuilder.literal(id));

				session.execute(update.build().setTimeout(Duration.ofSeconds(30)));
			}

			if (!"".equals(lastName)) {
				Update update = QueryBuilder.update("customer")
						.setColumn("lastName", QueryBuilder.raw("'" + lastName + "'")).whereColumn("id")
						.isEqualTo(QueryBuilder.literal(id));

				session.execute(update.build().setTimeout(Duration.ofSeconds(30)));
			}
		} else {
			cv.print("Nie ma takiego widza");
		}
	}

	@Override
	public String getName() {
		return "UpdateCustomer";
	}

}
