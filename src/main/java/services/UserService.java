package services;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import javax.ws.rs.core.Context;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import beans.Korisnik;
import dao.KorisnikDAO;

@Path("/users")
public class UserService {
	@Context
	ServletContext ctx;
	
	public UserService() {}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("userDAO") == null) {
	    	String contextPath = ctx.getRealPath("/data/users.json");
			ctx.setAttribute("userDAO", new KorisnikDAO(contextPath));
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	//@Produces(MediaType.APPLICATION_JSON)
	public void update(Korisnik user) throws JsonGenerationException, JsonMappingException, IOException {
		KorisnikDAO korisnikDao = (KorisnikDAO)ctx.getAttribute("userDAO");
		korisnikDao.update(user);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> getUsers() {
		KorisnikDAO korisnikDao = (KorisnikDAO)ctx.getAttribute("userDAO");
		return korisnikDao.findAll();
	}
	
	@GET
	@Path("/search/{text}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> search(@PathParam("text") String text) {
		KorisnikDAO korisnikDao = (KorisnikDAO)ctx.getAttribute("userDAO");
		return korisnikDao.search(text);
	}
	
	@GET
	@Path("/filterRole/{text}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> filterRole(@PathParam("text") String text) {
		KorisnikDAO korisnikDao = (KorisnikDAO)ctx.getAttribute("userDAO");
		return korisnikDao.filterRole(text);
	}
	
	@GET
	@Path("/filterType/{text}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> filterType(@PathParam("text") String text) {
		KorisnikDAO korisnikDao = (KorisnikDAO)ctx.getAttribute("userDAO");
		return korisnikDao.filterType(text);
	}
}
