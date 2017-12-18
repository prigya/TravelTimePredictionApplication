package com.accenture.TravelTimePredictionApplication.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class TravelTimeRecommendor {
	
	//function to call the Rscript model 
	public static String callRModel(String routeId, String weatherCode, String timezone, String weekDayN) throws IOException{
		System.out.println("calling R :"+routeId +" "+weatherCode+" "+timezone+" "+weekDayN);
	System.out.println("calling R");
	
	// create a new array of 5 strings
    String[] cmdArray = new String[6];

    // first argument is the program we want to open
    cmdArray[0] = "/usr/lib/R/bin/Rscript";
    //cmdArray[0] = "C:\\Program Files\\R\\R-3.4.1\\bin\\Rscript";

    // second argument is a txt file we want to open with notepad
    cmdArray[1] = "/home/leanarch/spring_flow/R_models/loadtrafficModel.R";
    //cmdArray[1] = "C:\\Prigya\\RScript\\loadtrafficModel.R";
    
    //command line arguments to pass
    cmdArray[2] = routeId;
    cmdArray[3] = weatherCode;
    cmdArray[4] = timezone;
    cmdArray[5] = weekDayN;
    

	
	/*Runtime.getRuntime().exec(new String[] {"/usr/bin/Rscript","/home/leanarch/spring_flow/R_models/loadtrafficModel.R","01","721","32","2"});
	try {
		//p.waitFor();
		Thread.sleep(7000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
    //trying to use Process builder instead
    
    Process process = new ProcessBuilder(cmdArray).start();
    InputStream is = process.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    String line;

    System.out.printf("Output of running %s is:", 
       Arrays.toString(cmdArray));

    while ((line = br.readLine()) != null) {
      System.out.println(line);
    }

	//Runtime.getRuntime().exec("C:\\Program Files\\R\\R-3.4.1\\bin\\Rscript C:\\Prigya\\RScript\\loadtrafficModel.R "+routeId+" "+weatherCode+" "+timezone+" "+weekDayN);
	//Runtime.getRuntime().exec("/usr/lib/R/bin/Rscript /home/leanarch/spring_flow/R_models/loadtrafficModel.R "+routeId+" "+weatherCode+" "+timezone+" "+weekDayN);
	System.out.println("done R");
	System.out.println("version 9.3");
	
	
	
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
