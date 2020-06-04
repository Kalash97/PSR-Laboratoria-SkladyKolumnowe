package com.actions.seacneActions;

import java.time.Duration;

import com.actions.Action;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;
import com.utils.ValidUtil;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateSeanceAction implements Action{

	private CqlSession session;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		cv.print("podaj ID");
		int id = Integer.parseInt(cv.getValidInt());
		cv.print("podaj cenê biletu:");
		String priceOfTicket = cv.read();
		
		Select select = QueryBuilder.selectFrom("seance").all().whereColumn("id").isEqualTo(QueryBuilder.literal(id));
		ResultSet resultSet = session.execute(select.build().setTimeout(Duration.ofSeconds(30)));
		
		if (resultSet.iterator().hasNext()) {
			if(!"".equals(priceOfTicket)) {
				if(ValidUtil.isDoubleInstance(priceOfTicket)) {
					double priceOfticketDouble = Double.parseDouble(priceOfTicket);
					Update update = QueryBuilder.update("seance")
						.setColumn("priceOfTicket", QueryBuilder.literal(priceOfticketDouble))
						.whereColumn("id").isEqualTo(QueryBuilder.literal(id));
					
					session.execute(update.build().setTimeout(Duration.ofSeconds(30)));
				}
			}
		}else {
			cv.print("Nie ma takiego filmu");
		}
	}

	@Override
	public String getName() {
		return "UpdateSeance";
	}

}
