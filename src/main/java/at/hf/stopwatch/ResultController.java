package at.hf.stopwatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.model.Classification;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.FemaleClassification;
import at.hf.stopwatch.model.Participant;
import at.hf.stopwatch.service.ClassificationService;
import at.hf.stopwatch.service.CompetitionService;
import at.hf.stopwatch.service.ParticipantService;

@Controller
public class ResultController implements Serializable {

	@Inject
	ParticipantService participantService;
	@Inject
	FacesContext facesContext;
	@Inject
	CompetitionService competitionService;
	@Inject
	ClassificationService classificationService;

	private Competition competition;
	private List<Participant> allResults;
	private List<ResultGroup> resultGroups;

	@PostConstruct
	public void setup() {
		allResults = new ArrayList<Participant>();
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public void loadAllResults() {
		allResults = participantService.findAllResults(competition);
	}

	public void populateGroupedResult() {
		resultGroups = new ArrayList<ResultGroup>();
		for (Classification classification : getCompetition().getClassifications()) {
			List<Participant> results = participantService.findResultsForClassification(competition, classification);
			if (!results.isEmpty()) {

				resultGroups.add(createResultGroup(classification, results));
			}

		}
	}

	private ResultGroup createResultGroup(Classification classification, List<Participant> results) {
		ResultGroup resultGroup = new ResultGroup();
		resultGroup.setClassification(classification);
		int position = 0;
		for (Participant participant : results) {
			position++;
			ResultGroupItem item = new ResultGroupItem();
			item.setPosition(position);
			item.setParticipant(participant);
			resultGroup.getResultGroupItems().add(item);
		}
		return resultGroup;
	}

	public List<Participant> getAllResults() {
		return allResults;
	}

	public List<ResultGroup> getResultGroups() {
		return resultGroups;
	}

}
