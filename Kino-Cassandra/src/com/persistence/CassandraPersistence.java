package com.persistence;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.servererrors.AlreadyExistsException;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;
import com.datastax.oss.driver.api.querybuilder.schema.CreateTable;
import com.datastax.oss.driver.api.querybuilder.schema.Drop;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;
import com.entities.Entity;

public class CassandraPersistence implements Persistence {

	private CqlSession session;

	public CassandraPersistence() {

		session = CqlSession.builder().build();

		CreateKeyspace createKeyspace = SchemaBuilder.createKeyspace("cinema").withSimpleStrategy(1);
		try {
			session.execute(createKeyspace.build().setTimeout(Duration.ofSeconds(200)));
		} catch (AlreadyExistsException e) {

		}

		session.execute("USE cinema");

		try {
			CreateTable createTableMovie = SchemaBuilder.createTable("Movie").withPartitionKey("id", DataTypes.INT)
					.withColumn("title", DataTypes.TEXT).withColumn("durationTimeInMinutes", DataTypes.INT);

			session.execute(createTableMovie.build().setTimeout(Duration.ofSeconds(200)));
		} catch (AlreadyExistsException e) {

		}

		try {
			CreateTable createTableCustomer = SchemaBuilder.createTable("Customer")
					.withPartitionKey("id", DataTypes.INT).withColumn("name", DataTypes.TEXT)
					.withColumn("lastName", DataTypes.TEXT);

			session.execute(createTableCustomer.build().setTimeout(Duration.ofSeconds(200)));
		} catch (AlreadyExistsException e) {

		}

		try {
			CreateTable createTableSeance = SchemaBuilder.createTable("Seance").withPartitionKey("id", DataTypes.INT)
					.withColumn("priceOfTicket", DataTypes.DOUBLE).withColumn("movieId", DataTypes.INT);

			session.execute(createTableSeance.build().setTimeout(Duration.ofSeconds(200)));
		} catch (AlreadyExistsException e) {

		}
		
		try {
			CreateTable createTableCustomerInSeance = SchemaBuilder.createTable("CustomerInSeance").withPartitionKey("id", DataTypes.INT)
				.withColumn("idCustomer", DataTypes.INT)
				.withColumn("idSeance", DataTypes.INT);
			
			session.execute(createTableCustomerInSeance.build().setTimeout(Duration.ofSeconds(200)));
		}catch (AlreadyExistsException e) {

		}
	}

	@Override
	public void save(Entity e) {

		String insert = entityToInsert(e);

		session.execute(insert);

	}

	private String entityToInsert(Entity e) {

		String insert = "INSERT INTO " + e.getClass().getSimpleName() + "(";
		String values = " VALUES (";

		Field[] fields = e.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {

			Field f = fields[i];

			try {
				boolean accessible = f.isAccessible();
				f.setAccessible(true);

				String fieldName = f.getName();
				String value = f.get(e) == null ? null : f.get(e).toString();

				insert += fieldName + ",";
				values += f.getType().equals(String.class) ? "'" + value + "'," : value + ",";

				f.setAccessible(accessible);

			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}

		}

		insert = StringUtils.chop(insert);
		values = StringUtils.chop(values);

		insert += ")";

		values += ")";

		return insert + values;
	}

	@Override
	public List<String> findAll(Class<? extends Entity> entity) {
		Select select = QueryBuilder.selectFrom(entity.getSimpleName()).all();
		SimpleStatement simpleStatement = select.build();
		ResultSet resultSet = session.execute(simpleStatement.setTimeout(Duration.ofSeconds(30)));

		return buildEntityString(entity, resultSet);
	}

	private List<String> buildEntityString(Class<? extends Entity> entity, ResultSet resultSet) {
		Field[] fields = entity.getDeclaredFields();
		List<String> s = new ArrayList<String>();
		for (Row row : resultSet) {
			String obj = entity.getSimpleName() + " ";

			for (Field f : fields) {
				if (f.getType().equals(Integer.class)) {
					obj += f.getName() + ": " + row.getInt(f.getName());

				}

				if (f.getType().equals(Double.class)) {
					obj += f.getName() + ": " + row.getDouble(f.getName());
				}

				if (f.getType().equals(String.class)) {
					obj += f.getName() + ": " + row.getString(f.getName());
				}

				if (f.getType().equals(List.class)) {
					obj += f.getName() + ": " + row.getList(f.getName(), Integer.class);
				}

				obj += " ";
			}

			s.add(obj);
		}

		return s;
	}

	@Override
	public String findById(Integer id, Class<? extends Entity> entity) {

		Select select = QueryBuilder.selectFrom(entity.getSimpleName()).all().whereColumn("id")
				.isEqualTo(QueryBuilder.literal(id));
		ResultSet resultSet = session.execute(select.build().setTimeout(Duration.ofSeconds(30)));

		List<String> result = buildEntityString(entity, resultSet);
		return result.size() == 1 ? result.get(0) : "No results";
	}

	@Override
	public void update(Integer id, Class<? extends Entity> entity, String fieldName, Object newValue) {

		Update update = QueryBuilder.update(entity.getSimpleName()).setColumn(fieldName,
				newValue instanceof String ? QueryBuilder.raw("'" + newValue + "'") : QueryBuilder.literal(newValue))
				.whereColumn("id").isEqualTo(QueryBuilder.literal(id));

		session.execute(update.build().setTimeout(Duration.ofSeconds(30)));

	}

	@Override
	public void delete(Integer id, Class<? extends Entity> entity) {
		Delete delete = QueryBuilder.deleteFrom(entity.getSimpleName()).whereColumn("id")
				.isEqualTo(QueryBuilder.literal(id));

		session.execute(delete.build().setTimeout(Duration.ofSeconds(30)));

	}

	@Override
	public void dropDB(String name) {
		Drop dropKeyspace = SchemaBuilder.dropKeyspace(name);
		session.execute(dropKeyspace.build().setTimeout(Duration.ofSeconds(30)));
	}

	@Override
	public Integer getEntityId(Integer id, Class<? extends Entity> entity) {

		Select select = QueryBuilder.selectFrom(entity.getSimpleName()).column("id")
				.whereColumn("id").isEqualTo(QueryBuilder.literal(id));
		
		ResultSet resultSet = session.execute(select.build().setTimeout(Duration.ofSeconds(30)));

		return resultSet.iterator().hasNext() ? id : null;
	}

	@Override
	public boolean checkIfPresent(Integer id, Class<? extends Entity> entity) {
		
		Select select = QueryBuilder.selectFrom(entity.getSimpleName()).all()
			.whereColumn("id").isEqualTo(QueryBuilder.literal(id));
		
		ResultSet resultSet = session.execute(select.build().setTimeout(Duration.ofSeconds(30)));
		
		return resultSet.iterator().hasNext() ? true : false;
	}

	
	
	@Override
	public void exitProgram() {
		session.close();
	}

}
