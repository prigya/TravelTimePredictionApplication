package com.accenture.TravelTimePredictionApplication.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TravelTimeRecommendor {
	
	//function to call the Rscript model 
	public static String callRModel(String routeId, String weatherCode, String timezone, String weekDayN) throws IOException{
		System.out.println("calling R :"+routeId +" "+weatherCode+" "+timezone+" "+weekDayN);
	System.out.println("calling R");
	Runtime.getRuntime().exec("C:\\Program Files\\R\\R-3.4.1\\bin\\Rscript C:\\Prigya\\RScript\\loadtrafficModel.R "+routeId+" "+weatherCode+" "+timezone+" "+weekDayN); 
	System.out.println("done R");
	
	//read from the csv file 
	String res = readDataFromCSV("C:/Prigya/RScript/PredictedTravelTime.csv");
	System.out.println("travel time is "+res);
	return res;
	}
	
	//function to call the weather API to populate the weather data for given time
	
	
	
	//function to read the csv file 
	private static String readDataFromCSV(String fileName) { 
		String timeinMin = "";
		Path pathToFile = Paths.get(fileName); 
		// create an instance of BufferedReader 
		 
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
			// read the first line from the csv file
			timeinMin = br.readLine(); 
			
			} 
		catch (IOException ioe)
		{ 
			ioe.printStackTrace(); 
		} 
		return timeinMin;
		}
			
		
	

	
}
