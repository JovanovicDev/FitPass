package services;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Korisnik;
import beans.Pol;
import beans.Uloga;
import dao.KomentarDAO;
import dao.KorisnikDAO;
import dao.SportskiObjekatDAO;
import dao.TreningDAO;

@Path("/registration")
public class RegistracijaServis {
	
	@Context
	ServletContext ctx;
	
	public RegistracijaServis() {}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("userDAO") == null) {
	    	String contextPath = ctx.getRealPath("/data/users.json");
			ctx.setAttribute("userDAO", new KorisnikDAO(contextPath));
			
			String pathForSportFacilities = ctx.getRealPath("/data/sportFacilities.json");
			ctx.setAttribute("sportFacilityDAO", new SportskiObjekatDAO(pathForSportFacilities));
			
			String pathForTrainings = ctx.getRealPath("/data/trainings.json");
			ctx.setAttribute("trainingDAO", new TreningDAO(pathForTrainings));
			
			String pathForComments = ctx.getRealPath("/data/comments.json");
			ctx.setAttribute("commentDAO", new KomentarDAO(pathForComments));
		}
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(HashMap<String, String> values) throws IOException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		
		KorisnikDAO userDao = (KorisnikDAO) ctx.getAttribute("userDAO");
		Korisnik userExists = userDao.getByUsername(values.get("username"));
		if (userExists != null) {
			return Response.status(400).entity("User already exists").build();
		}
		
		Korisnik newuser = new Korisnik(values.get("username"), values.get("password"), values.get("name"), values.get("lastName"), Pol.valueOf(values.get("gender")), values.get("dateOfBirth"));
		
		if(values.get("role") != null) {
			if(values.get("role").equals("MENADZER")) {
				newuser.setUloga(Uloga.MENADZER);
			} else {
				newuser.setUloga(Uloga.TRENER);
			}
		}
		
		userDao.addUser(newuser);
		String jsonString = mapper.writeValueAsString(newuser);
		userDao.saveChanges();
		
		return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
	}
}

