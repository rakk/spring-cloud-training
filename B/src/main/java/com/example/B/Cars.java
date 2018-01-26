package com.example.B;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("/cars")
@RestController
public class Cars {

	@RequestMapping("/all")
	public Map<String, Boolean> getcars() {
		Map<String, Boolean> result = new HashMap<>();
		result.put("Tesla", true);
		result.put("Mazda", false);
		result.put("BMW", false);
		result.put("Mercedes", false);
		return result;
	}
}
