package at.hf.stopwatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import at.hf.stopwatch.service.CompetitionService;

@Controller
public class ClassificationController implements Serializable {

	@Inject
	FacesContext facesContext;
	@Inject
	ClassificationService classificationService;
	@Inject
	CompetitionService competitionService;

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

	public void addNewFemaleClassification() {
		newClassification = new FemaleClassification();
		newClassification.setCompetition(competition);
	}

	public void addNewMaleClassification() {
		newClassification = new MaleClassification();
		newClassification.setCompetition(competition);
	}

	public Classification getNewClassification() {
		return newClassification;
	}

	public String getNewClassificationType() {
		if (newClassification instanceof FemaleClassification) {
			return "weiblich";
		}
		if (newClassification instanceof MaleClassification) {
			return "männlich";
		}

		return StringUtils.EMPTY;

	}

	@Transactional
	public void onRowEdit(RowEditEvent event) {
		Classification classification = (Classification) event.getObject();
		classificationService.save(classification);

	}

	public void onRowCancel(RowEditEvent event) {

	}

	@Transactional
	public void saveNewClassification() {
		classificationService.save(newClassification);
	}

	@Transactional
	public void removeClassification(Classification classification) {
		String name = classification.getLongName();
		competition.getClassifications().remove(classification);
		classificationService.delete(classification);
		competitionService.save(competition);
		facesContext.addMessage(null, new FacesMessage("Die Klasse " + name + " wurde gelöscht"));
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

}
