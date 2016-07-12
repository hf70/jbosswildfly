package at.hf.stopwatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.RowEditEvent;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.model.Classification;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.FemaleClassification;
import at.hf.stopwatch.model.MaleClassification;
import at.hf.stopwatch.service.ClassificationService;

@Controller
public class ClassificationController implements Serializable {

	@Inject
	FacesContext facesContext;
	@Inject
	ClassificationService classificationService;

	private Competition competition;
	private Classification newClassification;

	public List<Classification> filterFemaleClassifications() {
		List<Classification> femaleClassification = new ArrayList<Classification>();
		for (Classification classification : getCompetition().getClassifications()) {
			if (classification instanceof FemaleClassification) {
				femaleClassification.add(classification);
			}
		}
		return femaleClassification;
	}

	public List<Classification> filterMaleClassifications() {
		List<Classification> maleClassification = new ArrayList<Classification>();
		for (Classification classification : getCompetition().getClassifications()) {
			if (classification instanceof MaleClassification) {
				maleClassification.add((Classification) classification);
			}
		}
		return maleClassification;
	}
	
	public void addNewFemaleClassification(){
		newClassification = new FemaleClassification();
		newClassification.setCompetition(competition);
	}
	public void addNewMaleClassification(){
		newClassification = new MaleClassification();
		newClassification.setCompetition(competition);
	}
	

	public Classification getNewClassification() {
		return newClassification;
	}
	
	public String  getNewClassificationType(){
		if (newClassification == null){
			return StringUtils.EMPTY;
		}
		return newClassification.getClass().getName();
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited", ((Classification) event.getObject()).getLongName());
		facesContext.addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Classification) event.getObject()).getLongName());
		facesContext.addMessage(null, msg);
	}

	@Transactional
	public void saveClassification() {
		classificationService.save(newClassification);
		FacesMessage msg = new FacesMessage("Save", "test");
		facesContext.addMessage(null, msg);

	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

}
