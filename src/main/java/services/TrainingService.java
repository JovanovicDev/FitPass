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
import beans.SportskiObjekat;
import beans.Trening;
import dao.KorisnikDAO;
import dao.SportskiObjekatDAO;
import dao.TreningDAO;

@Path("/trainings")
public class TrainingService {
	@Context
	ServletContext ctx;
	
	public TrainingService() {}
	
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
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	//@Produces(MediaType.APPLICATION_JSON)
	public void update(Trening training) throws JsonGenerationException, JsonMappingException, IOException {
		TreningDAO treningDao = (TreningDAO)ctx.getAttribute("trainingDAO");
		treningDao.update(training);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> getTrainings() {
		TreningDAO treningDao = (TreningDAO)ctx.getAttribute("trainingDAO");
		return treningDao.findAll();
	}
	
	@GET
	@Path("/getTrainersInFacility/{text}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> getTrainersInFacility(@PathParam("text") String text) {
		TreningDAO treningDao = (TreningDAO)ctx.getAttribute("trainingDAO");
		return treningDao.getTrainersInFacility(text);
	}
	
	
}