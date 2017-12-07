package com.accenture.TravelTimePredictionApplication.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.TravelTimePredictionApplication.service.PredictedData;
import com.accenture.TravelTimePredictionApplication.service.TravelTimePredictionService;

@Controller
public class TravelTimePredictionController {
	
	@Autowired
    TravelTimePredictionService service;

    @RequestMapping(value="/trafficData", method = RequestMethod.GET)
    public String showFirstPage(ModelMap model){
        return "queryPage";
    }

    @RequestMapping(value="/trafficData", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String routeid, @RequestParam String timestamp) throws IOException{

        PredictedData predData = service.getPredictedValue(routeid, timestamp);

        
        model.put("routeid", routeid);
        model.put("timestamp", timestamp);
        model.put("pred", predData.gettravelData());
        model.put("weather", predData.getweatherDesc());

        return "resultPage";
    }

}
