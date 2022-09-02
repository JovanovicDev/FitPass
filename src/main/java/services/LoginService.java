package services;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import beans.Korisnik;
import dao.KorisnikDAO;
import dao.SportskiObjekatDAO;
import dao.TreningDAO;

@Path("/log")
public class LoginService {
	
	@Context
	ServletContext ctx;
	
	public LoginService() {}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("userDAO") == null) {
	    	String contextPath = ctx.getRealPath("/data/users.json");
			ctx.setAttribute("userDAO", new KorisnikDAO(contextPath));
			
			String pathForSportFacilities = ctx.getRealPath("/data/sportFacilities.json");
			ctx.setAttribute("sportFacilityDAO", new SportskiObjekatDAO(pathForSportFacilities));
			
			String pathForTrainings = ctx.getRealPath("/data/trainings.json");
			ctx.setAttribute("trainingDAO", new TreningDAO(pathForTrainings));
		}
	}
	
	@POST
	@Path("/in")
	@Consumes(MediaType.APPLICATION_JSON)
	public Korisnik login(HashMap<String, String> values) {
		KorisnikDAO userDao = (KorisnikDAO) ctx.getAttribute("userDAO");
		Korisnik loggedUser = userDao.find(values.get("username"), values.get("password"));

		return loggedUser;
	}
}
