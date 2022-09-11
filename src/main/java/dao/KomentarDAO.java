package dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Komentar;

public class KomentarDAO {
	private Map<String, Komentar> comments = new HashMap<>();
	String path;
	
	public KomentarDAO() {}
	
	public KomentarDAO(String contextPath){
		this.path = contextPath;
		try {
			loadComments(contextPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadComments(String contextPath) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		
		List<Komentar> commentData = mapper.readValue(new File(contextPath), new TypeReference<List<Komentar>>(){});
		for(Komentar k : commentData) {
			comments.put(Integer.toString(k.getId()), k);
		}	
	}
	
	public String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
	
	public void addComment(Komentar comment) {
		comments.put(Integer.toString(comment.getId()), comment);
	}
	
	public Komentar getById(int id) {
		return comments.get(Integer.toString(id));
	}
	
	public Collection<Komentar> findAll() {
		return comments.values();
	}
	
	public void update(Komentar comment) throws JsonGenerationException, JsonMappingException, IOException {
		comments.put(Integer.toString(comment.getId()), comment);
		saveChanges();
	}
	
	public void saveChanges() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		
		List<Komentar> commentList  = new ArrayList<>();
		for(Map.Entry<String, Komentar> entry : comments.entrySet()) {
			commentList.add(new Komentar(entry.getValue()));
		}
		mapper.writeValue(new File(path), commentList);
	}
	
	public Collection<Komentar> getCommentsOnFacility(String text){
		List<Komentar> commentsOnFacility = new ArrayList<Komentar>();
		for(Komentar k : comments.values()) {
			if(k.getSportskiObjekatId() == Integer.parseInt(text)){
				commentsOnFacility.add(k);
			}
		}
		return commentsOnFacility;
	}
}
