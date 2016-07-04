package at.hf.stopwatch.model;

import java.io.Serializable;
import java.util.List;

public class ClassificationGroup implements Serializable {
	private String name;
	private List<Classification> classifications;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Classification> getClassifications() {
		return classifications;
	}
	public void setClassifications(List<Classification> classifications) {
		this.classifications = classifications;
	}
	
}
