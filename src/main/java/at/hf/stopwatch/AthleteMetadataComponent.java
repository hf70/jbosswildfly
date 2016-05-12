package at.hf.stopwatch;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.mockito.Mockito;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.service.GenderService;
import at.hf.stopwatch.service.YearOfBirthService;

@Controller
public class AthleteMetadataComponent implements Serializable {

	@Inject
	GenderService genderService;
	@Inject
	YearOfBirthService yearOfBirthService;

	public Map<String, String> getGenderList() {
		return genderService.getGenderList();
	}

	public TreeMap<String, Integer> getYearOfBirthList() {
	 return yearOfBirthService
				.getYearOfBirthList();

	}

}
