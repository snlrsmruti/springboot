package com.api.tracking.generator.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.tracking.generator.exception.InvalidParameterException;
import com.api.tracking.generator.model.ResponseBean;
import com.api.tracking.generator.validator.ValidateRequestParam;
import com.mifmif.common.regex.Generex;

@RestController
public class TrackShipmentController {


	@GetMapping("/GET/next-tracking-number")
	public ResponseEntity<ResponseBean> getTrackingNumber(@RequestParam String origin_country_id,
			@RequestParam String destination_country_id,
			@RequestParam double weight, @RequestParam String created_at,
			@RequestParam String customer_id, @RequestParam String customer_name, @RequestParam String customer_slug)
			throws InvalidParameterException {

		if (!new ValidateRequestParam().validateParameter(origin_country_id, destination_country_id, weight, created_at, customer_id,
				customer_name, customer_slug))
			throw new InvalidParameterException("Please provide valid input");

		ResponseBean response = new ResponseBean();
		HttpHeaders headers = new HttpHeaders();
		
		response.setTrackingNumber(UUID.randomUUID().toString());
		response.setCreatedAt( new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()));

		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}
}
