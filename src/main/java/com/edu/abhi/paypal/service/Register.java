package com.edu.abhi.paypal.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.edu.abhi.paypal.dao.DataStore;
import com.edu.abhi.paypal.domain.StringID;

/**
 * 
 * @author abhishekkhare
 *
 */
@Path("/service")
public class Register {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String createCustomer(StringID id) {
		DataStore.store(id.getId());
		return "<p>" + "ID:" + id.getId() + " has been added to the store."  + "<p>";
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getCustomer(@PathParam("id") int id) {
		List<String> list = DataStore.getDataFromStore(id);
		return list;
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public String testService() {
		return "<p>" + this.getClass().getSimpleName() + "<p>";
	}
}
