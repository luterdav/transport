package hu.webuni.transport.luterdav.dto;

import java.util.List;

import hu.webuni.transport.luterdav.model.Section;

public class TransportPlanDto {
	
	private long id;
	private int revenue;
	private List<SectionDto> section;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getRevenue() {
		return revenue;
	}
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
	public List<SectionDto> getSection() {
		return section;
	}
	public void setSection(List<SectionDto> section) {
		this.section = section;
	}
	
	

}
