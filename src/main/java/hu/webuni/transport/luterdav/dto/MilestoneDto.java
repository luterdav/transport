package hu.webuni.transport.luterdav.dto;

import java.time.LocalDateTime;

public class MilestoneDto {

	private long Id;
	private LocalDateTime plannedTime;

	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}
	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	
}
