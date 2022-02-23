package hu.webuni.transport.luterdav.config;

import java.util.TreeMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "transport")
@Component
public class TransportConfigProperties {

	private RevenueDecrease decrease = new RevenueDecrease();
	
	public static class RevenueDecrease{
//		private Integer limit1;
//		private Integer limit2;
//		private Integer limit3;
//		private Integer percent1;
//		private Integer percent2;
//		private Integer percent3;
		
		private TreeMap<Long, Integer> limits;

		public TreeMap<Long, Integer> getLimits() {
			return limits;
		}

		public void setLimits(TreeMap<Long, Integer> limits) {
			this.limits = limits;
		}
	}

	public RevenueDecrease getDecrease() {
		return decrease;
	}

	public void setDecrease(RevenueDecrease decrease) {
		this.decrease = decrease;
	}
	
}
