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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Korisnik;
import beans.SportskiObjekat;
import beans.Uloga;

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
	
	public void update(Korisnik user) throws JsonGenerationException, JsonMappingException, IOException {
		users.put(user.getUsername(), user);
		saveChanges();
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
	
	public Collection<Korisnik> search(String text){
		List<Korisnik> newUsers = new ArrayList<Korisnik>();
		for(Korisnik k : users.values()) {
			if(k.getUsername().toLowerCase().contains(text.toLowerCase()) 
					|| k.getIme().toLowerCase().contains(text.toLowerCase()) 
					|| k.getPrezime().toLowerCase().contains(text.toLowerCase())) {
				newUsers.add(k);
			}
		}
		return newUsers;
	}
	
	public Collection<Korisnik> filterRole(String text){
		List<Korisnik> newUsers = new ArrayList<Korisnik>();
		for(Korisnik k : users.values()) {
			if(k.getUloga().name().equals(text)) newUsers.add(k);
		}
		return newUsers;
	}
	
	public Collection<Korisnik> filterType(String text){
		List<Korisnik> newUsers = new ArrayList<Korisnik>();
		for(Korisnik k : users.values()) {
			if(k.getTipKupca().getIme().equals(text)) newUsers.add(k);
		}
		return newUsers;
	}
	
	public Collection<Korisnik> getVisitorsInFacility(String id) {
		List<Korisnik> visitors = new ArrayList<Korisnik>();
		List<SportskiObjekat> facilities = new ArrayList<SportskiObjekat>();
		for(Korisnik k : users.values()) {
			facilities = k.getPoseceniObjekti();
			for(SportskiObjekat s : facilities) {
				if(s.getId() == Integer.parseInt(id)) {
					visitors.add(k);
				}
			}
		}
		return visitors;
	}	
	
	public Collection<Korisnik> getFreeManagers(){
		List<Korisnik> managers = new ArrayList<Korisnik>();
		for (Korisnik k : users.values()) {
			if (k.getUloga() == Uloga.MENADZER && k.getSportskiObjekatId() == 0)
				managers.add(k);
		}
		return managers;
	}
	
}
