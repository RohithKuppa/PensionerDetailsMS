package com.pension.managment.Controller;

import java.io.IOException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pension.managment.Exception.NotFoundException;
import com.pension.managment.Model.PensionerDetail;
import com.pension.managment.Service.PensionerdetailService;

@RestController
public class PensionerDetailsController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PensionerdetailService pds;

	@GetMapping("/pensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail getPensionerDetailByAadhaar(@PathVariable long aadhaarNumber ) {
		logger.info("/pensionerDetailByAadhaar is accessed");
		try {
			logger.info("Fetching Pensioner Details");
			return pds.getPensionerDetailByAadhaarNumber(aadhaarNumber);
		} catch (NumberFormatException | IOException | NotFoundException | ParseException e) {
			logger.error("Pensioner Details not found"+e);
			return null;
		}

	}

}
