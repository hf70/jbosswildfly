package at.hf.stopwatch;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

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
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;
import at.hf.stopwatch.service.ParticipantService;

@Controller
public class StartListController implements Serializable {

	private static Integer STARTBLOCK_1 = 1;
	private static String DATE_FORMAT_PATTERN = "dd.MM.yyyy";

	@Inject
	ParticipantService participantService;

	private Image logo;

	private Competition competition;
	private Integer startBlock;
	private List<Participant> startList;

	@PostConstruct
	public void init() {
		createLogo();
		startBlock = STARTBLOCK_1;
	}

	public void updateStartList() {
		startList = participantService.findStartersForStartBlock(competition, startBlock);
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

		content.add(new Chunk(logo, PageSize.A4.getWidth() - PageSize.A4.getBorderWidthRight() - (logo.getWidth()*2), 0));
	
		return content;

	}

	private String formatCompetitionDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		return sdf.format(competition.getDate());
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public List<Participant> getStartList() {
		return startList;
	}

	public void setStartList(List<Participant> startList) {
		this.startList = startList;
	}

	public Integer getStartBlock() {
		return startBlock;
	}

	public void setStartBlock(Integer startBlock) {
		this.startBlock = startBlock;
	}

}
