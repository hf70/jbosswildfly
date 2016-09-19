package at.hf.stopwatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import at.hf.stopwatch.model.Classification;

public class ResultGroup implements Serializable {

	private Classification classification;
	private List<ResultGroupItem> resultGroupItems = new ArrayList<ResultGroupItem>();

	
	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public List<ResultGroupItem> getResultGroupItems() {
		return resultGroupItems;
	}

	public void setResultGroupItems(List<ResultGroupItem> resultGroupItems) {
		this.resultGroupItems = resultGroupItems;
	}


}
