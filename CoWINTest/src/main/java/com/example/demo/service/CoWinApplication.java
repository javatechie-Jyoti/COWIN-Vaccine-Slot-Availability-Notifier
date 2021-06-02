package com.example.demo.service;

import com.example.demo.model.SessionDTO;
import com.example.demo.model.SessionsDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootApplication
public class CoWinApplication {
	public static int count = 0;
	public static int countAPICall = 0;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(CoWinApplication.class, args);
		System.out.println("Server Started");

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				fetchAvailableAppointmentByDistrict();
			}
		};

		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(runnable, 0,30, TimeUnit.SECONDS);

	}


	public static void fetchAvailableAppointmentByDistrict() {
		//For South Delhi: String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id=149&date=18-05-2021";

		//For East Delhi: String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id=145&date=18-05-2021";

		//For "district_id":141,"district_name":"Central Delhi"
		//{"district_id":147,"district_name":"North East Delhi"}
		//"district_id":145,"district_name":"East Delhi"
		// "district_id":149,"district_name":"South Delhi"
		// "district_id":650,"district_name":"Gautam budh Nagar"}
		//String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id=149&date=09-06-2021";
		String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id=149&date=02-06-2021";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("accept", "application/json");
		httpHeaders.set("Accept-Language", "hi_IN");
		httpHeaders.set("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36");

		HttpEntity<SessionsDTO> requestEntity = new HttpEntity<SessionsDTO>(httpHeaders);

		ResponseEntity<SessionsDTO> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, SessionsDTO.class);
		countAPICall++;
		System.out.println("Calling API "+ countAPICall + " times");
		SessionsDTO tempRes = response.getBody();
		SessionsDTO res = new SessionsDTO();
		SessionsDTO resFirstDose = new SessionsDTO();

		res.setData(tempRes.getData().stream().filter(data -> Integer.valueOf(data.getMin_age_limit()) == 18 && Integer.valueOf(data.getAvailable_capacity_dose2()) > 10 && "COVAXIN".equals(data.getVaccine())).collect(Collectors.toList()));

		resFirstDose.setData(tempRes.getData().stream().filter(data -> Integer.valueOf(data.getMin_age_limit()) == 18 && Integer.valueOf(data.getAvailable_capacity_dose1()) > 10 ).collect(Collectors.toList()));

		if (res.getData() != null && res.getData().size() != 0) {
			String mailText = new String();
			for (SessionDTO centerDetails : res.getData()) {
				mailText = "centerName: " + centerDetails.getName() + " available_slots dose 2: " + centerDetails.getAvailable_capacity_dose2() + " for min_age:" + centerDetails.getMin_age_limit() + "/n";
			}
			//if(count ==0) {
				System.out.println("count : "+count);
				EmailService emailService = new EmailService();
				emailService.sendEmailUsingGmailServer(mailText);
				count++;
			//}
		}

	}
}