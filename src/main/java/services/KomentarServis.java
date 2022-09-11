package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Komentar;
import dao.KomentarDAO;
import dao.KorisnikDAO;
import dao.SportskiObjekatDAO;
import dao.TreningDAO;

@Path("/comments")
public class KomentarServis {

	@Context
	ServletContext ctx;
	
	public KomentarServis() {}
	
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
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Komentar> getComments() {
		KomentarDAO komentarDao = (KomentarDAO)ctx.getAttribute("commentDAO");
		return komentarDao.findAll();
	}
	
	@GET
	@Path("/getCommentsOnFacility/{text}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Komentar> getCommentsOnFacility(@PathParam("text") String text) {
		KomentarDAO komentarDao = (KomentarDAO)ctx.getAttribute("commentDAO");
		return komentarDao.getCommentsOnFacility(text);
	}
}
