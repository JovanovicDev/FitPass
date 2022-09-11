package services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;

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
import javax.ws.rs.POST;

import beans.Korisnik;
import beans.Trening;
import dao.KomentarDAO;
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
			
			String pathForComments = ctx.getRealPath("/data/comments.json");
			ctx.setAttribute("commentDAO", new KomentarDAO(pathForComments));
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	//@Produces(MediaType.APPLICATION_JSON)
	public void update(Trening t) throws JsonGenerationException, JsonMappingException, IOException {
		TreningDAO treningDao = (TreningDAO)ctx.getAttribute("trainingDAO");			
		
		if(!t.getSlika().contains("images")) {
			String imagePath = ctx.getRealPath("images/tt") + t.getId() + ".png";
			byte[] decodedImg = Base64.getDecoder().decode(t.getSlika());
			try (OutputStream stream = new FileOutputStream(imagePath)) {
				stream.write(decodedImg);
				stream.flush();
			}catch(Exception ex) { }
			t.setSlika("images/tt" + t.getId() + ".png");
		}
		
		treningDao.update(t);	
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> getTrainings() {
		TreningDAO treningDao = (TreningDAO)ctx.getAttribute("trainingDAO");
		return treningDao.findAll();
	}
	
	@GET
	@Path("/getById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Trening getById(@PathParam("id") String id) {
		TreningDAO treningDao = (TreningDAO)ctx.getAttribute("trainingDAO");
		return treningDao.getById(Integer.parseInt(id));
	}
	
	@GET
	@Path("/getTrainersInFacility/{text}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Korisnik> getTrainersInFacility(@PathParam("text") String text) {
		TreningDAO treningDao = (TreningDAO)ctx.getAttribute("trainingDAO");
		return treningDao.getTrainersInFacility(text);
	}
	
	@GET
	@Path("/getTrainingsInFacility/{text}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> getTrainingsInFacility(@PathParam("text") String text) {
		TreningDAO treningDao = (TreningDAO)ctx.getAttribute("trainingDAO");
		return treningDao.getTrainingsInFacility(text);
	}
	
	@POST
	@Path("/add")
	public Trening add(Trening t) throws JsonGenerationException, JsonMappingException, IOException {
		TreningDAO treningDao = (TreningDAO)ctx.getAttribute("trainingDAO");
		if (treningDao.getByName(t.getNaziv()) != null)
			return null;
		
		t.setId(treningDao.generateId());
		String imagePath = ctx.getRealPath("images/t") + t.getId() + ".png";
		byte[] decodedImg = Base64.getDecoder().decode(t.getSlika());
		try (OutputStream stream = new FileOutputStream(imagePath)) {
			stream.write(decodedImg);
			stream.flush();
		}catch(Exception ex) { }
		t.setSlika("images/t" + t.getId() + ".png");
		treningDao.addTraining(t);
		return t;
	}
	
}
