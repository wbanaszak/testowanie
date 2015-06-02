package com.example.personrestservice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.restservicedemo.domain.Person;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;

public class PersonRestServiceTest {

	Person person;

	@Before
	public void setUp() {
		when().get("http://localhost:8080/restservicedemo/persons/deleteall");
		person = new Person("FOO", 1000);
	}

	@After
	public void tearDown() {
		when().get("http://localhost:8080/restservicedemo/persons/deleteall");
	}

	@Test
	public void statusCode() {
		when().get("http://localhost:8080/restservicedemo/persons/getallpeople")
				.then().statusCode(200);
	}

	@Test
	public void headerTest() {
		when().get("http://localhost:8080/restservicedemo/persons/getallpeople")
				.then().header("Content-Type", "application/json")
				.header("Transfer-Encoding", "chunked");
	}

	@Test
	public void schemaValidation() {
		get("http://localhost:8080/restservicedemo/persons/0").then().body(
				matchesJsonSchemaInClasspath("person-schema.json"));
	}

	@Test
	public void createPersonTest() {
		Person person = new Person("FOO", 1000);

		given().body(person).header("Content-Type", "application/json").when()
				.post("http://localhost:8080/restservicedemo/persons/create")
				.then().body(equalTo("Person saved"));
	}

	@Test
	public void bodyTest() {
		given().body(person).header("Content-Type", "application/json").when()
				.post("http://localhost:8080/restservicedemo/persons/create");
		when().get("http://localhost:8080/restservicedemo/persons/getallpeople")
				.then().body("person.firstName", equalTo("FOO"))
				.statusCode(200);
	}

	@Test
	public void deleteAllPeopleTest() {
		when().get("http://localhost:8080/restservicedemo/persons/deleteall")
				.then().body(equalTo("You killed all people")).statusCode(202);
	}

}
