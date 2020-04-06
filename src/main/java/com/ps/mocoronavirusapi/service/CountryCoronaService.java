package com.ps.mocoronavirusapi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ps.mocoronavirusapi.model.CountryCorona;

import static com.ps.mocoronavirusapi.util.IntegerValidation.isInt;

@Service
public class CountryCoronaService {

	public Map<String, Object> getAllPandemic() throws Exception {
		Map<String, Object> dataJson = new LinkedHashMap<String, Object>();
		dataJson.put("source",
				"Wikipedia - https://en.wikipedia.org/wiki/2019%E2%80%9320_coronavirus_pandemic_by_country_and_territory");
		dataJson.put("updateDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		dataJson.put("information", getAllCountries());
		return dataJson;
	}
	
	public Map<String, Object> getCountryByFilter (String country) throws Exception {
		List<CountryCorona> countryFound = getAllCountries().stream().filter(element -> element.getCountry().equalsIgnoreCase(country)).collect(Collectors.toList());
		
		Map<String, Object> dataJson = new LinkedHashMap<String, Object>();
		dataJson.put("source",
				"Wikipedia - https://en.wikipedia.org/wiki/2019%E2%80%9320_coronavirus_pandemic_by_country_and_territory");
		dataJson.put("updateDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		dataJson.put("information", countryFound);
		return dataJson;
	}
	
	private List<CountryCorona> getAllCountries () throws Exception {
		Document doc = Jsoup
				.connect("https://en.wikipedia.org/wiki/2019%E2%80%9320_coronavirus_pandemic_by_country_and_territory")
				.get();
		Element pandemicTable = doc.getElementById("thetable");
		Elements countrys = pandemicTable.select("tr");

		List<CountryCorona> countriesCorona = new ArrayList<>();

		for (int i = 1; i < countrys.size(); i++) {
			Element countryInformation = countrys.get(i);
			CountryCorona corona = new CountryCorona();
			if (countryInformation.select("th a").size() > 0) {
				Element countryName = countryInformation.select("th a").get(0);
				if (!countryName.attr("title").isEmpty() && countryName.attr("title") != null) {
					corona.setCountry(countryName.html().replaceAll("\\<.*?\\>", "").replaceAll("amp;", ""));
				}
			} 
			
			
			if (countryInformation.select("th img").size() == 1) {
				corona.setImage(countryInformation.select("th img").get(0).attr("src"));
			}
			if (countryInformation.select("td").size() >= 3) {
				int[] valuesInformation = new int[3];
				
				for (int valueIndex = 0; valueIndex < 3; valueIndex++) {
					String information = countryInformation.select("td").get(valueIndex).html().replaceAll(",", "")
							.replaceAll("\\<.*?\\>", "");

					if (!isInt(information)) {
						information = "0";
					}
					valuesInformation[valueIndex] = Integer.parseInt(information);
				}

				corona.setCauses(valuesInformation[0]);
				corona.setDeaths(valuesInformation[1]);
				corona.setRecoveries(valuesInformation[2]);
			}

			if (corona.getCountry() != null && !corona.getCountry().isEmpty()) {
				countriesCorona.add(corona);
			}

		}
		
		return countriesCorona;
	}
}
