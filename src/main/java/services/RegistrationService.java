package services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Korisnik;
import beans.Pol;
import beans.Uloga;
import dao.KorisnikDAO;

@Path("/registration")
public class RegistrationService {
	
	@Context
	ServletContext ctx;
	
	public RegistrationService() {}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("userDAO") == null) {
	    	String contextPath = ctx.getRealPath("/data/users.json");
			ctx.setAttribute("userDAO", new KorisnikDAO(contextPath));
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

		userDao.addUser(newuser);
		String jsonString = mapper.writeValueAsString(newuser);
		userDao.saveChanges();
		
		return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
	}
}
