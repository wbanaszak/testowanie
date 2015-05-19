package com.example.restservicedemo.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;

@Path("persons")
public class PersonRESTService {	
	
	private PersonManager pm = new PersonManager();
	
	@GET
	@Path("/{personId}")
	@Produces("application/json")
	public Person getPerson(@PathParam("personId") Long id){
		Person p = pm.getPerson(id);
		return p;
	}
	
	@GET
	@Path("/test")
	@Produces("text/html")
	public String test(){
		return "REST Service is running";
	}

	@GET
	@Path("/getallpeople")
	@Produces("application/json")
	public List<Person> getPeople() {
		return pm.getAllPersons();
	}
	
	@POST
	@Path("/create")
	@Produces("text/html")
	public Response createPerson(Person person) {
		pm.addPerson(person);
		String result = "Person saved";
		return Response.status(201).entity(result).build(); 
	}
	
	@GET
	@Path("/deleteall")
	@Produces("text/html")
	public Response deleteAllPeople() {
		pm.clearPersons();
		String result = "You killed all people";
		return Response.status(202).entity(result).build(); 
	}
}
