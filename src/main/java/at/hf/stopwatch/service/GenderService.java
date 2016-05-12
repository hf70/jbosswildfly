package at.hf.stopwatch.service;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import at.hf.stopwatch.cdi.Service;

@Service
public class GenderService implements Serializable {
	private Map<String, String> genderList;

	@PostConstruct
	public void init() {
		genderList = new TreeMap<String, String>();
		genderList.put("Mann", "m");
		genderList.put("Frau", "w");
		genderList.put("- Bitte auswählen -", "");
	}

	public Map<String, String> getGenderList() {
		return genderList;
	}

}
