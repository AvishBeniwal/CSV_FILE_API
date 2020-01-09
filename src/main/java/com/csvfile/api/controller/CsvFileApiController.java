package com.csvfile.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csvfile.api.service.CsvFileService;
import com.csvfile.api.service.CsvFileServiceImpl;

@RestController
@RequestMapping(path = "/csvapi")
public class CsvFileApiController {
	
	@Autowired
	CsvFileService csvFileService;

	@GetMapping("/get")
	public String  readCsvFile() {
		//String fileName = "SampleFile.csv";
		/*String dataFileSetName = "";
		String queryFileName = "";
		csvFileService.outputRec(queryFileName, dataFileSetName);*/;
		String dataFileSetName = "phone_data.csv";
		String queryFileName = "query.txt";
		CsvFileService csvFileService = new CsvFileServiceImpl();
		csvFileService.outputRec(queryFileName, dataFileSetName);
		return "Success";
	}
}
