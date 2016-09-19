package at.hf.stopwatch;

import java.io.Serializable;

import at.hf.stopwatch.model.Participant;

public class ResultGroupItem implements Serializable {

	private Participant participant;
	private int position;

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
