package at.hf.stopwatch.converter;

import java.text.MessageFormat;
import java.time.Duration;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

@Named
public class RuntimeConverter implements Converter {
	String ISO_8601_DURATION_PATTERN="PT{0}H{1}M{2}S" ;
	String DURATION_TO_STRING_PATTERN="HH:mm:ss.SS";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isEmpty(value)){
			return null;
		}
		return Duration.parse(formatAsDuration(value)).toMillis();
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return DurationFormatUtils.formatDuration((Long) value, DURATION_TO_STRING_PATTERN, true);
	}
	
	private String formatAsDuration(String maskedInput){		
		return MessageFormat.format(ISO_8601_DURATION_PATTERN, (Object[]) maskedInput.split(":"));
	
	}

}
