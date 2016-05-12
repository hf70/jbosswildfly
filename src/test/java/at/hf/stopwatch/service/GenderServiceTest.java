package at.hf.stopwatch.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GenderServiceTest {

	@InjectMocks
	GenderService genderService;

	@Test
	public void genderListContainsThreeElements() {
		// genderService = new GenderService();
		int expectedListSize = 3;
		genderService.init();

		assertTrue(genderService.getGenderList() != null);
		assertEquals(expectedListSize, genderService.getGenderList().size());
	}

}
