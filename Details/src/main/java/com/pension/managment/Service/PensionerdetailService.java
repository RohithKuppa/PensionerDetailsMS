package com.pension.managment.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pension.managment.Exception.NotFoundException;
import com.pension.managment.Model.Bank;
import com.pension.managment.Model.PensionerDetail;
import com.pension.managment.util.DateUtil;



@Service
public class PensionerdetailService {

	Logger logger = LoggerFactory.getLogger(PensionerdetailService.class);
	private Map<Long, PensionerDetail> pensionDetails;
	
	
	public PensionerDetail getPensionerDetailByAadhaarNumber(long aadhaarNumber)
			throws IOException, NotFoundException, NumberFormatException, ParseException {
		logger.trace("Started Tracing");
		String line = "";
		pensionDetails = new HashMap<>();
		logger.info("Starting to read Csv file");
	
		BufferedReader br = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream("/details.csv")));
		
	
		logger.info("Successfully completed reading Csv file");
		
		
		while ((line = br.readLine()) != null) // returns a Boolean value
		{
			String[] person = line.split(",");
			PensionerDetail pd = new PensionerDetail(person[1], DateUtil.parseDate(person[2]), person[3],
					Double.parseDouble(person[4]), Double.parseDouble(person[5]), person[6],
					new Bank(person[7], Long.parseLong(person[8]), person[9]));
			pensionDetails.put(Long.parseLong(person[0]), pd);
		}
		logger.trace(" tracing ended ");
		
		if (pensionDetails.containsKey(aadhaarNumber)) {
			return pensionDetails.get(aadhaarNumber);
		} else {
			throw new NotFoundException("AadharNumber Not Found");
		}
	}
	

}
