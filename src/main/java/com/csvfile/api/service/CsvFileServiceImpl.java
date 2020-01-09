package com.csvfile.api.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csvfile.api.model.PhoneRecord;
import com.csvfile.api.repository.CsvFileRepository;

@Service
public class CsvFileServiceImpl implements CsvFileService {

	@Autowired
	CsvFileRepository csvFileRepository;
	
	@Override
	public void outputRec(String queryFileName, String dataSetFileName)  {
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(queryFileName))) {
			String line;
			HashMap<String, PriorityQueue<PhoneRecord>> map = csvFileRepository.getInputFileRecords(dataSetFileName);
			while((line = br.readLine()) != null) {
				if(map.containsKey(line)) {
					csvFileRepository.writeInOutFile(map.get(line));
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
