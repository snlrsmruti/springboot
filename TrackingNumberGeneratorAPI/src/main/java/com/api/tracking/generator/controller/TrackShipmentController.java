package com.api.tracking.generator.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.tracking.generator.InvalidParameterException;
import com.api.tracking.generator.model.ResponseBean;

@RestController
@RequestMapping("GET/next-tracking-number")
@Validated
public class TrackShipmentController {

	@GetMapping("/{orderId}")
	public ResponseEntity getTrackingNumber(@RequestParam String origin_country_id,
			@RequestParam String destination_country_id,
			@RequestParam double weight, @RequestParam String created_at,
			@RequestParam String customer_id, @RequestParam String customer_name, @RequestParam String customer_slug)
			throws InvalidParameterException {

		if (!validateParameter(origin_country_id, destination_country_id, weight, created_at, customer_id,
				customer_name, customer_slug))
			throw new InvalidParameterException("Please provide valid input");

		String trackingNumber = UUID.randomUUID().toString();
		String createdAt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date());
		ResponseBean response = new ResponseBean();
		response.setTrackingNumber(trackingNumber);
		response.setCreatedAt(createdAt);

		HttpHeaders headers = new HttpHeaders();

		ResponseEntity<ResponseBean> entity = new ResponseEntity<>(response, headers, HttpStatus.CREATED);

		return entity;
	}

	private boolean validateParameter(String origin_country_id, String destination_country_id, double weight,
			String created_at, String customer_id, String customer_name, String customer_slug) {

		if (validateDecimalParameter(weight) && validateCountry(origin_country_id, destination_country_id)
				&& validateCustomerId(customer_id))
			return true;
		return false;
	}

	private boolean validateDecimalParameter(double weight) {
		final String regExp = "[0-9]+([,.][0-9]{1,2})?";
		final Pattern pattern = Pattern.compile(regExp);
		return pattern.matches(regExp, weight + "");
	}

	private boolean validateCustomerId(String customer_id) {
		try {
			UUID.fromString(customer_id).toString();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean validateCountry(String origin_country_id, String destination_country_id) {

		final Set<String> ISO_COUNTRIES = Set.of(Locale.getISOCountries());

		if (ISO_COUNTRIES.contains(origin_country_id) && ISO_COUNTRIES.contains(destination_country_id))
			return true;

		return false;
	}
}
