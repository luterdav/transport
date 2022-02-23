package hu.webuni.transport.luterdav.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TransportPlan {
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
 
  private int revenue;
  
  @OneToMany(mappedBy = "transportPlan")
  private List<Section> section;
  
  public TransportPlan() {}
  
  public TransportPlan(Long id, int revenue, List<Section> section) {
    this.id = id;
    this.revenue = revenue;
    this.section = section;
  }
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public int getRevenue() {
    return this.revenue;
  }
  
  public void setRevenue(int revenue) {
    this.revenue = revenue;
  }
  
  public List<Section> getSection() {
    return this.section;
  }
  
  public void setSection(List<Section> section) {
    this.section = section;
  }
}

