package dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Korisnik;

public class KorisnikDAO {
	private Map<String, Korisnik> users = new HashMap<>();
	String path;
	
	public KorisnikDAO() {}
	
	public KorisnikDAO(String contextPath){
		this.path = contextPath;
		try {
			loadUsers(contextPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadUsers(String contextPath) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		
		List<Korisnik> userData = mapper.readValue(new File(contextPath), new TypeReference<List<Korisnik>>(){});
		for(Korisnik k : userData) {
			users.put(k.getUsername(), k);
		}	
	}
	
	
	public String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
	
	public Korisnik find(String username, String password) {
		if (!users.containsKey(username)) {
			return null;
		}
		Korisnik user = users.get(username);
		if (!user.getSifra().equals(password)) {
			return null;
		}
		return user;
	}
	
	public void addUser(Korisnik user) {
		this.users.put(user.getUsername(),user);
	}
	
	public Korisnik getByUsername(String username) {
		return users.get(username);
	}
	
	public Collection<Korisnik> findAll() {
		return users.values();
	}
	
	public void saveChanges() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		
		List<Korisnik> userList  = new ArrayList<>();
		for(Map.Entry<String, Korisnik> entry : users.entrySet()) {
			userList.add(new Korisnik(entry.getValue()));
		}
		mapper.writeValue(new File(path), userList);
	}
}
