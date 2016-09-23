package at.hf.stopwatch;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.model.Classification;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;
import at.hf.stopwatch.service.ClassificationService;
import at.hf.stopwatch.service.CompetitionService;
import at.hf.stopwatch.service.ParticipantService;

@Controller
public class ResultController implements Serializable {

	private static String DATE_FORMAT_PATTERN = "dd.MM.yyyy";

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
	private List<Classification> selectedClassifications;
	
	private Image logo;

	@PostConstruct
	public void setup() {
		allResults = new ArrayList<Participant>();
		selectedClassifications = new ArrayList<Classification>();
		createLogo();
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

	public List<Classification> completeClassification(String query) {
		List<Classification> filteredClassifications = new ArrayList<Classification>();
		for (Classification classification : getCompetition().getClassifications()) {
			if (classification.getLongName().startsWith(query) || classification.getShortName().startsWith(query)) {
				filteredClassifications.add(classification);
			}
		}
		return filteredClassifications;
	}

	public void itemSelected(SelectEvent event) {
		populateGroupedResult();
	}

	public void itemUnSelected(UnselectEvent event) {
		populateGroupedResult();
	}

	public void populateGroupedResult() {
		resultGroups = new ArrayList<ResultGroup>();
		for (Classification classification : selectedClassifications) {
			List<Participant> results = participantService.findResultsForClassification(competition, classification);
			if (!results.isEmpty()) {

				resultGroups.add(createResultGroup(classification, results));
			}

		}
	}

	public void preProcessPDF(Object document) throws DocumentException {
		Document pdf = (Document) document;
		Font font = new Font(Font.TIMES_ROMAN, 8);

		String footerText = "ASV Salzburg LA, ".concat(formatCompetitionDate());
		Paragraph date = new Paragraph(footerText, font);
		date.setAlignment(Element.ALIGN_RIGHT);
		HeaderFooter footer = new HeaderFooter(new Phrase("Seite ", font), date);
		footer.setAlignment(Element.ALIGN_RIGHT);
		footer.setBorder(Rectangle.NO_BORDER);
		footer.setBorderWidthTop(new Float(0.5));
		pdf.setHeader(createHeader());
		pdf.setFooter(footer);
	}

	private HeaderFooter createHeader() {
		HeaderFooter header = new HeaderFooter(createHeaderContent(), false);
		header.setBorder(Rectangle.NO_BORDER);

		return header;
	}

	private Paragraph createHeaderContent() {
		Font font = new Font(Font.TIMES_ROMAN, 12);
		Paragraph content = new Paragraph();

		Paragraph subject = new Paragraph(competition.getSubject(), font);
		content.add(subject);

		content.add(new Chunk(logo, PageSize.A4.getHeight() - PageSize.A4.getBorderWidthRight() - (logo.getWidth() * 2),
				0));

		return content;

	}

	private String formatCompetitionDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		return sdf.format(competition.getDate());
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

	private void createLogo() {
		try {

			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String logoPath = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "images"
					+ File.separator + "ASV_Logo.png";
			logo = Image.getInstance(logoPath);
			logo.setAlignment(Image.RIGHT | Image.TEXTWRAP);

		} catch (BadElementException | IOException e) {
			e.printStackTrace();
		}
	}


	
	public List<Participant> getAllResults() {
		return allResults;
	}

	public List<ResultGroup> getResultGroups() {
		return resultGroups;
	}

	public List<Classification> getSelectedClassifications() {
		return selectedClassifications;
	}

	public void setSelectedClassifications(List<Classification> selectedClassifications) {
		this.selectedClassifications = selectedClassifications;
	}

	

}
