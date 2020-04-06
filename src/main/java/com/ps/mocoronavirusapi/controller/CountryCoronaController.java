package com.ps.mocoronavirusapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ps.mocoronavirusapi.service.CountryCoronaService;

@Controller
public class CountryCoronaController {
	
	@Autowired
	private CountryCoronaService countryCoronaService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllPandemic (@RequestParam(required = false) String country) throws Exception {
		if (country == null || country.isEmpty()) 
			return ResponseEntity.ok(countryCoronaService.getAllPandemic());
		else 
			return ResponseEntity.ok(countryCoronaService.getCountryByFilter(country));
	}
}
