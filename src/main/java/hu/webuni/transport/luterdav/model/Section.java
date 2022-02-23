package hu.webuni.transport.luterdav.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Section {
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @OneToOne
  private Milestone fromMilestone;

  @OneToOne
  private Milestone toMilestone;
 
  private int number;
  
  @ManyToOne
  private TransportPlan transportPlan;
  
  public Section() {}
  
  public Section(Long id, Milestone fromMilestone, Milestone toMilestone, int number, TransportPlan transportPlan) {
    this.id = id;
    this.fromMilestone = fromMilestone;
    this.toMilestone = toMilestone;
    this.number = number;
    this.transportPlan = transportPlan;
  }
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public Milestone getFromMilestone() {
    return this.fromMilestone;
  }
  
  public void setFromMilestone(Milestone fromMilestone) {
    this.fromMilestone = fromMilestone;
  }
  
  public Milestone getToMilestone() {
    return this.toMilestone;
  }
  
  public void setToMilestone(Milestone toMilestone) {
    this.toMilestone = toMilestone;
  }
  
  public int getNumber() {
    return this.number;
  }
  
  public void setNumber(int number) {
    this.number = number;
  }
  
  public TransportPlan getTransportPlan() {
    return this.transportPlan;
  }
  
  public void setTransportPlan(TransportPlan transportPlan) {
    this.transportPlan = transportPlan;
  }
}

