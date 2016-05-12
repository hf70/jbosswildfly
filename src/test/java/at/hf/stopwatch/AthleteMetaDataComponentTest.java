package at.hf.stopwatch;

import static org.junit.Assert.assertTrue;

import java.util.TreeMap;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.hf.stopwatch.service.GenderService;
import at.hf.stopwatch.service.YearOfBirthService;

@RunWith(MockitoJUnitRunner.class)
public class AthleteMetaDataComponentTest {

	@Mock
	GenderService genderService;
	@Mock
	YearOfBirthService yearOfBirthService;

	@InjectMocks
	AthleteMetadataComponent athleteMetadataComponent;

	@Before
	public void setup() {
		Mockito.when(genderService.getGenderList()).thenReturn(
				new TreeMap<String, String>());
		Mockito.when(yearOfBirthService.getYearOfBirthList()).thenReturn(
				new TreeMap<String, Integer>());
	}

	@Test
	public void yearOfBirthServiceIsCalled() {
		athleteMetadataComponent.getYearOfBirthList();
		Mockito.verify(yearOfBirthService).getYearOfBirthList();

	}

}
