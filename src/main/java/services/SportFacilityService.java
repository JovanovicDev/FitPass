package services;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Korisnik;
import beans.SportskiObjekat;
import dao.KorisnikDAO;
import dao.SportskiObjekatDAO;

@Path("/sportFacilities")
public class SportFacilityService {
	@Context
	ServletContext ctx;
	
	public SportFacilityService() {}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("userDAO") == null) {
	    	String contextPath = ctx.getRealPath("/data/users.json");
			ctx.setAttribute("userDAO", new KorisnikDAO(contextPath));
			
			String pathForSportFacilities = ctx.getRealPath("/data/sportFacilities.json");
			ctx.setAttribute("sportFacilityDAO", new SportskiObjekatDAO(pathForSportFacilities));
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	//@Produces(MediaType.APPLICATION_JSON)
	public void update(SportskiObjekat objekat) throws JsonGenerationException, JsonMappingException, IOException {
		SportskiObjekatDAO sportskiObjekatDAO = (SportskiObjekatDAO)ctx.getAttribute("sportFacilityDAO");
		sportskiObjekatDAO.update(objekat);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> getSportFacilities() {
		SportskiObjekatDAO sportskiObjekatDAO = (SportskiObjekatDAO)ctx.getAttribute("sportFacilityDAO");
		return sportskiObjekatDAO.findAll();
	}
	
}
