package com.csvfile.api.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

import com.csvfile.api.model.PhoneRecord;

public interface CsvFileRepository {

	public HashMap<String, PriorityQueue<PhoneRecord>> getInputFileRecords(String fileName);
	public void writeInOutFile(PriorityQueue<PhoneRecord> out) throws IOException;
	
}
