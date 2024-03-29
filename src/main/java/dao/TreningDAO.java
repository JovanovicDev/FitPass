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

import beans.Korisnik;
import beans.Trening;

public class TreningDAO {
	private Map<String, Trening> trainings = new HashMap<>();
	String path;
	
	public TreningDAO() {}
	
	public TreningDAO(String contextPath){
		this.path = contextPath;
		try {
			loadTrainings(contextPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadTrainings(String contextPath) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		
		List<Trening> trainingData = mapper.readValue(new File(contextPath), new TypeReference<List<Trening>>(){});
		for(Trening t : trainingData) {
			trainings.put(Integer.toString(t.getId()), t);
		}	
	}
	
	public String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
	
	public void addTraining(Trening training) {
		trainings.put(Integer.toString(training.getId()), training);
	}
	
	public Trening getById(int id) {
		return trainings.get(Integer.toString(id));
	}
	
	public Collection<Trening> findAll() {
		return trainings.values();
	}
	
	public void update(Trening training) throws JsonGenerationException, JsonMappingException, IOException {
		trainings.put(Integer.toString(training.getId()), training);
		saveChanges();
	}
	
	public void saveChanges() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		
		List<Trening> trainingList  = new ArrayList<>();
		for(Map.Entry<String, Trening> entry : trainings.entrySet()) {
			trainingList.add(new Trening(entry.getValue()));
		}
		mapper.writeValue(new File(path), trainingList);
	}

	public Map<String, Korisnik> getTrainersInFacility(String text){
		Map<String, Korisnik> trainers = new HashMap<>();
		for(Trening t : trainings.values()) {
			if(t.getSportskiObjekatId() == Integer.parseInt(text)) {
				if(t.getTrener() != null) {
					trainers.put(t.getTrener().getUsername(), t.getTrener());
				}
			}
		}

		return trainers;
	}
	
	public Collection<Trening> getTrainingsInFacility(String text){
		List<Trening> trainingsInFacility = new ArrayList<Trening>();
		for(Trening t : trainings.values()) {
			if(t.getSportskiObjekatId() == Integer.parseInt(text)){
				trainingsInFacility.add(t);
			}
		}

		return trainingsInFacility;
	}
	
	public Trening getByName(String name) {
		for (Trening t : trainings.values()) {
			if (t.getNaziv().equals(name))
				return t;
		}
		return null;
	}
	
	public int generateId() {
		int max = 0;
		if(trainings.isEmpty()) max = 0;
		for(Trening t : trainings.values()) {
			if(t.getId() > max) max = t.getId();
		}
		return ++max;
	}
	
}
