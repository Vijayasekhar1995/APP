package com.sbms.ControllersTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbms.Entitys.Address;
import com.sbms.Entitys.Booking_info;
import com.sbms.Entitys.FinalTicketResponse;
import com.sbms.Entitys.InitialTicketResponse;
import com.sbms.Entitys.Passenger;
import com.sbms.Entitys.TicketRequest;
import com.sbms.FeignClients.FeignClientToTrainApplication;
import com.sbms.Repositorys.FinalTicketResponseRepository;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "com.sbms")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = { FinalTicketResponseControllerTest.class })
@DisplayName("FinalTicketResponseControllerTestWithRealDataBase")

public class FinalTicketResponseControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	FeignClientToTrainApplication feignClientToTrainApplication;
	
	@MockBean
	FinalTicketResponseRepository finalTicketResponseRepository;
	
//	@MockBean
//	FinalTicketResponseServicessImpl finalTicketResponseServicessImpl;

	
	@Test
	@Order(1)
	@DisplayName("TestbookTicketWithisCreated")
	public void TestbookTicketWithisCreated() throws Exception {
//		System.out.println("***  TestbookTicketWithisCreated  ***");
//		Address address = new Address("Nellore",500023);
//		
//		Passenger passenger = new Passenger("Vijayasekhar", "MALE", 26,address);
//		
//	
//		TicketRequest ticketRequest = new TicketRequest(12709, "kavali", "vijayawada", "1AC", "08-12-2022",passenger);
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		String writeValueAsString = objectMapper.writeValueAsString(ticketRequest);
//		
//		
//		Booking_info booking_info = new Booking_info(12709, "KAVALI", "VIJAYAWADA", "1AC", "08-12-2022");
//		InitialTicketResponse initialTicketResponse = new InitialTicketResponse();
//		
//		initialTicketResponse.setTrain_no(12709);
//		initialTicketResponse.setTrain_name("SIMHAPURI EXPRESS");
//		initialTicketResponse.setFrom_station("KAVALI");
//		initialTicketResponse.setFrom_date("08-11-2022");
//		initialTicketResponse.setArri_at_from_station("19:48:00");
//		initialTicketResponse.setDept_at_from_station("19:50:00");
//		initialTicketResponse.setBoarding_station("KAVALI");
//		initialTicketResponse.setBoarding_date("08-11-2022");
//		initialTicketResponse.setArri_at_boarding_station("19:48:00");
//		initialTicketResponse.setDept_at_boarding_station("19:50:00");
//		initialTicketResponse.setTo_station("VIJAYAWADA");
//		initialTicketResponse.setArri_at_to_station("23:00:00");
//		initialTicketResponse.setDept_at_to_station("23:10:00");
//		initialTicketResponse.setTo_date("23-11-2022");
//		initialTicketResponse.setTotal_journey_hours("3:10:00");
//		initialTicketResponse.setTotal_distance("204 KM");
//		initialTicketResponse.setCost(697.00);
//		initialTicketResponse.setCoach("1AC");
//		
//		System.out.println(" initialTicketResponse :: "+initialTicketResponse);
//		
//		when(feignClientToTrainApplication.bookTicket(any())).thenReturn(initialTicketResponse);
//		
//		FinalTicketResponse finalTicketResponse = new FinalTicketResponse();
//		
//		finalTicketResponse.setTrain_no(initialTicketResponse.getTrain_no());
//		finalTicketResponse.setTrain_name(initialTicketResponse.getTrain_name());
//		finalTicketResponse.setFrom_station(initialTicketResponse.getFrom_station());
//		finalTicketResponse.setFrom_date(initialTicketResponse.getFrom_date());
//		finalTicketResponse.setArri_at_from_station(initialTicketResponse.getArri_at_from_station());
//		finalTicketResponse.setDept_at_from_station(initialTicketResponse.getDept_at_from_station());
//		finalTicketResponse.setBoarding_station(initialTicketResponse.getBoarding_station());
//		finalTicketResponse.setBoarding_date(initialTicketResponse.getBoarding_date());
//		finalTicketResponse.setArri_at_boarding_station(initialTicketResponse.getArri_at_boarding_station());
//		finalTicketResponse.setDept_at_boarding_station(initialTicketResponse.getDept_at_boarding_station());
//		finalTicketResponse.setTo_station(initialTicketResponse.getTo_station());
//		finalTicketResponse.setArri_at_to_station(initialTicketResponse.getArri_at_to_station());
//		finalTicketResponse.setDept_at_to_station(initialTicketResponse.getDept_at_to_station());
//		finalTicketResponse.setTo_date(initialTicketResponse.getTo_date());
//		finalTicketResponse.setTotal_J_H_from_from_station(initialTicketResponse.getTotal_journey_hours());
//		finalTicketResponse.setTotal_J_H_from_boarding_station(initialTicketResponse.getTotal_journey_hours());
//		finalTicketResponse.setTotal_dist_from_from_station(initialTicketResponse.getTotal_distance());
//		finalTicketResponse.setTotal_dist_from_boarding_station(initialTicketResponse.getTotal_distance());
//		finalTicketResponse.setCost(initialTicketResponse.getCost());
//		finalTicketResponse.setCoach(initialTicketResponse.getCoach());
//		finalTicketResponse.setPnr(11111111);
//		
//		finalTicketResponse.setPassenger(ticketRequest.getPassenger());
//		
//		System.out.println(" finalticketResponse :: "+finalTicketResponse);
//		
//		when(finalTicketResponseRepository.save(any())).thenReturn(finalTicketResponse);
//		
////		when(finalTicketResponseServicessImpl.bookTicket(ticketRequest)).thenReturn(finalTicketResponse);
//		
//				
//		mockMvc.perform(post("/bookTicket")
//			   .contentType(MediaType.APPLICATION_JSON)
//			   .content(writeValueAsString))
//		       .andExpect(status().isCreated())
//		       .andDo(print());			
//	}
//
//	@Test
//	@Order(11)
//	@DisplayName("TestgetTicketWithisFound")
//	public void getTicketTestWithisFound() throws Exception {
//		System.out.println("*** TestgetTicketWithisFound ***");
//		mockMvc.perform(get("/getTicket/10001178"))
//			   .andExpect(status()
//			   .isFound())
//			   .andDo(print());
//	}
//	
//	@Test
//	@Order(12)
//	@DisplayName("TestgetTicketWithBadRequest")
//	public void getTicketTestisBadRequest() throws Exception {
//		System.out.println("  TestgetTicketWithBadRequest  ");
//		mockMvc.perform(get("/getTicket/1000117"))
//			   .andExpect(status().isBadRequest())
//			   .andDo(print());
	}
	

}
