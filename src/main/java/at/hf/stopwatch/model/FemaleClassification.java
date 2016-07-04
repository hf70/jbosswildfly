package at.hf.stopwatch.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="w")
public class FemaleClassification extends Classification {

}
