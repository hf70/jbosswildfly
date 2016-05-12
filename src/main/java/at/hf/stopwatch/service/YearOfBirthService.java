package at.hf.stopwatch.service;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import at.hf.stopwatch.cdi.Service;

@Service
public class YearOfBirthService implements Serializable {

	private TreeMap<String, Integer> yearOfBirthList;

	@Inject
	Clock clock;

	@PostConstruct
	public void init() {
		initYearOfBirthList();

	}

	private void initYearOfBirthList() {
		yearOfBirthList = new TreeMap<String, Integer>(
				Collections.reverseOrder());
		LocalDateTime localDateTime = LocalDateTime.ofInstant(clock.instant(),
				ZoneId.systemDefault());
		localDateTime = localDateTime.minusYears(3);

		for (int year = localDateTime.getYear(); year >= 1900; year--) {
			yearOfBirthList.put(String.valueOf(year), year);
		}

	}

	public TreeMap<String, Integer> getYearOfBirthList() {
		return yearOfBirthList;
	}

}
