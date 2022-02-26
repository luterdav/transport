package hu.webuni.transport.luterdav.dto;


public class MilestoneDelayDto {

	private long milestoneId;
	private long delay;
	
	public MilestoneDelayDto(long milestoneId, long delay) {
		super();
		this.milestoneId = milestoneId;
		this.delay = delay;
	}
	public long getMilestoneId() {
		return milestoneId;
	}
	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}
	public long getDelay() {
		return delay;
	}
	public void setDelay(long delay) {
		this.delay = delay;
	}
	
}
