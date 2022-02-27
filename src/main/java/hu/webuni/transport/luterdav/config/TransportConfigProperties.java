package hu.webuni.transport.luterdav.config;

import java.time.Duration;
import java.util.TreeMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "transport")
@Component
public class TransportConfigProperties {

	private RevenueDecrease decrease = new RevenueDecrease();
	private JwtData jwtData = new JwtData();
	
	
	public static class RevenueDecrease{
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
	

	public static class JwtData{
		private String issuer;
		private String secret;
		private String alg;
		private Duration duration;
		
		public String getIssuer() {
			return issuer;
		}
		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}
		public String getSecret() {
			return secret;
		}
		public void setSecret(String secret) {
			this.secret = secret;
		}
		public String getAlg() {
			return alg;
		}
		public void setAlg(String alg) {
			this.alg = alg;
		}
		public Duration getDuration() {
			return duration;
		}
		public void setDuration(Duration duration) {
			this.duration = duration;
		}
		
	}
	
	public JwtData getJwtData() {
		return jwtData;
	}

	public void setJwtData(JwtData jwtData) {
		this.jwtData = jwtData;
	}
	
	
	
}
