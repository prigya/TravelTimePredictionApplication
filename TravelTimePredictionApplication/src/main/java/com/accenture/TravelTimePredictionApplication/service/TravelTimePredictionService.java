package com.accenture.TravelTimePredictionApplication.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.accenture.TravelTimePredictionApplication.service.PredictedData;
import com.accenture.TravelTimePredictionApplication.service.Route;
import com.accenture.TravelTimePredictionApplication.service.RouteDetails;
import com.accenture.TravelTimePredictionApplication.service.TravelTimeRecommendor;


@Service
public class TravelTimePredictionService {
	
public PredictedData getPredictedValue(String routeId, String timestamp) throws IOException {
		
		
		//get route details
		Route routeDetail = RouteDetails.getRoute(routeId);
		System.out.println("Route detail: "+routeDetail.getName());
		
		
		
		//format timestamp from string to date format
		timestamp = timestamp.replaceAll("T", " "); // this is becoz time stamp coming in url has T in format, needs to be removed
		System.out.println("Time detail: "+timestamp);
		Date timeofTrav = getformattedTimestamp(timestamp);
		
		//get time zone and day of week
		Integer timeZone = getTimeofDayZone(timestamp);
				
		Integer dayofWeek = timeofTrav.getDay()+1;
		
		//initialize weather code, if API throws exception, this goes as input to the Algorithm
		String weatherCode = "800";
		String weatherDesc = "clear sky";
		String tempDesc = "34";
		
		//call weatherAPI , get the weather data for that city
		WeatherData weatherd = new WeatherData();
		try {
			weatherd = WeatherForecastRetriever.getWeatherCode(timestamp,routeDetail.getCity());
			weatherCode = weatherd.getWeatherCode();
			weatherDesc = weatherd.getWeatherDesc();
			tempDesc = weatherd.getTempInCelsius();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String duration = TravelTimeRecommendor.callRModel(routeId,weatherCode,timeZone.toString(),dayofWeek.toString());
		String travelData = "You would take "+duration+" mins to travel from "+routeDetail.getName()+" in "+ routeDetail.getCity();
		String weatherDes = "The weather in your city is "+weatherDesc+" and temperature is "+tempDesc;
		//trying to return in json format 
	    //return outString;
		return new PredictedData(travelData,weatherDes);
	    }
	
	private Date getformattedTimestamp(String timestamp){
		
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // IST time zone 
		Date timeOfTravel = new Date();
		try {
			timeOfTravel = formatter.parse(timestamp);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return timeOfTravel;
	}
	
	public Integer getTimeofDayZone(String timestamp){
		
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // IST time zone 
		Date timeOfTravel = new Date();
		Integer timezone = 0;
		try {
			timeOfTravel = formatter.parse(timestamp);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(timeOfTravel);
			Calendar ISTTime = new GregorianCalendar(TimeZone.getTimeZone("Asia/Kolkata"));
			ISTTime.setTimeInMillis(calendar.getTimeInMillis());
			int hour = ISTTime.get(Calendar.HOUR_OF_DAY);
			int min = ISTTime.get(Calendar.MINUTE);
			System.out.println(hour);
			System.out.println(min);
			timezone = ((hour*60)/15) + ((min+15)/15);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*Integer v = dt1.getHours();
		Integer v1 = dt1.getMinutes();
		System.out.println(v);
		System.out.println(v1);
		Integer Zone = ( (v*60)/15 ) +(( v1+15)/15);*/
		
		return timezone;
		
	}

}
