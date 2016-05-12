package at.hf.stopwatch.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Clock;
import java.time.Instant;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class YearOfBirthServiceTest {

	@Mock
	Clock now;
	@InjectMocks
	YearOfBirthService yearOfBirthService;

	@Test
	public void yearOfBirthSelectItemsStartThreeYearsFromNowAndEndWithYear1900() {
		Mockito.when(now.instant()).thenReturn(
				Instant.parse("2005-11-01T00:00:00.00Z"));

		Integer expectedFirstYear = 2002;
		Integer expectedLastYear = 1900;

		yearOfBirthService.init();
		TreeMap<String, Integer> yearOfBirthList = yearOfBirthService
				.getYearOfBirthList();
		assertTrue(yearOfBirthList != null);
		assertFalse(yearOfBirthList.isEmpty());
		assertEquals(expectedFirstYear, yearOfBirthList.firstEntry().getValue());
		assertEquals(expectedLastYear, yearOfBirthList.lastEntry().getValue());

	}

}
