package dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.SportskiObjekat;
import beans.TipSportskogObjekta;

public class SportskiObjekatDAO {
	private Map<String, SportskiObjekat> sportskiObjekti = new HashMap<>();
	String path;
	
	public SportskiObjekatDAO() {}
	
	public SportskiObjekatDAO(String contextPath){
		this.path = contextPath;
		try {
			loadSportFacilities(contextPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadSportFacilities(String contextPath) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		
		List<SportskiObjekat> sportFacilityData = mapper.readValue(new File(contextPath), new TypeReference<List<SportskiObjekat>>(){});
		for(SportskiObjekat k : sportFacilityData) {
			sportskiObjekti.put(Integer.toString(k.getId()), k);
		}	
	}
	
	public String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
	
	public void addSportFacility(SportskiObjekat objekat) {
		this.sportskiObjekti.put(Integer.toString(objekat.getId()),objekat);
	}
	
	public SportskiObjekat getById(int id) {
		return sportskiObjekti.get(Integer.toString(id));
	}
	
	public Collection<SportskiObjekat> findAll() {	
		Map<String, SportskiObjekat> sortedMap = sortMap();
		List<SportskiObjekat> invertedList = invertResults(sortedMap);
		return invertedList;
	}
	
	public void update(SportskiObjekat objekat) throws JsonGenerationException, JsonMappingException, IOException {
		sportskiObjekti.put(Integer.toString(objekat.getId()),objekat);
		saveChanges();
	}
	
	public void saveChanges() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		
		List<SportskiObjekat> sportFacilityList  = new ArrayList<>();
		for(Map.Entry<String, SportskiObjekat> entry : sportskiObjekti.entrySet()) {
			sportFacilityList.add(new SportskiObjekat(entry.getValue()));
		}
		mapper.writeValue(new File(path), sportFacilityList);
	}
	
	private Map<String, SportskiObjekat> sortMap(){
		Map<String, SportskiObjekat> resultSet = sportskiObjekti.entrySet()
				.stream()
				.sorted(Comparator.comparing(e -> e.getValue().getStatus()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (left, right) -> left, LinkedHashMap::new));
		return resultSet;
	}
	
	private List<SportskiObjekat> invertResults(Map<String, SportskiObjekat> resultSet) {
		List<SportskiObjekat> resultList = new ArrayList<SportskiObjekat>();
		for (SportskiObjekat o : resultSet.values()) {
		    resultList.add(o);
		}
		Collections.reverse(resultList);
		return resultList;
	}
	
	public SportskiObjekat getByName(String name) {
		for (SportskiObjekat o : sportskiObjekti.values()) {
			if (o.getNaziv().equals(name))
				return o;
		}
		return null;
	}
	
	public int generateId() {
		int max = 0;
		if(sportskiObjekti.isEmpty()) max = 0;
		for(SportskiObjekat o : sportskiObjekti.values()) {
			if(o.getId() > max) max = o.getId();
		}
		return ++max;
	}
	
	public SportskiObjekat getFacilityByManagerUsername(String username) {
		for(SportskiObjekat o : sportskiObjekti.values()) {
			if(o.getMenadzerUsername().equals(username)) {
				return o;
			}
		}
		return null;
	}
	
	public Collection<SportskiObjekat> filterType(String text){
		List<SportskiObjekat> objekti = new ArrayList<SportskiObjekat>();
		for(SportskiObjekat o : sportskiObjekti.values()) {
			if(o.getTip().toString().equals(text)) {
				objekti.add(o);
			}
		}
		return objekti;
	}
	
	public Collection<SportskiObjekat> filterWorking(){
		List<SportskiObjekat> objekti = new ArrayList<SportskiObjekat>();
		for(SportskiObjekat o : sportskiObjekti.values()) {
			if(o.getStatus().equals("Radi")) {
				objekti.add(o);
			}
		}
		return objekti;
	}
	
	public Collection<SportskiObjekat> search(TipSportskogObjekta type, String city, String name, String averageGrade){
		 Map<Integer, SportskiObjekat> filteredFacilities = new HashMap<>();
		
		 if(city == null) city = "";
		 if(name == null) name = "";
		 if(type == null) {
			 if(averageGrade == null) {
				 for(SportskiObjekat o : sportskiObjekti.values()) {
					 if(o.getLokacija().getAdresa().toLowerCase().contains(city.toLowerCase()) 
							 && o.getNaziv().toLowerCase().contains(name.toLowerCase()))
						 filteredFacilities.put(o.getId(), o);					
				 }
			 } else {
				 for(SportskiObjekat o : sportskiObjekti.values()) {
					 if(o.getLokacija().getAdresa().toLowerCase().contains(city.toLowerCase()) 
							 && o.getProsecnaOcena() == Double.parseDouble(averageGrade)
							 && o.getNaziv().toLowerCase().contains(name.toLowerCase()))
						 filteredFacilities.put(o.getId(), o);						
				 }
			 }			 
		 } else {
			 if(averageGrade == null) {
				 for(SportskiObjekat o : sportskiObjekti.values()) {
					 if(o.getLokacija().getAdresa().toLowerCase().contains(city.toLowerCase())
							 && o.getNaziv().toLowerCase().contains(name.toLowerCase())
							 && o.getTip()==type)
						 filteredFacilities.put(o.getId(), o);						
				 }
			 } else {
				 for(SportskiObjekat o : sportskiObjekti.values()) {
					 if(o.getLokacija().getAdresa().toLowerCase().contains(city.toLowerCase()) 
							 && o.getProsecnaOcena() == Double.parseDouble(averageGrade)
							 && o.getNaziv().toLowerCase().contains(name.toLowerCase())
							 && o.getTip()==type)
						 filteredFacilities.put(o.getId(), o);						
				 }
			 }		
		 }	 
		 return filteredFacilities.values();
	}
}
