package at.hf.stopwatch.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import at.hf.stopwatch.model.Classification;
import at.hf.stopwatch.model.Participant;
import at.hf.stopwatch.service.ClassificationService;
import at.hf.stopwatch.service.ParticipantService;

@Named
public class ClassificationConverter implements Converter {
	@Inject
	ClassificationService classificationService;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (StringUtils.isEmpty(value))
			return null;

		try {
			return classificationService.findById(Integer.parseInt(value));
		} catch (NumberFormatException e) {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		if (object != null) {
			return String.valueOf(((Classification) object).getId());
		} else {
			return null;
		}
	}

}
