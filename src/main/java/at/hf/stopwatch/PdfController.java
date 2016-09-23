package at.hf.stopwatch;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;

@ManagedBean
public class PdfController {

	private static String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
	private static String TIME_FORMAT_PATTERN = "mm:ss,SSS";
	private ResultGroupItem itemToPrint;

	public PdfController() {
	}

	public StreamedContent createPDF() throws DocumentException, IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String formPath = externalContext.getRealPath("") + File.separator + "resources" + File.separator
				+ "LAUrkunde_Formular.pdf";
		PdfReader reader = new PdfReader(formPath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfStamper stamper = new PdfStamper(reader, baos);
		AcroFields form = stamper.getAcroFields();
		Participant participant = itemToPrint.getParticipant();
		form.setField("Name",participant.getAthlete().getFirstName().concat(" ").concat( participant.getAthlete().getLastName()));
		form.setField("Verein", participant.getAthlete().getClub());
		form.setField("Klasse", participant.getClassification().getLongName());
		form.setField("Bewerb", "10.000 m");
		form.setField("Zeit", formatRunTime(participant.getRuntime()));
		form.setField("Rang", String.valueOf(itemToPrint.getPosition()).concat("."));
		form.setField("Datum",formatCompetitionDate(participant.getCompetition()));
		stamper.setFormFlattening(true);
		stamper.close();

			baos.close();
		String fileName = "Urkunde_" + participant.getAthlete().getLastName()+ itemToPrint.getParticipant().getId() + ".pdf";

		InputStream stream = new ByteArrayInputStream(baos.toByteArray());
		return new DefaultStreamedContent(stream, "application/pdf", fileName);

	}

	private String formatCompetitionDate(Competition competition) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		return sdf.format(competition.getDate());
	}
	private String formatRunTime(Long runtime){
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_PATTERN );
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(runtime);
	}

	public void setItemToPrint(ResultGroupItem itemToPrint) {
		this.itemToPrint = itemToPrint;
	}

}
