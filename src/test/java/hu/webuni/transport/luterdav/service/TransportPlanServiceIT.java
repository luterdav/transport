package hu.webuni.transport.luterdav.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import hu.webuni.transport.luterdav.config.TransportConfigProperties;
import hu.webuni.transport.luterdav.dto.LoginDto;
import hu.webuni.transport.luterdav.dto.MilestoneDelayDto;
import hu.webuni.transport.luterdav.dto.SectionDto;
import hu.webuni.transport.luterdav.dto.TransportPlanDto;
import hu.webuni.transport.luterdav.model.Milestone;
import hu.webuni.transport.luterdav.model.Section;
import hu.webuni.transport.luterdav.model.TransportPlan;
import hu.webuni.transport.luterdav.repositories.MilestoneRepository;
import hu.webuni.transport.luterdav.repositories.SectionRepository;
import hu.webuni.transport.luterdav.repositories.TransportPlanRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class TransportPlanServiceIT {

	private static final String BASE_URI = "/api/transportPlans";

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	TransportConfigProperties config;
	
	private String username = "Peter";
	private String password = "1234";
	private String jwt;

	@BeforeEach
	public void init() {
		createDBData();
		
		LoginDto loginDto = new LoginDto();
		loginDto.setUsername(username);
		loginDto.setPassword(password);
		jwt = webTestClient
		.post()
		.uri("/api/login")
		.bodyValue(loginDto)
		.exchange()
		.expectBody(String.class)
		.returnResult()
		.getResponseBody();
		
	}
	
	public void createDBData() {
		Milestone milestone1 = milestoneRepository.save(new Milestone(null, null, LocalDateTime.of(2022, 3, 10, 10, 10, 10)));
		Milestone milestone2 = milestoneRepository.save(new Milestone(null, null, LocalDateTime.of(2022, 3, 20, 2, 2, 2)));
		Milestone milestone3 = milestoneRepository.save(new Milestone(null, null, LocalDateTime.of(2022, 3, 21, 3, 3, 3)));
		Milestone milestone4 = milestoneRepository.save(new Milestone(null, null, LocalDateTime.of(2022, 3, 22, 4, 4, 4)));
		Milestone milestone5 = milestoneRepository.save(new Milestone(null, null, LocalDateTime.of(2022, 4, 20, 4, 4, 4)));

		TransportPlan transportPlan1 = transportPlanRepository.save(new TransportPlan(null, 10000, null));

		Section section1 = sectionRepository.save(new Section(null, milestone1, milestone2, 0, transportPlan1));
		Section section2 = sectionRepository.save(new Section(null, milestone3, milestone4, 1, transportPlan1));
		
	}

	@Test
	void TestThatCreateValidDelayForFromMilestone() throws Exception {
		List<TransportPlanDto> transportPlansBefore = getAllTransportPlans();
		TransportPlanDto transportPlan = transportPlansBefore.get(0);
		SectionDto sectionBefore = transportPlan.getSection().get(0);
		
		long transportPlanId = transportPlan.getId();
		long milestoneId = 1;
		long delay = 70;
		
		MilestoneDelayDto milestoneDelayDto = new MilestoneDelayDto(milestoneId, delay);
		
		LocalDateTime plannedTimeFromMilestoneBefore = sectionBefore.getFromMilestone().getPlannedTime();
		LocalDateTime plannedTimeToMilestoneBefore = sectionBefore.getToMilestone().getPlannedTime();
		
		TransportPlanDto transportPlanWithDelay = addDelay(transportPlanId, milestoneDelayDto)
		.expectStatus()
		.isOk()
		.expectBody(TransportPlanDto.class)
		.returnResult()
		.getResponseBody();
		
		
		SectionDto sectionAfter = transportPlanWithDelay.getSection().get(0);
		
		LocalDateTime plannedTimeFromMilestoneAfter = sectionAfter.getFromMilestone().getPlannedTime();
		LocalDateTime plannedTimeToMilestoneAfter = sectionAfter.getToMilestone().getPlannedTime();
		
		assertThat(plannedTimeFromMilestoneBefore).isEqualTo(plannedTimeFromMilestoneAfter.minusMinutes(delay));
		assertThat(plannedTimeToMilestoneBefore).isEqualTo(plannedTimeToMilestoneAfter.minusMinutes(delay));

	}
	
	@Test
	void TestThatCreateValidDelayForToMilestone() throws Exception {
		List<TransportPlanDto> transportPlansBefore = getAllTransportPlans();
		TransportPlanDto transportPlan = transportPlansBefore.get(0);
		
		long transportPlanId = transportPlan.getId();
		
		SectionDto sectionBeforeFirst = transportPlan.getSection().get(0);
		SectionDto sectionBeforeSecond = transportPlan.getSection().get(1);
		
		long milestoneId = 2;
		long delay = 70;
		
		MilestoneDelayDto milestoneDelayDto = new MilestoneDelayDto(milestoneId, delay);
		
		LocalDateTime plannedTimeToMilestoneBefore = sectionBeforeFirst.getToMilestone().getPlannedTime();
		LocalDateTime plannedTimeFromMilestoneBefore = sectionBeforeSecond.getFromMilestone().getPlannedTime();
		
		TransportPlanDto transportPlanWithDelay = addDelay(transportPlanId, milestoneDelayDto)
		.expectStatus()
		.isOk()
		.expectBody(TransportPlanDto.class)
		.returnResult()
		.getResponseBody();
		
		
		SectionDto sectionAfterFirst = transportPlanWithDelay.getSection().get(0);
		SectionDto sectionAfterSecond = transportPlanWithDelay.getSection().get(1);
		
		LocalDateTime plannedTimeToMilestoneAfter = sectionAfterFirst.getToMilestone().getPlannedTime();
		LocalDateTime plannedTimeFromMilestoneAfter = sectionAfterSecond.getFromMilestone().getPlannedTime();
		
		assertThat(sectionBeforeFirst.getNumber()).isEqualTo(sectionBeforeSecond.getNumber() - 1);
		assertThat(sectionAfterFirst.getNumber()).isEqualTo(sectionAfterSecond.getNumber() - 1);
		
		assertThat(plannedTimeToMilestoneBefore).isEqualTo(plannedTimeToMilestoneAfter.minusMinutes(delay));
		assertThat(plannedTimeFromMilestoneBefore).isEqualTo(plannedTimeFromMilestoneAfter.minusMinutes(delay));

	}
	
	@Test
	void testThatCreateInvalidDelayWithStatusNotFound() throws Exception{
        List<TransportPlanDto> transportPlans = getAllTransportPlans();
		
        TransportPlanDto lastTransportPlan = transportPlans.get(transportPlans.size() - 1);
		int sectionSize = lastTransportPlan.getSection().size();
		SectionDto sectionLast = lastTransportPlan.getSection().get(sectionSize - 1);
		
		long transportPlanIdValid = 1;
		long milestoneIdInvalid = sectionLast.getToMilestone().getId() + 2;
		
		MilestoneDelayDto milestoneDelayDto1 = new MilestoneDelayDto(milestoneIdInvalid, 70);
		
		addDelay(transportPlanIdValid, milestoneDelayDto1)
		.expectStatus()
		.isNotFound();
		
		long transportPlanIdInvalid = lastTransportPlan.getId() + 1;
		
		MilestoneDelayDto milestoneDelayDto2 = new MilestoneDelayDto(1, 70);
		
		addDelay(transportPlanIdInvalid, milestoneDelayDto2)
		.expectStatus()
		.isNotFound();
		
	}
	
	@Test
	void testThatCreateInvalidDelayWithStatusBadRequest() throws Exception{
		
		long transportPlanId = 1;
		long milestoneId = 5;
		long delay = 70;
		
		MilestoneDelayDto milestoneDelayDto = new MilestoneDelayDto(milestoneId, delay);
		
		addDelay(transportPlanId, milestoneDelayDto)
		.expectStatus()
		.isBadRequest();
		
	}
	
	@Test
	void testThatRevenueDecreaseValid() throws Exception{
		List<TransportPlanDto> transportPlans = getAllTransportPlans();
		TransportPlanDto transportPlan = transportPlans.get(0);
		
		int revenueBefore = transportPlan.getRevenue();
		long delay = 70;
		
		TreeMap<Long, Integer> limits = config.getDecrease().getLimits();
		Integer percent = limits.floorEntry(delay).getValue();
		int revenueDecrease = revenueBefore - (revenueBefore * (100 - percent) / 100);
		
		MilestoneDelayDto milestoneDelayDto = new MilestoneDelayDto(1, delay);
		
		TransportPlanDto transportPlanWithDelay = addDelay(1, milestoneDelayDto)
		.expectStatus()
		.isOk()
		.expectBody(TransportPlanDto.class)
		.returnResult()
		.getResponseBody();
		
		
		int revenueAfter = transportPlanWithDelay.getRevenue();
		
		assertThat(revenueAfter).isEqualTo(revenueBefore - revenueDecrease);
		
	}
	
	

	private ResponseSpec addDelay(long transportPlanId, MilestoneDelayDto milestoneDelayDto) {
		String path = BASE_URI + "/" + transportPlanId + "/delay";
		return webTestClient
				.post()
				.uri(path)
				.headers(headers -> headers.setBearerAuth(jwt))
				.bodyValue(milestoneDelayDto)
				.exchange();
	}


	private List<TransportPlanDto> getAllTransportPlans() {
		List<TransportPlanDto> responseList = webTestClient
				.get()
				.uri(BASE_URI)
				.headers(headers -> headers.setBearerAuth(jwt))
				.exchange()
				.expectStatus()
				.isOk()
				.expectBodyList(TransportPlanDto.class)
				.returnResult()
				.getResponseBody();
		Collections.sort(responseList, Comparator.comparing(TransportPlanDto::getId));
		return responseList;
	}

}
