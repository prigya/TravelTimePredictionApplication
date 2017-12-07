package com.accenture.TravelTimePredictionApplication.service;

public class WeatherData {
	public String getWeatherCode() {
		return weatherCode;
	}
	public void setWeatherCode(String weatherCode) {
		this.weatherCode = weatherCode;
	}
	public String getWeatherDesc() {
		return weatherDesc;
	}
	public void setWeatherDesc(String weatherDesc) {
		this.weatherDesc = weatherDesc;
	}
	public String getTempInCelsius() {
		return tempInCelsius;
	}
	public void setTempInCelsius(String tempInCelsius) {
		this.tempInCelsius = tempInCelsius;
	}
	private String weatherCode;
	private String weatherDesc;
	private String tempInCelsius;
	
}
