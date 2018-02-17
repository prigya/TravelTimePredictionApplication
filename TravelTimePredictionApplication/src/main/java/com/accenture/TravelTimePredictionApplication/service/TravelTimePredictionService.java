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
	
public PredictedData getPredictedValue(String routeId, String timestamp) throws Exception {
		
		
		//get route details
		Route routeDetail = RouteDetails.getRoute(routeId);
		System.out.println("Route detail: "+routeDetail.getName());
		
		
		
		//format timestamp from string to date format
		timestamp = timestamp.replaceAll("T", " "); // this is becoz time stamp coming in url has T in format, needs to be removed
		System.out.println("Time detail: "+timestamp);
		
		//format time to IST timezone, evaluate timeZone of day and day of week, along with time in miliseconds
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // IST time zone 
		Date timeOfTravel1 = formatter.parse(timestamp);
		//using calendar
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeOfTravel1);
		Calendar ISTTime = new GregorianCalendar(TimeZone.getTimeZone("Asia/Kolkata"));
		ISTTime.setTimeInMillis(calendar.getTimeInMillis());
		int hour = ISTTime.get(Calendar.HOUR_OF_DAY);
		int min = ISTTime.get(Calendar.MINUTE);
		System.out.println(hour);
		System.out.println(min);
		Integer timeZone = 0;
		Integer dayofWeek = 0;
		Long timeInMillis = Long.valueOf(0);
		timeInMillis = timeOfTravel1.getTime() ;
		
		TimeZone tz = TimeZone.getTimeZone("Asia/Kolkata");
		int offset = tz.getOffset(timeInMillis);
		
		timeZone = ((hour*60)/15) + ((min+15)/15);
		dayofWeek = ISTTime.get(Calendar.DAY_OF_WEEK);
		timeInMillis = timeInMillis+offset  ;
		
		
		/*Date timeofTrav = getformattedTimestamp(timestamp);
		 //fgsdfgsdfg
		
		//get time zone and day of week
		Integer timeZone = getTimeofDayZone(timestamp);
				
		Integer dayofWeek = timeofTrav.getDay()+1;*/
		
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
		
		//calling accuracy checker
		String realtimedata = TravelTimeAccuracyChecker.SomeRealityCheck(routeDetail.getId(),timeInMillis);
		
		
		
		String duration = TravelTimeRecommendor.callRModel(routeId,weatherCode,timeZone.toString(),dayofWeek.toString());
		
		Integer rtd = Integer.parseInt(realtimedata);
		Integer ptd = Integer.parseInt(duration);
		Integer accu = (ptd - rtd);
		System.out.println("rtd :"+rtd);
		System.out.println("ptd :"+ptd);
		System.out.println("accu :"+accu);
		
		String diff = accu.toString();
		
		String accData = "Google maps predicts travel duration as "+rtd.toString()+". Difference is "+diff+"mins";
		
		String travelData = "You would take "+duration+" mins to travel from "+routeDetail.getName()+" in "+ routeDetail.getCity();
		String weatherDes = "The weather in your city is "+weatherDesc+" and temperature is "+tempDesc;
		//trying to return in json format 
	    
		return new PredictedData(travelData,weatherDes,realtimedata,accData);
	    }
	
	

}
