package services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Korisnik;
import beans.SportskiObjekat;
import beans.TipSportskogObjekta;
import dao.KomentarDAO;
import dao.KorisnikDAO;
import dao.SportskiObjekatDAO;
import dao.TreningDAO;

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
			
			String pathForTrainings = ctx.getRealPath("/data/trainings.json");
			ctx.setAttribute("trainingDAO", new TreningDAO(pathForTrainings));
			
			String pathForComments = ctx.getRealPath("/data/comments.json");
			ctx.setAttribute("commentDAO", new KomentarDAO(pathForComments));
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
	
	@GET
	@Path("/getById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SportskiObjekat getById(@PathParam("id") String id) {
		SportskiObjekatDAO sportskiObjekatDAO = (SportskiObjekatDAO)ctx.getAttribute("sportFacilityDAO");
		return sportskiObjekatDAO.getById(Integer.parseInt(id));
	}
	
	@POST
	@Path("/add")
	public SportskiObjekat add(SportskiObjekat objekat) throws JsonGenerationException, JsonMappingException, IOException {
		SportskiObjekatDAO sportskiObjekatDAO = (SportskiObjekatDAO)ctx.getAttribute("sportFacilityDAO");
		if (sportskiObjekatDAO.getByName(objekat.getNaziv()) != null)
			return null;
		
		objekat.setId(sportskiObjekatDAO.generateId());
		String imagePath = ctx.getRealPath("images/") + objekat.getId() + ".png";
		byte[] decodedImg = Base64.getDecoder().decode(objekat.getLogo());
		try (OutputStream stream = new FileOutputStream(imagePath)) {
			stream.write(decodedImg);
			stream.flush();
		}catch(Exception ex) { }
		objekat.setLogo("images/" + objekat.getId() + ".png");
		sportskiObjekatDAO.addSportFacility(objekat);
		return objekat;
	}
	
	@GET
	@Path("/getFacilityByManagerUsername/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public SportskiObjekat getFacilityByManagerUsername(@PathParam("username") String username) {
		SportskiObjekatDAO sportskiObjekatDAO = (SportskiObjekatDAO)ctx.getAttribute("sportFacilityDAO");
		return sportskiObjekatDAO.getFacilityByManagerUsername(username);
	}
	
	@GET
	@Path("/filterType/{text}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> filterType(@PathParam("text") String text) {
		SportskiObjekatDAO sportskiObjekatDAO = (SportskiObjekatDAO)ctx.getAttribute("sportFacilityDAO");
		return sportskiObjekatDAO.filterType(text);
	}
	
	@GET
	@Path("/filterWorking")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> filterWorking() {
		SportskiObjekatDAO sportskiObjekatDAO = (SportskiObjekatDAO)ctx.getAttribute("sportFacilityDAO");
		return sportskiObjekatDAO.filterWorking();
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> getFilteredFacilities(@QueryParam("type") TipSportskogObjekta type,
			@QueryParam("city") String city, @QueryParam("name") String name, @QueryParam("averageGrade") String averageGrade){
		SportskiObjekatDAO sportskiObjekatDAO = (SportskiObjekatDAO)ctx.getAttribute("sportFacilityDAO");
		return sportskiObjekatDAO.search(type, city, name, averageGrade);
	}
	
}
