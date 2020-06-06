package com.main;

import java.util.ArrayList;
import java.util.List;

import com.actions.Action;
import com.actions.ExitAction;
import com.actions.customerActions.CreateCustomerAction;
import com.actions.customerActions.DeleteCustomerAction;
import com.actions.customerActions.ShowAllCustomersAction;
import com.actions.customerActions.ShowCustomerByIdAction;
import com.actions.customerActions.UpdateCustomerAction;
import com.actions.movieActions.CreateMovieAction;
import com.actions.movieActions.DeleteMovieAction;
import com.actions.movieActions.ShowAllMoviesAction;
import com.actions.movieActions.ShowMovieByIdAction;
import com.actions.movieActions.UpdateMovieAction;
import com.actions.seacneActions.CreateSeanceAction;
import com.actions.seacneActions.DeleteSeanceAction;
import com.actions.seacneActions.ShowAllSeancesAction;
import com.actions.seacneActions.ShowSeanceByIdAction;
import com.actions.seacneActions.UpdateSeanceAction;
import com.datastax.oss.driver.api.core.CqlSession;
import com.persistence.CassandraPersistence;
import com.persistence.Persistence;
import com.view.ConsoleView;

public class Main {

	private static List<Action> actions;
	private static ConsoleView cv;
	
	public static void main(String[] args) {

		// usruchamioanie serwera z CMD: cassandra\bin\cassandra.bat
		init();
	
		while (true) {
			cv.print("Lista dostêpnych akcji:");
			showActions();
			cv.print("");
			cv.print("Podaj akcjê");
			runAction(cv.read());
		}

	}
	
	public static void init() {
		
		actions = new ArrayList<Action>();
		cv = new ConsoleView();
		
		Persistence cp = new CassandraPersistence();
		
		
		System.out.println("connecting");
		CqlSession session = CqlSession.builder().build();
		
		actions.add(new CreateMovieAction(cp, cv));
		actions.add(new ShowAllMoviesAction(cp, cv));
		actions.add(new ShowMovieByIdAction(cv, cp));
		actions.add(new DeleteMovieAction(cp, cv));
		actions.add(new UpdateMovieAction(cv, cp));
		
		actions.add(new CreateCustomerAction(cp, cv));
		actions.add(new ShowAllCustomersAction(cp, cv));
		actions.add(new ShowCustomerByIdAction(cv, cp));
		actions.add(new DeleteCustomerAction(cp, cv));
		actions.add(new UpdateCustomerAction(cv, cp));
		
		actions.add(new CreateSeanceAction(cp, cv));
		actions.add(new ShowAllSeancesAction(cp, cv));
		actions.add(new ShowSeanceByIdAction(cv, cp));
		actions.add(new DeleteSeanceAction(cp, cv));
		actions.add(new UpdateSeanceAction(cv, cp));
		
		actions.add(new ExitAction(session, cv));
	}

	private static void runAction(String name) {
		for (Action a : actions) {
			if (name.equals(a.getName())) {
				launchAction(a);
				return;
			}
		}
		cv.print("Nie ma takiej akcji: " + name);
	}
	
	private static void launchAction(Action a) {
			a.launch();
	}
	
	private static void showActions() {
		for (Action a : actions) {
			cv.print(" " + a.getName());
		}
	}
	
}
