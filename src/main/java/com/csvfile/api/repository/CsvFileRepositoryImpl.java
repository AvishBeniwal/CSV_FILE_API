package com.csvfile.api.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

import org.springframework.stereotype.Repository;

import com.csvfile.api.model.PhoneRecord;

@Repository
public class CsvFileRepositoryImpl implements CsvFileRepository {

	private HashMap<String, PriorityQueue<PhoneRecord>> map = null;
	
	@Override
	public HashMap<String, PriorityQueue<PhoneRecord>> getInputFileRecords(String fileName) {
		if(map == null) this.readFile(fileName);
		return map;
	}
	
	
	private void readFile(String fileName) {
		map = new HashMap<String, PriorityQueue<PhoneRecord>>();
		String line = "";
        String cvsSplitBy = ",";
        BufferedReader br = null;
        try {
        	br = new BufferedReader(new FileReader(fileName));
        while((line = br.readLine()) != null) {
				String[] rowData = line.split(cvsSplitBy);
				PhoneRecord p = null;
				String lastName = null;
				if(rowData.length == 3 && rowData[1].matches(".*\\d.*")) {
					p = new PhoneRecord();
					String[] fullName = rowData[0].split(" ");
					lastName = fullName[1];
					p.setFirstName(fullName[0]);
					p.setLastName(fullName[1]);
					p.setPhoneNumber(rowData[1]);
					p.setState(rowData[2]);
					System.out.print(p.toString());
				}else if(rowData.length == 4 && rowData[3].matches(".*\\d.*")) {
					p = new PhoneRecord();
					lastName = rowData[0];
					p.setFirstName(rowData[1]);
					p.setLastName(rowData[0]);
					p.setPhoneNumber(rowData[3]);
					p.setState(rowData[2]);
					System.out.print(p.toString());
				}else if(rowData.length == 4 && rowData[2].matches(".*\\d.*")) {
					p = new PhoneRecord();
					lastName = rowData[1];
					p.setFirstName(rowData[0]);
					p.setLastName(rowData[1]);
					p.setPhoneNumber(rowData[2]);
					p.setState(rowData[3]);
					System.out.print(p.toString());
				}
				if(p != null && lastName != null) {
					PriorityQueue<PhoneRecord> pq = null;
					if(map.containsKey(lastName)) {
						pq = map.get(lastName);
						pq.add(p);
						map.put(lastName, pq);
					}else {
						pq = new PriorityQueue<PhoneRecord>();
						pq.add(p);
						map.put(lastName, pq);
					}
				}
			}	
		} catch (FileNotFoundException e) {
			System.out.print("ASDFDS");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.print("ASDFDS");
			e.printStackTrace();
		}
	}


	@Override
	public void writeInOutFile(PriorityQueue<PhoneRecord> out) throws IOException {

		File file = new File("output.txt");
		
		if (!file.exists() && file.createNewFile())
		{
		    System.out.println("File is created!");
		} else {
		    System.out.println("File already exists.");
		}
		 
		//Write Content
		FileWriter writer = new FileWriter(file);
		Iterator itr = out.iterator();
		String lname = out.peek().getLastName();
		String line = "Matches for: "+lname;
		int count = 1;
		while(itr.hasNext()) {
			writer.write("Result "+count+": "+itr.next().toString());
			count++;
		}
		writer.close();
	}

}
