package com.accenture.TravelTimePredictionApplication.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.json.JSONObject;

public class WeatherForecastRetriever {
	
public static WeatherData getWeatherCode(String dt1, String city) throws Exception{
		
		//get route details
		//Route routeDetail = RouteDetails.getRoute(id);
		
		//second, getting the weather data 
		String url1 = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&units=metric&appid=f3f97cd5ed838ace7b1e3751cfa90d4c";

		URL obj1 = new URL(url1);
		HttpURLConnection con1 = (HttpURLConnection) obj1.openConnection();

		// optional default is GET
		con1.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode1 = con1.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url1);
		System.out.println("Response Code : " + responseCode1);

		BufferedReader in1 = new BufferedReader(
		        new InputStreamReader(con1.getInputStream()));
		String inputLine1;
		StringBuffer response1 = new StringBuffer();

		while ((inputLine1 = in1.readLine()) != null) {
			response1.append(inputLine1);
		}
		in1.close();
		
		//trying for json stuff here 
		JSONObject msgObjWeather = new JSONObject(response1.toString()); // json object for weather data 
		Integer weatherCode = msgObjWeather.getJSONArray("weather").getJSONObject(0).getInt("id");
		String weatherDesc = msgObjWeather.getJSONArray("weather").getJSONObject(0).getString("description");
		Integer tempInCelsius = msgObjWeather.getJSONObject("main").getInt("temp");
		
		//set in weather object
		WeatherData weatherdata = new WeatherData();
		weatherdata.setTempInCelsius(tempInCelsius.toString());
		weatherdata.setWeatherCode(weatherCode.toString());
		weatherdata.setWeatherDesc(weatherDesc);
		
		
		//
		return weatherdata;
			
			
			
		}

}
