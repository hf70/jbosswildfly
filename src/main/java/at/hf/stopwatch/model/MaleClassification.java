package at.hf.stopwatch.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="m")
public class MaleClassification extends Classification {

}
