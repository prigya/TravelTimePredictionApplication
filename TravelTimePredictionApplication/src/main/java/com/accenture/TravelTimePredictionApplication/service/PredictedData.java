package com.accenture.TravelTimePredictionApplication.service;



public class PredictedData {
	
	private final String weatherDesc ;
	private final String travelData;
	private String realtimeData;
	private String accuracy;
	
	public PredictedData(String travelData,String weatherDesc,String realtimeData,String accuracy) {
        //this.id = id;
        this.travelData = travelData;
        this.weatherDesc = weatherDesc;
        this.realtimeData = realtimeData;
        this.accuracy = accuracy;
    }


    public String getRealtimeData() {
		return realtimeData;
	}

	public String getAccuracy() {
		return accuracy;
	}
	
	public String gettravelData() {
        return travelData;
    }
	
    public String getweatherDesc() {
        return weatherDesc;
    }

}
