package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestService {

	@Context
	ServletContext ctx;
	@PostConstruct
	public void init() {
		
	}
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getName() {
		return "aa";
	}
}
