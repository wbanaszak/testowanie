package com.example.personrestservice;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

public class PersonServiceRESTDBTest {

	private static IDatabaseConnection connection;
	private static IDatabaseTester databaseTester;
	static IDataSet dataSet;
	static Connection jdbcConnection;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo";

		jdbcConnection = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		connection = new DatabaseConnection(jdbcConnection);

		databaseTester = new JdbcDatabaseTester("org.hsqldb.jdbcDriver",
				"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		 dataSet = new FlatXmlDataSetBuilder()
				.build(new FileInputStream(new File(
						"src/test/resources/PersonHasCar.xml")));
	}

	@Before
	public void setUp() throws Exception {
		IDatabaseConnection con = new DatabaseConnection(jdbcConnection);
		DatabaseOperation.CLEAN_INSERT.execute(con, dataSet);
	}

	@Test
	public void addCarToPerson() throws Exception {

		given().contentType("application/json").when()
				.get("/persons/1/addCar/4").then().assertThat().statusCode(201);

		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("Person_Has_Car");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable(
				actualTable, new String[] { "ID" });

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(
				"src/test/resources/expectedTable.xml"));
		ITable expectedTable = expectedDataSet.getTable("Person_Has_Car");

		Assertion.assertEquals(expectedTable, filteredTable);
	}

	@Test
	public void addCarToPerson2() throws Exception {

		given().contentType("application/json").when()
				.get("/persons/1/addCar/4").then().assertThat().statusCode(201);

		given().contentType("application/json").when()
				.get("/persons/10/addCar/10").then().assertThat()
				.statusCode(201);

		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("Person_Has_Car");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable(
				actualTable, new String[] { "ID" });

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(
				"src/test/resources/expectedTable2.xml"));
		ITable expectedTable = expectedDataSet.getTable("Person_Has_Car");

		Assertion.assertEquals(expectedTable, filteredTable);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		databaseTester.onTearDown();
	}

}
