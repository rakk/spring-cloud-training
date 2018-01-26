package com.example.A;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestMapping("/electric")
@RestController
public class ElectricCars {

	private RestTemplate restTemplate = new RestTemplate();

	@RequestMapping("/cars")
	public List<String> getCards (){
		Map<String, Boolean> formB = restTemplate.getForObject("http://localhost:9050/cars/all", Map.class);
		return formB.entrySet().stream()
				.map(entrySet -> Boolean.TRUE.equals(entrySet.getValue()) ? entrySet.getKey() : null)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}
}
