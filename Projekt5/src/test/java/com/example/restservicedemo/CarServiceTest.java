package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.jayway.restassured.RestAssured;

public class CarServiceTest {

	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo";
	}

	@Test
	public void getCar() {
		get("/cars/0").then().assertThat().body("model", equalTo("Corsa"));

		Car aCar = get("/cars/0").as(Car.class);
		assertThat(aCar.getMake(), equalToIgnoringCase("Opel"));
	}

	@Test
	public void addCar() {

		Car aCar = new Car(2, "Ford", "Fiesta", 2011, new ArrayList<Person>());
		given().contentType("application/json; charset=UTF-16").body(aCar)
				.when().post("/cars/").then().assertThat().statusCode(201)
				.body(containsString("Car saved:"));
	}

	@Test
	public void schemaValid() {
		get("/cars/0").then().body(
				matchesJsonSchemaInClasspath("car-schema.json"));
	}

	@Test
	public void ownersTest() {
		get("/cars/0").then().body("owners.firstName[0]", equalTo("Grzegorz"))
				.body("owners.firstName[1]", equalTo("Daniel"));
	}

}
