package com.accenture.TravelTimePredictionApplication.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.json.JSONException;
import org.json.JSONObject;

public class TravelTimeAccuracyChecker {
	
	//checking the accuracy of predicted data
	
	public static String SomeRealityCheck(String routeId, Long timeInMillis) throws  Exception{
		
		//get route details
		Route routeDetail = RouteDetails.getRoute(routeId);
		System.out.println("routID"+ routeId);
		System.out.println("time in millis" + timeInMillis);
		
		//First getting the Traffic Data
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+routeDetail.getOrigin()+"&destinations="+routeDetail.getDestination()+"&departure_time="+timeInMillis+"&traffic_model=best_guess&mode=driving&key=AIzaSyBHz1QUGhHoP4ziV3SJRcmOAEEMukVGxtU";
		
		//https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=18.595416,73.770809&destinations=18.578769, 73.737213&departure_time=1552142340000&traffic_model=pessimistic&key=AIzaSyDxM4ZhGncwqAgNiKnSUO_CcxytBBpO1sE
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		

		//add request header
		//con.setRequestProperty("User-Agent", "java call");
		

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//trying for json stuff here 
		JSONObject msgObj = new JSONObject(response.toString()); // json object for traffic data 
		
		Integer durationInTraffic = msgObj.getJSONArray("rows")
        .getJSONObject(0)
        .getJSONArray ("elements")
        .getJSONObject(0)
        .getJSONObject("duration_in_traffic").getInt("value");
		
		//get mins
		Integer durationInMins = durationInTraffic/60;
		
		//String originAdd = msgObj.getString("origin_addresses").toString().replace("[","").replace("]","").replace(",", "/");
		//String destinationAdd = msgObj.getString("destination_addresses").toString().replace("[","").replace("]","").replace(",", "/");
		
		//System.out.println("origin: "+originAdd);
		System.out.println("duration_in_traffic: "+durationInMins);
		
		return durationInMins.toString();
	}

}
