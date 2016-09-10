package at.hf.stopwatch.converter;

import static org.junit.Assert.assertEquals;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.Test;
import org.mockito.Mock;

public class RuntimeConverterTest {

	
	private static final String TIME_WITHOUT_MILLISECONDS_PART = "PT00H00M00.S";
	private static final String TIME_2000_MILLISECONDS = "PT00H00M00.2S";

	@Mock
	FacesContext facesContext;	
	@Mock
	UIComponent uiComponent;
	
	RuntimeConverter runtimeConverter = new RuntimeConverter();
	
	
	@Test
	public void milliSecondsPartIs0(){
		Long expected = new Long(0);
		Object milliseconds = runtimeConverter.getAsObject(facesContext, uiComponent, TIME_WITHOUT_MILLISECONDS_PART);
		assertEquals(expected, milliseconds);
	}
	
	@Test
	public void milliSecondsPartIsFilledUpToThreeDigits(){
		Long expected =new Long(200);
		Object milliseconds = runtimeConverter.getAsObject(facesContext, uiComponent, TIME_2000_MILLISECONDS);
		assertEquals(expected, milliseconds);
	}
	
	
	
	
}
