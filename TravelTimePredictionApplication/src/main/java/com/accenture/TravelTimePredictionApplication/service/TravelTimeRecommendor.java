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
	
	// create a new array of 5 strings
    String[] cmdArray = new String[6];

    // first argument is the program we want to open
    cmdArray[0] = "/usr/bin/Rscript";
    //cmdArray[0] = "C:\\Program Files\\R\\R-3.4.1\\bin\\Rscript";

    // second argument is a txt file we want to open with notepad
    cmdArray[1] = "/home/leanarch/spring_flow/R_models/loadtrafficModel.R";
    //cmdArray[1] = "C:\\Prigya\\RScript\\loadtrafficModel.R";
    
    //command line arguments to pass
    cmdArray[2] = routeId;
    cmdArray[3] = weatherCode;
    cmdArray[4] = timezone;
    cmdArray[5] = weekDayN;
    

	
	Runtime.getRuntime().exec(cmdArray);
	try {
		//p.waitFor();
		Thread.sleep(7000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	//Runtime.getRuntime().exec("C:\\Program Files\\R\\R-3.4.1\\bin\\Rscript C:\\Prigya\\RScript\\loadtrafficModel.R "+routeId+" "+weatherCode+" "+timezone+" "+weekDayN);
	//Runtime.getRuntime().exec("/usr/lib/R/bin/Rscript /home/leanarch/spring_flow/R_models/loadtrafficModel.R "+routeId+" "+weatherCode+" "+timezone+" "+weekDayN);
	System.out.println("done R");
	
	
	
	//read from the csv file 
	//String res = readDataFromCSV("C:\\Prigya\\RScript\\PredictedTravelTime.csv");
	String res = readDataFromCSV("/home/leanarch/spring_flow/R_models/PredictedTravelTime.csv");
	System.out.println("travel time is "+res);
	return res;
	}
	
	
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
