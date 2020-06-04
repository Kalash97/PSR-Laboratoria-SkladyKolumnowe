package com.main;

import java.time.Duration;
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
import com.datastax.oss.driver.api.core.servererrors.AlreadyExistsException;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;
import com.tables.CustomerTableManager;
import com.tables.MovieTableManager;
import com.tables.SeanceTableManager;
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
		
		System.out.println("connecting");
		CqlSession session = CqlSession.builder().build();

		System.out.println("creating db");
		CreateKeyspace createKeyspace = SchemaBuilder.createKeyspace("cinema").withSimpleStrategy(1);
		try {
			session.execute(createKeyspace.build().setTimeout(Duration.ofSeconds(30)));
			System.out.println("DB created");
		} catch (Exception e) {
			System.out.println("DB already exists");
		}

		session.execute("USE cinema");

		System.out.println("creating table movie");
		MovieTableManager movieTableManager = new MovieTableManager(session);
		try {
			movieTableManager.createTable();
		} catch (AlreadyExistsException e) {
			System.out.println("table movie already exists");
		}

		System.out.println("creating table customer");
		CustomerTableManager customerTableManager = new CustomerTableManager(session);
		try {
			customerTableManager.createTable();
		} catch (AlreadyExistsException e) {
			System.out.println("table customer already exists");
		}
		
		System.out.println("creating table seance");
		SeanceTableManager seanceTableManager = new SeanceTableManager(session);
		try {
			seanceTableManager.createTable();
		}catch (AlreadyExistsException e) {
			System.out.println("table seance already exists");
		}
		
		actions.add(new CreateMovieAction(session, cv));
		actions.add(new ShowAllMoviesAction(session, cv));
		actions.add(new ShowMovieByIdAction(session, cv));
		actions.add(new DeleteMovieAction(session, cv));
		actions.add(new UpdateMovieAction(session, cv));
		
		actions.add(new CreateCustomerAction(session, cv));
		actions.add(new ShowAllCustomersAction(session, cv));
		actions.add(new ShowCustomerByIdAction(session, cv));
		actions.add(new DeleteCustomerAction(session, cv));
		actions.add(new UpdateCustomerAction(session, cv));
		
		actions.add(new CreateSeanceAction(session, cv));
		actions.add(new ShowAllSeancesAction(session, cv));
		actions.add(new ShowSeanceByIdAction(session, cv));
		actions.add(new DeleteSeanceAction(session, cv));
		actions.add(new UpdateSeanceAction(session, cv));
		
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
