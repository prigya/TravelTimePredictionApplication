package com.accenture.TravelTimePredictionApplication.service;



public class PredictedData {
	
	private final String weatherDesc ;
	private final String travelData;
	
	public PredictedData(String travelData,String weatherDesc) {
        //this.id = id;
        this.travelData = travelData;
        this.weatherDesc = weatherDesc;
    }

    /*public long getId() {
        return id;
    }*/

    public String gettravelData() {
        return travelData;
    }
    public String getweatherDesc() {
        return weatherDesc;
    }

}
